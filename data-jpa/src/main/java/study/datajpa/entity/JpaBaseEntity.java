package study.datajpa.entity;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;


@Getter
@MappedSuperclass  //진짜 상속관계가 아니라 그냥 속성만 데려와서 쓰는 상속관계
public class JpaBaseEntity {
    @Column(updatable = false, insertable = true)
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdDate=now;
        updatedDate=now;
    }

    @PreUpdate
    public void preUpdate(){
        updatedDate= LocalDateTime.now();
    }


}
