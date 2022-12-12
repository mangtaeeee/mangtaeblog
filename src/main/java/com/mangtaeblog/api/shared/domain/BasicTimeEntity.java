package com.mangtaeblog.api.shared.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BasicTimeEntity {

    @CreatedDate
    @Column(updatable = false) //등록시 한번만 작성 되면 되기에 false로 두었음
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;
}
