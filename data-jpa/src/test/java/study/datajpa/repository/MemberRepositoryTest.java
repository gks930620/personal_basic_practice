package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired
    MemberQueryRepository memberQueryRepository;
    @Autowired
    MemberRepository memberRepository;


    @Autowired
    TeamRepository teamRepository;
    // 개발자는 MemberRepository라는 인터페이스를 만들었을 뿐
    // 실제 injection 되는 객체는 spring이 알아서 만든 구현 클래스 (프록시)

    @PersistenceContext
    EntityManager em;


    @Test
    public void testTeamSetOrder(){
        Team team = new Team("teamA");
        teamRepository.save(team);
        System.out.println("team.getId() = " + team.getId());
        Member m1 = new Member("AAA", 10);
        memberRepository.save(m1);
        m1.setTeam(team);  //DB에는  insert안되네


        Member m2 = new Member("BBB", 10);
        m2.setTeam(team);
        memberRepository.save(m2);


    }

    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);
        Optional<Member> byId = memberRepository.findById(savedMember.getId());
        Member findMember = byId.get();
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findHelloBy() {
        List<Member> helloBy = memberRepository.findTop3HelloBy();
    }

    @Test
    public void testNamedQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);

    }

    @Test
    public void testQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA", 10);
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);

    }

    @Test
    public void findUsernameList() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s=" + s);
        }
    }

    @Test
    public void findMemberDto() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        memberRepository.save(m1);
        m1.setTeam(team);


        List<MemberDto> dtoList = memberRepository.findMemberDto();
        for (MemberDto dto : dtoList) {
            System.out.println("dto = " + dto);
        }
    }


    @Test
    public void findByNames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> memberList = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : memberList) {
            System.out.println("member=" + member);
        }
    }

    @Test
    public void returnTypes() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> list = memberRepository.findListByUsername("adjflhjfkhsdf");  //  list는 null아님 단지 길이0   그래서 if empty하면 별 문제 없음


        Member findMember = memberRepository.findMemberByUsername("adfdf");
        //문제는 단건조회    없으면 null임
        Optional<Member> optionalMember = memberRepository.findOptionalByUsername("AAA");
        //그래서 optioal을 씀.  없어도 되고 있어도 상관없는 optional

        //1건 조회 할거라 생각했는데 결과가 2개 이상이라면??   ==> optional이든 나발이든 예외!!

        System.out.println("list = " + list);
        System.out.println("findMember =" + findMember);
        System.out.println("optionalMember =" + optionalMember);
    }


    @Test
    public void paging() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 5;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));//spring data jpa는 페이징을 0부터


        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);  //pageRequest는 pagable을 상속받았지.  //age왜 있지? 필요없는데...
        //반환타입이 Page다?  totalCount필요하니까 알아서  spring jpa가 날려준다

        Page<MemberDto> dtoMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null)); //이러면 API스펙에 써도 됨


        //then
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();

        for (Member member : content) {
            System.out.println("member = " + member);
        }
        System.out.println("totalElements = " + totalElements);

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();

    }


    @Test
    public void bulkUpdate() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));
        //1. 영속성에 있는 member5 나이 40

        int resultCount = memberRepository.bulkAgePlus(20);  //20이상은 1플러스해준다.
        // 2. DB에서 업데이트 해서 member5 나이 41 .   bulk연산은  영속성컨텍스트를 무시한다.


        em.clear();
        ;
        //5. 그래서 벌크연산후에는 영속성을 초기화해줘야한다.
        // 근데 이거 직접안해도 memberRepostiory에서 @Modifing에  설정해주면 알아서 em. clear 해준다.


        Member member5 = memberRepository.findMemberByUsername("member5");
        // 3. find는 영속성에 있으면 있는거 그대로 사용

        //assertThat(member5.getAge()).isEqualTo(40);
        //4. 그래서 나이 그대로 40     (매우 중요)
        assertThat(member5.getAge()).isEqualTo(41);
        //6.  영속성 날리면 find에서 영속성에 현재 member5가 없으니까 이번엔 DB에서 가져옴

        assertThat(resultCount).isEqualTo(3);
    }

    @Test
    public void findMemberLazy() {

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //save만 해도 DB에 저장됨

        em.flush();
        em.clear();


        List<Member> members = memberRepository.findEntityGraphByUsername("member1");
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member.team = " + member.getTeam().getName());
        }
    }

    @Test
    public void queryHint() {
        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        //when
        Member findMember = memberRepository.findReadOnlyByUsername("member1"); // 나는 조회만하고 값 여러번 바꾸고 놀건데 DB는 변화 안했으면 좋겠다..
        findMember.setUsername("member2");
        em.flush();

    }


    @Test
    public void lock() {
        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");

    }

    @Test
    public void callCustom() {
        //자바에서는 memberRepositoryImpl이 실행되지않지만, spring jpa가 해준다.
        memberRepository.findMemberCustom();
    }


    @Test
    public void projections() {
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);
        em.flush();
        em.clear();


        List<NestedClosedProjections> result = memberRepository.findProjectionsByUsername("m1", NestedClosedProjections.class);
        for (NestedClosedProjections usernameOnly : result) {
            System.out.println("usernameOnly = " + usernameOnly);
            String username = usernameOnly.getUsername();
            System.out.println("username = " + username);
            String name = usernameOnly.getTeam().getName();
            System.out.println("name = " + name);

        }
    }

    @Test
    public void nativeQuery() {
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);
        em.flush();
        em.clear();

        Member result = memberRepository.findBynativeQuery("m1");
        System.out.println("result = " + result);

    }


    @Test
    public void nativeQueryPaging() {
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);
        em.flush();
        em.clear();

        Page<MemberProjection> result = memberRepository.findByNatvieProjection(PageRequest.of(0, 10));
        List<MemberProjection> content=result.getContent();
        for(MemberProjection memberProjection : content){
            System.out.println("memberProjection.username = " + memberProjection.getUsername());
            System.out.println("memberProjection.team = " + memberProjection.getTeamName());
        }


    }

}