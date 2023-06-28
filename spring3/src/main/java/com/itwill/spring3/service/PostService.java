package com.itwill.spring3.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring3.dto.PostCreateDto;
import com.itwill.spring3.dto.PostSearchDto;
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
    @Transactional(readOnly = true) // 수정 안되게 readOnly
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
    
    // detail
    @Transactional(readOnly = true)
    public Post read(long id) {
        log.info("read(id={})", id);
        return postRepository.findById(id).orElseThrow();
    }
    
    // delete
    public void delete(long id) {
        postRepository.deleteById(id);
    }
    
    // 1. 메서드에 @Transactional 애너테이션을 설정
    // 2. DB에서 entity를 검색
    // 3. 검색한 entity를 수정
    // 트랜잭션이 끝나는 시점에 DB update가 자동으로 수행됨.
    @Transactional //(1)
    public void update(PostUpdateDto dto) {
        log.info("update(dto={})", dto);
        Post entity = postRepository.findById(dto.getId()).orElseThrow(); //(2)
        entity.update(dto); //(3)
    }
    
    /*
     * public Post update(PostUpdateDto dto) { 
     *      log.info("update(dto={})", dto);
     * 
     *      Post entity = postRepository.findById(dto.getId()).orElseThrow();
     *      log.info("update 전: {}", entity);
     *      entity.update(dto); 
     *      postRepository.saveAndFlush(entity);
     *      return entity; 
     * }
     */
    
    @Transactional(readOnly = true)
    public List<Post> search(PostSearchDto dto) {
        log.info("search(dto={})", dto);
        List<Post> list = null;
        
        switch (dto.getType()) {
        case "t":
            list = postRepository.findByTitleContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
            break;
        case "c":
            list = postRepository.findByContentContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
            break;
        case "tc":
            list = postRepository.searchByKeyword(dto.getKeyword());
            break;
        case "a":
            list = postRepository.findByAuthorContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
            break;
        }
        
        return list;
    }
    
}
