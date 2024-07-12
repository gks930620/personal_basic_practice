package study.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

@SpringBootTest
@Transactional
@Rollback(false)
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;


    @BeforeEach
    public void before(){
        queryFactory   =new JPAQueryFactory(em);


        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL(){
        //member1을 찾아라
        Member findMember = em.createQuery("select m from Member m where m.username= :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
    @Test
    public void startQuerydsl(){
        //member = Qmember.member   static import 한 상태

        Member findMember= queryFactory.select(member).from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search(){
        Member findMember = queryFactory.selectFrom(member)
                .where(member.username.eq("member1").and(member.age.eq(10)))
                .fetchOne();

        //and or isnot not  goe >=   gt>   loe<=   lt <    like, contais startswtih  등 다있음

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }


    @Test
    public void searchAndParam(){
        Member findMember = queryFactory.selectFrom(member)
                .where(member.username.eq("member1"),(member.age.eq(10)))
                .fetchOne();  //and 대신 , 로 해도 된다.
        //, 쓰면 좋은게 , null, 이러면 null 무시 => 동적쿼리 쉽게 된다.


        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultFetch(){
        List<Member> fetch=queryFactory.selectFrom(member).fetch();
        Member fetchOne = queryFactory.selectFrom(member).fetchOne();
        Member fetchFirst = queryFactory.selectFrom(member).fetchFirst();
        QueryResults<Member> results = queryFactory.selectFrom(member).fetchResults();
        long total = results.getTotal();
        List<Member> content = results.getResults();
    }

    @Test
    public void sort(){
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));
        List<Member> result = queryFactory.selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        Member member5=result.get(0);
        Member member6=result.get(1);
        Member memberNull=result.get(2);
        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }

      @Test
    public void paging1(){
        List<Member> result = queryFactory.selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }


    @Test
    public void paging2(){
        QueryResults<Member> queryResult = queryFactory.selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();
        //where조건이 붙으면 카운트쿼리랑 셀렉트쿼리 둘다 붙는다.
        assertThat(queryResult.getTotal()).isEqualTo(4);
        assertThat(queryResult.getLimit()).isEqualTo(2);
        assertThat(queryResult.getOffset()).isEqualTo(1);
        assertThat(queryResult.getResults().size()).isEqualTo(2);
    }


    @Test
    public void aggregation(){
        List<Tuple> result = queryFactory.select(member.count(), member.age.sum(), member.age.avg(), member.age.max(), member.age.min())
                .from(member)
                .fetch();
        Tuple tuple = result.get(0);  //쿼리 잘 보면 결과는 1개일 수밖에..
        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);


    }

    /**
     * 팀의 이름과 각 팀의 평균 연령을 구해라
     * @throws Exception
     */
    @Test
    public void group() throws Exception  {
        List<Tuple> result = queryFactory.select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();
        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);

        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35 );
    }


    /**
     *
     * teamA에 소속된 모든 회원
     */
    @Test
    public void join() throws Exception  {

        List<Member> result = queryFactory.selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();  //leftjoin, rightjoin 하면 됨.  null이 없으니까 결과는 같을 것

        assertThat(result).extracting("username").containsExactly("member1","member2");

    }


    @Test
    public void theta_join_my() throws Exception  {
        List<Member> result = queryFactory.select(member)
                .from(member, team)
                .fetch(); //카타시안이랑 동일


        assertThat(result.size()).isEqualTo(8);
    }

    /**
     * 세타 조인
     * 회원의 이름이 팀 이름과 같은 회원 조회
     */
    @Test
    public void theta_join() throws Exception  {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));  //사람이름이 teamA teamB
        List<Member> result = queryFactory.select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();//카타시안조인이랑 동일    연관관계가 없는 테이블도 조인 가능하다는걸 보여줄려고,

            //이런 세타조인방식은  left right 등 외부조인이 원래는 안됐음
             // 조인 on 을 사용해서 해결
       assertThat(result).extracting("username").containsExactly("teamA","teamB");
     }


     /**
      * 회원,팀 조인,    팀 이름이 teamA인 팀만,    회원은 모두 조회
      * JPQL  : select mmt from Member m left join m.team t on t.name='teanA'
      */
     @Test
    public void join_on_filtering(){
         List<Tuple> results= queryFactory.select(member, team)
                 .from(member)
                 .leftJoin(member.team, team).on(team.name.eq("teamA"))

                    //where(team.name.eq("teamA"))   on대신 where을 사용하면 left,right가 의미 없어짐 결과는 뭐, teamA인 데이터만 보임
                 .fetch();
         for(Tuple tuple : results){
             System.out.println("tuple=" + tuple);
         }
     }



    /**
     *  연관관계 없는 엔티티 외부조인
     *  회원의 이름이 팀 이름과 같은 대상 외부 조인
     */
    @Test
    public void join_on_no_relation(){
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));
        List<Tuple> result = queryFactory.select(member,team)
                .from(member)
                .leftJoin(team).on(member.username.eq(team.name))
                // on은 필터링,   join에는 join 조건이 들어가는데  지금은 그냥 join조건없이  다  데이터 가지고 옴  team 없을때도 null로 오지.

                .fetch();

        for(Tuple tuple : result){
            System.out.println("tuple= " + tuple);
        }
    }


    @PersistenceUnit
    EntityManagerFactory emf;


    @Test
    public void fetchJoinNo(){
        em.flush();
        em.clear();
        Member findMember = queryFactory.selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();    // 영속성컨텍스트에 조회될떄는 member만.  team 조회 안됨  기본 LAZY

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());   // 영속성컨텍스트에 올라왔는지 안됬는지 확인해주는 메서드
        assertThat(loaded).as("페치조인 미적용").isFalse() ;
    }



    @Test
    public void fetchJoinUse(){
        em.flush();
        em.clear();

        Member findMember = queryFactory.selectFrom(member)
                .join(member.team,team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();    // 페치조인을 통해 가져왔기때문에 team도 영속성 컨텍스트에 존재

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());   // 영속성컨텍스트에 올라왔는지 안됬는지 확인해주는 메서드
        assertThat(loaded).as("페치조인 미적용").isTrue() ;
    }



    @Test
    public void subQuery(){
        QMember memberSub=new QMember("memberSub");

        List<Member> result = queryFactory.selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions.select(memberSub.age.max()).from(memberSub))
                )
                .fetch();
        assertThat(result).extracting("age").containsExactly(40);
    }


    /**
     * 평균보다큰 회원
     */
    @Test
    public void subQueryGoe(){
        QMember memberSub=new QMember("memberSub");

        List<Member> result = queryFactory.selectFrom(member)
                .where(member.age.goe(
                        JPAExpressions.select(memberSub.age.avg()).from(memberSub))
                )
                .fetch();
        assertThat(result).extracting("age").containsExactly(30,40);
    }

    @Test
    public void subQueryIn(){
        QMember memberSub=new QMember("memberSub");

        List<Member> result = queryFactory.selectFrom(member)
                .where(member.age.in(
                        JPAExpressions.select(memberSub.age).from(memberSub).where(memberSub.age.gt(10)))   // where  in 20,30,40
                )
                .fetch();
        assertThat(result).extracting("age").containsExactly(20,30,40);
    }

    @Test
    public void selectSubQuery(){
        QMember memberSub=new QMember("memberSub");
        List<Tuple> result = queryFactory.select(member.username
                , JPAExpressions               //참고로 static import 하면 쉽게  sql 짜듯이 가능
                        .select(memberSub.age.avg())
                        .from(memberSub)
        ).from(member)
                .fetch();


        for(Tuple tuple : result){
            System.out.println("tuple = " + tuple);
        }
    }


    //JPA에서는 from  서브쿼리 안 해줌  .  querydsl도 안됨
    //해결방식은 1. join으로 변경  2. 쿼리2번 또는 애플리케이션단에서 해결  3. nativeSQL, mybatis 등

    @Test
    public void basicCase(){
        List<String> result = queryFactory.select(member.age.when(10).then("열살")
                .when(20).then("스무살").otherwise("기타")
        ).from(member)
                .fetch();
        for(String s : result){
            System.out.println("s = " + s);
        }
    }

    @Test
    public void complexCase(){
        List<Tuple> result = queryFactory.select(member,new CaseBuilder().when(member.age.between(0, 20)).then("0~20살")
                .when(member.age.between(21, 30)).then("성인")
                .otherwise("기타")
        ).from(member)
                .fetch();
        for(Tuple  s : result){
            System.out.println("s = " + s);
        }
    }

    @Test
    public void constant(){
        List<Tuple> result = queryFactory.select(member.username, Expressions.constant("A"))
                .from(member)
                .fetch();

        for(Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    @Test
    public void concat(){
        // "_"  + 3  ,  문자+숫자니까 concat 될거같지면 타입 달라서 안됨.  그래서 StringValue 활용
        List<String> result = queryFactory.select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();

        for(String  s : result){
            System.out.println("s = " + s);
        }
    }

    @Test
    public void simpleProjection(){
        List<Member> result = queryFactory.select(member)
                .from(member)
                .fetch();  //반환타입이 Member ,   select에 member.username이면 String
    }


    @Test
    public void tupleProjection(){
        List<Tuple> result = queryFactory.select(member.username,member.age)
                .from(member)
                .fetch();  // select에 2개면 반환타입이 tpuple
        for(Tuple tuple : result){
            String username= tuple.get(member.username);
            Integer age= tuple.get(member.age);
            System.out.println("username = " + username);
            System.out.println("age = " + age);
        }
    }



    @Test
    public void findDtoByJPQL(){
        List<MemberDto> resultList = em.createQuery("select new study.querydsl.dto.MemberDto(m.username,m.age) from Member m ", MemberDto.class)
                .getResultList();
        for(MemberDto memberDto : resultList){
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoBySetter(){
        List<MemberDto> result = queryFactory.select(Projections.bean(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();
        for(MemberDto memberDto : result){
            System.out.println("memberDto = " + memberDto);
        }
    }
    @Test
    public void findDtoByConstructor(){ //이거의 단점은 없는 생성자( 파라미터 개수가 다르거나 타입이 다를때)룰 써도 컴파일에러안남. but runtiomError
        List<MemberDto> result = queryFactory.select(Projections.constructor(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();
        for(MemberDto memberDto : result){
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByFileds(){  //files에다가 값을 한번에 꽂는건 알겠는데,  private인데도 그게 가능한가?  자바 리플렉션을 통해서 가능.
        List<MemberDto> result = queryFactory.select(Projections.fields(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();
        for(MemberDto memberDto : result){
            System.out.println("memberDto = " + memberDto);
        }
    }




    @Test
    public void findDtoByFiledsUserDto(){    //UserDto는  필드이름이 name인데  에러는 안나고 그냥 세팅이 안됨  => as 활용  or ExpressionUtis  상황에 따라 편한거
        QMember memberSub = new QMember("memberSub");

        List<UserDto> result = queryFactory.select(Projections.fields(UserDto.class, member.username.as("name"),
                ExpressionUtils
                .as(JPAExpressions.select(memberSub.age.max())
                .from(memberSub),"age"))
                )
                .from(member)
                .fetch();
        for(UserDto userDto : result){
            System.out.println("userDto = " + userDto);
        }
    }

    @Test
    public void findDtoByConstructorUserDto(){
        QMember memberSub = new QMember("memberSub");

        List<UserDto> result = queryFactory.select(Projections.constructor(UserDto.class, member.username.as("name"),
                ExpressionUtils
                        .as(JPAExpressions.select(memberSub.age.max())
                                .from(memberSub),"age"))
        )
                .from(member)
                .fetch();
        for(UserDto userDto : result){
            System.out.println("userDto = " + userDto);
        }
    }


    //@QueryProejction을 MemberDto 생성자에 추가했음
    //findDtoByConstructor  에서의 단점을 compieErrro에서 해결해줌
    // 물론 단점도 잇음
    @Test
    public void findDtoByQueryProjection(){
        List<MemberDto> results = queryFactory.select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();
        for(MemberDto memberDto : results){
            System.out.println("memberDto = " + memberDto);
        }
    }

    // 동적쿼리 해결해보기   1. boolean builder
    @Test
    public void dynamicQuery_BooleanBuilder(){
        String usernameParam="member1";
        Integer ageParam=null;

        List<Member> result=searchMember1(usernameParam,ageParam);
        assertThat(result.size()).isEqualTo(1);
    }
    private  List<Member> searchMember1(String usernameCond,Integer ageCond){ //파라미터가 있을 수도 있고 null일수도 있다..
        BooleanBuilder builder=new BooleanBuilder();
        if(usernameCond !=null){
            builder.and(member.username.eq(usernameCond));
        }
        if (ageCond!=null){
            builder.and(member.age.eq(ageCond));
        }
        return queryFactory.selectFrom(member)
                .where( builder)
                .fetch();
    }

    // 동적쿼리 해결해보기   2. where 다중 파라미터
    @Test
    public void dynamicQuery_WhereParam(){
        String usernameParam="member1";
        Integer ageParam=null;

        List<Member> result=searchMember2(usernameParam,ageParam);
        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameCond,Integer ageCond) {
        return queryFactory.selectFrom(member)
                .where(usernameEq(usernameCond),ageEq(ageCond) )   //where조건에 null이 들어가면 기본적으로 무시됨
                .fetch();
    }


    private BooleanExpression usernameEq(String usernameCond) {
        return usernameCond ==null ? null : member.username.eq(usernameCond);
    }
    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond ==null ? null : member.age.eq(ageCond);
    }

    private  Predicate allEq(String usernameCond,Integer ageCond){
        return usernameEq(usernameCond).and(ageEq(ageCond) );
    }
    //  위 eq 메소드들을 만들 면 좋은 점
    //  사용예시   광고의 경우,   isValid,  날짜가 In =>  isServicable   으로  조합해서 사용가능.=> 재사용가능






    //배치 : 실시간처리가 중요하지 않은 업무들을 모아서 한번에 처리
    // update는 항상 DB, 영속성 컨테스트  상태를 생각해야한다.
    @Test
    public void bulkUpdate(){
        long count = queryFactory.update(member).set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();   //    bulk연산은  DB에는 변경되었지만,  영속성컨테스는 변함없음.
        //select하면  영속성 컨텍스트에 이미 있는 경우 기존 거 사용     (쿼리가 안 날라가는건 아님)
        List<Member> reskt = queryFactory.selectFrom(member).fetch();
        for(Member member : reskt){
            System.out.println("member = " + member);
        }

        // 해결방법 :  벌크연산 후 바로 영속성 초기화( 직접하거나 @통해서)
    }

    @Test
    public void bulkAdd(){ //더하기 외에도 비슷하게
        long count = queryFactory.update(member).set(member.age, member.age.add(1))
                .execute();
    }

    @Test
    public void bulkDelete(){
        long count = queryFactory.delete(member)
                .where(member.age.gt(18))
                .execute();
    }

    @Test
    public void sqlFunction(){   //H2DB에 replace라는 함수가 있고, 이게 방언에 등록되어있어야함.   보통은 내가 쓰는 sql에 유명한 함수는 다 됨 걱정 ㄴㄴ
        List<String> result = queryFactory.select(Expressions.stringTemplate(
                "function('replace',{0},{1},{2})", member.username, "member", "M")
        ).from(member).fetch();
        for(String s : result){
            System.out.println("s = " + s);

        }
    }

    @Test  //근데대부분 있어서 그냥 . lower 쓰면 됨
    public void sqlFunction2(){
        List<String> result = queryFactory.select(member.username).from(member)
                .where(
                        member.username.eq(
                member.username.lower()
                        )
        ).fetch();

        for(String s : result){
            System.out.println("s = " + s);
        }
    }



}
