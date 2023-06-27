package com.itwill.spring3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring3.dto.PostCreateDto;
import com.itwill.spring3.dto.PostUpdateDto;
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.post.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final로 선언된 field를 자동으로 초기화하는 생성자를 만들어줌
@Service
public class PostService {
    
    private final PostRepository postRepository; // -> final : 의존성 주입 위해. 생성자를 사용한 의존성 주입.
    
    // DB POSTS 테이블에서 전체 검색한 결과를 리턴:
    public List<Post> read() {
        log.info("read()");
        
        return postRepository.findByOrderByIdDesc();
    }
    
    // DB에 POSTS 테이블에 엔터티를 삽입(insert):
    public Post create(PostCreateDto dto) {
        log.info("create(dto={})", dto);
        
        // dto를 entity로 변환
        Post entity = dto.toEntity();
        log.info("entity={}", entity);
        
        postRepository.save(entity);
        log.info("entity={}", entity);
        
        return entity;
    }
    
    public Post read(long id) {
        log.info("read(id={})", id);
        
        return postRepository.findById(id).orElseThrow();
    }
    
    public void delete(long id) {
        postRepository.deleteById(id);
    }
    
    public Post update(PostUpdateDto dto, long id) {
        log.info("update(dto={}, id={})", dto, id);
       
        Post entity = postRepository.findById(id).orElseThrow();
        log.info("update 전: {}", entity);
        
        entity.update(dto);
        
        postRepository.saveAndFlush(entity);
        
        return entity;
    }
}
