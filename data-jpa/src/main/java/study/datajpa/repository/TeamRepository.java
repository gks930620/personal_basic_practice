package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Team;


@Repository // 있어도 되지만 JpaRepository를 상속받은 interface니까 spring이 알아서   객체 만들고 빈으로 가지고 있는다.   나중에 주입된다.
public interface TeamRepository extends JpaRepository<Team,Long> {
}
