package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // lombok에서 기본 생성자 자동으로 만들어주는 기능.
@AllArgsConstructor // 모든 field를 argument로 갖는 생성자 만들어줌.
@Builder // setter, toString, ... 
@Getter // getter method
@Setter // setter method
@ToString // toString method
public class Post {
    
    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    
    
}
