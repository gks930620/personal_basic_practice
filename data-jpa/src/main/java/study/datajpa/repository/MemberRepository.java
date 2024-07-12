package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.persistence.Entity;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryCustom{

    // List<Member> findByUsername(String username);
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy();  // findAll이 아니어도 기본적으로 이렇게 이상하게 하면 전체조회

    List<Member> findTop3HelloBy();

    // @Query(name = "Member.findByUsername")    //@Qeury없어도 동작함. 메소드이름에 따라
    List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto( m.id,m.username,t.name) from Member m join m.team t")  //DTO조회때는 new operation써야지..
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);


    List<Member> findListByUsername(String username);
    Member findMemberByUsername(String username);
    Optional<Member> findOptionalByUsername(String username);

    @Query(value = "select m from Member m left join m.team t",  countQuery = "select count(m.username) from Member m")  //없어도 동작하는데 join관련 기능을 넣고 싶을 때 + count쿼리를 따로 만들고 싶을 때
    Page<Member> findByAge(int age, Pageable pageable);
    //age 필요없는데

    List<Member> findOnlyListByAge(int age, Pageable pageable);   // page에 있는 정보 잘 필요없고, 몇번째부터 몇번째까지 데이터만 가져오면 돼!! 할때


    @Modifying(clearAutomatically = true)  // 이게 있어야 executeUpdate를 실행.  없으면 getSingleResult나 getResultList 실행
    //참고로 flush와 clear 차이는 잘 이해하자.  flush는 영속성에 있는걸 DB로 날리기,  clear는 영속성 초기화
    @Query(" update Member m set m.age = m.age +1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);


    @Query(" select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll(); //  기본적으론 member만 조회하는데 fetch조인하고싶을때  직접 jpql쓰기 귀찮을 때

    @EntityGraph(attributePaths = {"team"})
    @Query(" select m from Member m")
    List<Member> findMemberEntityGraph();   //위와 동일  jpql쓰고 페치조인 내용 쓰기 귀찮아

    //@EntityGraph("Member.team")
    @EntityGraph(attributePaths = {"team"})   //위에것처럼 Member 클래스에 설정해서 써도 되지만  이게 더 편할듯
    List<Member> findEntityGraphByUsername(@Param("username") String username);  //부분찾을때도 페치조인 적용가능!!


    @QueryHints(value = @QueryHint(name="org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    <T> List<T> findProjectionsByUsername(@Param("username") String username,Class<T> type); // API에 따라 여러 DTO를 줘야할 때 하면 쉽겠군



    //native쿼리는 return 타입 지원이 적어서 한계가 많다. 그래서 이렇게 하는것보다 mybatis나 다른 방법을 알아보자.
    @Query(value = "select * from member where username =?" , nativeQuery = true)
    Member findBynativeQuery(String username);

    @Query(value = "select m.member_id as id, m.username, t.name as teamName from member m left join team t", nativeQuery = true
    ,countQuery = "select count(*) from member")
    Page<MemberProjection> findByNatvieProjection(Pageable pageable);

}
