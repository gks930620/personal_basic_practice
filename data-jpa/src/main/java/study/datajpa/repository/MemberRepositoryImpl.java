package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl  implements  MemberRepositoryCustom{
    //이거는 MemberRepository + Impl  이름 명칭 규칙만 지켜주면 됨

    private final EntityManager em;


    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m ").getResultList();
    }
}
