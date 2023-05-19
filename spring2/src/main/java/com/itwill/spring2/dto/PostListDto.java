package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class PostListDto { 
    
    // field
    private long id;
    private String title;
    private String author;
    private Timestamp modifiedTime;
    // -> JSTL에서는 LocalDateTime 객체를 사용하지 못하기 때문에 Timestamp 타입으로 선언.
    
    // Post 타입의 객체를 PostListDto 타입의 객체로 변환해서 리턴하는 메서드.
    public static PostListDto fromEntity (Post entity) {
        return PostListDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .modifiedTime(Timestamp.valueOf(entity.getModified_time()))
                .build();
    }
    
}
