import Entity.Member;
import Entity.Team;
import common.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);
            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);
            Member member1 = new Member();
            member1.setName("member1");
            member1.setTeam(teamA);
            Member member2 = new Member();
            member2.setName("member2");
            member2.setTeam(teamA);
            Member member3 = new Member();
            member3.setName("member3");
            member3.setTeam(teamB);   //member3만 teamB
            member1.setHomeAddress(new Address("city", "street", "33333"));
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.flush();
            em.clear();
            System.out.println("=================================================");
            





            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }


    private static void printMember(Member member) {
        System.out.println("member.getName() = " + member.getName());
    }

    private static void printMemberAndTeam(Member member) {
        String name = member.getName();
        System.out.println("name = " + name);
        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }


}
