package study.querydsl.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import study.querydsl.entity.Member;

import java.util.List;



public interface MemberRepository extends JpaRepository<Member,Long> , MemberRepositoryCustom , QuerydslPredicateExecutor<Member> {  //근데 실제 구현체는 MemberReposiryImpl 걸 사용
    //QuerydslPr~~~는 조인이 불가능.   그래서 안 씀
    //select m from Member m where m.username = :username
    List<Member> findByUsername(@Param("username") String username);   //@Param 안쓰면 에러날거임



}
