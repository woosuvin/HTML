package com.itwill.spring3.repository;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// 여러 테이블에서 공통으로 사용되는 생성 시간, 수정 시간을 property로 갖는 객체를 설계.

@EntityListeners(AuditingEntityListener.class) // -> main method를 가지는 메인 클래스에서 JPA Auditing 기능이 활성화 되어 있는 경우에 Entity가 삽입/수정되는 시간이 자동으로 기록되도록 하기 위해서.
@MappedSuperclass // -> 다른 도메인(Entity) 클래스의 상위 클래스로 사용됨. 이 클래스를 상속하는 하위 클래스는 BaseTimeEntity가 정의하는 컬럼들을 갖게 됨.
@Getter // -> getter method 자동 생성.
public class BaseTimeEntity {
    
    // 표기법 - camel 표기법, 이름 - db와 같아야 함
    // Entity class의 field 이름은 db 테이블의 컬럼 이름과 같거나, 컬럼 이름을 camel 표기법으로 변환한 이름으로 작성.
    // (ex) 테이블 컬럼 created_time -> 클래스 필드 createdTime
    // 엔터티 클래스의 필드 이름은 자바의 관습(camel 표기법)으로 작성.
    // 테이블의 컬럼 이름은 데이터베이스의 관습(snake 표기법)을 따름.
    
    @CreatedDate // insert 될 때의 시간이 자동으로 기록됨.
    private LocalDateTime createdTime; 
    
    @LastModifiedDate // update 될 때의 시간이 자동으로 기록됨.
    private LocalDateTime modifiedTime;
    
}
