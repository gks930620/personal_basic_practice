package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication
public class QuerydslApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuerydslApplication.class, args);
	}



	//JPAQueryFactory 빈 등록
	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em){   //이렇게 빈으로 할려면 em이 빈으로 등록되어있어야함.
		return new JPAQueryFactory(em);
	}
}
