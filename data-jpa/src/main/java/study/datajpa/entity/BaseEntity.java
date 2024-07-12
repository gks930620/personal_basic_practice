package study.datajpa.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)  //audinting lister라는걸 알려주는@
@MappedSuperclass
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;


    //테이블마다  데이트는 필요한데 작성자는 필요없거나 그 반대일 수도 있으니까
    // BaseTimeEntity, BaseByEntity 등을 따로  만들어서 사용하면 좋다.
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;  //LocalDateTime은 자동으로 처리되는데 이 String은 어떻게 해야 처리할까?
    //빈 설정에서 해줘야 한다. 빈설정은 main 클래스에서

}
