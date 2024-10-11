package com.study.outclass.common.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass   // 상속받는 클레스에게 매핑 정보만 제공하는 클레스
@Getter
@Setter
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy
    @Column(updatable = false)
    private String createBy;  // 누가 가입했는지.

    @LastModifiedBy
    private String modifiedBy;  //누가 수정했는지.

}
