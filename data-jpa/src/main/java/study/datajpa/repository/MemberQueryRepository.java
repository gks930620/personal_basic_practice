package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    //복잡한 쿼리는  이렇게 @Repository 따로 붙여서 만들고  사용하면 됨.  spring jpa에서 제공하는 interface로 해결이 안되는 동적쿼리 등등의 경우에

    private final EntityManager em;
    List<Member> findAllMembers(){
        return em.createQuery(" select m from Member m").getResultList();
    }

}
