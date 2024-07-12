package study.querydsl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {
    private final InitMemberService initMemberService;


    @PostConstruct  //static class 만들어서 하는 이유는  spring life cycle 관련인데,,,,  사실 trasactional이 안 먹어 controller단에서..
    public void init(){
        initMemberService.init();
    }

    @Component
    static class InitMemberService{
        @PersistenceContext
        private EntityManager em;
        @Transactional
        public void init(){
            Team teamA=new Team("teamA");
            Team teamB=new Team("teamB");
            em.persist(teamA);
            em.persist(teamB);
            for(int i=0 ; i<100 ; i++){
                Team selectedTeam= i%2 ==0? teamA : teamB;
                em.persist(new Member("member"+i,i,selectedTeam));
            }
        }
    }
}