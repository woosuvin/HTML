package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // getter setter requiredArgsConstructor toString equals&hashCode
@AllArgsConstructor // 모든 argument 갖는 생성자
@NoArgsConstructor // 기본 생성자
@Builder
public class PostCreateDto {
    
    // field: jsp에서 request parameter(name)과 동일하게 선언
    private String title;
    private String content;
    private String author;
    
    // PostCreateDto 타입의 객체를 Post 타입의 객체로 변환해서 리턴.
    public Post toEntity() {
//        return new Post(0, title, content, author, null, null);
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
    
}
