package com.itwill.spring3.repository.post;

import com.itwill.spring3.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString

@Entity // JPA Entity class - 데이터베이스 테이블과 매핑되는 클래스. id 컬럼이 반드시 있어야 함.
@Table(name = "POSTS") // Entity class 이름이 데이터베이스 테이블 이름과 다를 경우, 테이블 이름을 명시
@SequenceGenerator(name = "POSTS_SEQ_GEN", sequenceName = "POSTS_SEQ", allocationSize = 1)
public class Post extends BaseTimeEntity {
    
    @Id // Primary Key 제약조건
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTS_SEQ_GEN")
    private long id;
    
    @Column(nullable = false) // Not Null 제약조건
    private String title;
    
    @Column(nullable = false)
    private String content;
    
    @Column(nullable = false)
    private String author;
}
