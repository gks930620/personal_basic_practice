package study.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

//강의의 나머지기능들 SPECIFICATION,QUERYbYeXAMPLE 는 따로 코딩안했음. 그냥 듣기만했음.   왜? 잘 안쓴데..
//Proejction은 특정조건에서는 좋긴하지만   queryDSL 사용하자!!
// ㅋㅋ 뭐든 결론은 실무에서는 queryDSL 사용하자





@EnableJpaAuditing  // createTime, 등 넣을거면 이거 꼭 넣어야함
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "study.datajpa.repository")  가 필요한데 boot는 알아서 해줌
public class DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	@Bean
    public AuditorAware<String> auditorProvider(){
	   return () -> Optional.of(UUID.randomUUID().toString());
    }

}
