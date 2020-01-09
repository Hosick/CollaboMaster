package cm.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //  상속하는 클래스들은 본 필드들을 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {  //  날짜 Auditing 클래스

    @CreatedDate    //  생성 날짜
    private LocalDateTime createdDate;

    @LastModifiedDate   //  최종 수정 날짜
    private LocalDateTime modifiedDate;
}