package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Hello;
import study.querydsl.entity.Member;
import study.querydsl.entity.QHello;
import study.querydsl.entity.QMember;
import study.querydsl.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class QuerydslApplicationTests {

	@PersistenceContext  //표준스펙은 이걸권장하지만 스프링에서는 걍 inject autowired 가능
	EntityManager em;

	@Autowired
	MemberRepository memberRepository;
	@Test
	void contextLoads() {
		Hello hello=new Hello();
		em.persist(hello);

		JPAQueryFactory query=new JPAQueryFactory(em);

		QHello qHello = new QHello("h");
		Hello result = query.selectFrom(qHello).fetchOne(); //파라미터로는 QHello, 결과는 Hello
		assertThat(result).isEqualTo(hello);

	}


}
