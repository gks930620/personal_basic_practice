package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
// 원래 EntityManager는 @PersistenceContext만 됨, @Autowired는 주입 안되는데
//부트에서는 됩니다  그래서 @RequiredArgsConstructor 사용가능
public class MemberRepositoryOld {


    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery(
                "select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }


}
