package study.querydsl.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.util.StringUtils.isEmpty;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

@Repository
public class MemberJpaRepository {
    @PersistenceContext
    private  EntityManager em;

    @Autowired
    private   JPAQueryFactory queryFactory;

//    public MemberJpaRepository(EntityManager em) {
//  EntityManager를 빈으로 등록하는 방법은 몇개 있지만 QueryFacttory는 따로 없다. 그래서 직접주입하거나  빈으로 등록 후 @붙이면된다.
//        this.em = em;
//        this.queryFactory=new JPAQueryFactory(em);
//    }




    public void save(Member member){
        em.persist(member);
    }
    public Optional<Member> findById(Long id){
        Member findMember=em.find(Member.class,id);
        return Optional.ofNullable(findMember);
    }
    public List<Member> findAll(){
        return em.createQuery("select m from Member m" , Member.class).getResultList();
    }
    public List<Member> findByUsername(String username){
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username" , username)
                .getResultList();
    }


    public List<Member> findAll_Querydsl(){
        return (List<Member>) queryFactory.selectFrom(member).fetch();
    }
    public List<Member> findByUsername_Querydsl(String username){
        return queryFactory.select(member).from(member).where(member.username.eq(username)).fetch();
    }

    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition){
        BooleanBuilder builder=new BooleanBuilder();
        if(StringUtils.hasText(condition.getUsername())){
            builder.and(member.username.eq(condition.getUsername()));
        }
        if(StringUtils.hasText(condition.getTeamName())){
            builder.and(team.name.eq(condition.getTeamName()));
        }
        if(condition.getAgeGoe() !=null){
            builder.and(member.age.goe(condition.getAgeGoe()));
        }
        if(condition.getAgeLoe() !=null){
            builder.and(member.age.goe(condition.getAgeLoe()));
        }


        return queryFactory.select(
                new QMemberTeamDto(member.id.as("memberId"),member.username,member.age, team.id.as("teamId"), team.name.as("teamName"))
        )
                .from(member)
                .leftJoin(member.team,team)
                .where(builder)
                .fetch();
    }


    public List<MemberTeamDto> search(MemberSearchCondition condition){
        return queryFactory.select(
                new QMemberTeamDto(member.id.as("memberId"),member.username,member.age, team.id.as("teamId"), team.name.as("teamName"))
        )
                .from(member)
                .leftJoin(member.team,team)
                .where(
                        usernameEq(condition.getUsername()), teamNameEq(condition.getTeamName())
                        ,ageGoe(condition.getAgeGoe()), ageLoe(condition.getAgeLoe())
                ).fetch();
    }


    // 밑의것들은 MemberTeamDto 뿐만아니라 다른Dto, entity를 조회할 때 그대로 재사용 가능
    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }
    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe !=null ? member.age.goe(ageGoe) : null;
    }
    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe !=null ? member.age.loe(ageLoe) : null;
    }


    //그래서 여러가지 필요할 거 가능 메소드 미리만들어서 사용도 가능
    private  BooleanExpression ageBetween(Integer ageGoe,Integer ageLoe){
        return ageGoe(ageGoe).and(ageLoe(ageLoe) );
    }



}
