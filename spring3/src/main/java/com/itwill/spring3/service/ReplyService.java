package com.itwill.spring3.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring3.dto.reply.ReplyCreateDto;
import com.itwill.spring3.dto.reply.ReplyUpdateDto;
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.post.PostRepository;
import com.itwill.spring3.repository.reply.Reply;
import com.itwill.spring3.repository.reply.ReplyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ReplyService {
    
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    
    public List<Reply> read(Post post) {
        log.info("read(Post={})", post);
        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);
        return list;
    }
    
    public Long countByPost(Post post) {
        log.info("countByPost(post={})", post);
        return replyRepository.countByPost(post);
    }
    
    @Transactional(readOnly = true)
    public List<Reply> read(long postId) { // 포스트id로 댓글 목록 찾기
        log.info("read(postId={})", postId);
        
        // 1. postId로 Post를 검색
        Post post = postRepository.findById(postId).orElseThrow();
        
        // 2. 찾은 Post에 달려 있는 댓글 목록을 검색.
        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);
        
        return list;
    }
    
    public Reply create(ReplyCreateDto dto) {
        log.info("create(dto= {})", dto);
        
        // 1. post 엔터티 검색
        Post post = postRepository.findById(dto.getPostId()).orElseThrow();
        
        // 2. ReplyCreateDto 객체를 Reply 엔터티 객체로 변환
        Reply entity = Reply.builder()
                .post(post)
                .replyText(dto.getReplyText())
                .writer(dto.getWriter())
                .build();
        
        // 3. DB replies 테이블에 insert
        replyRepository.save(entity);
        log.info("entity={}", entity);
        
        return entity;
    }
    
    public void delete(long id) {
        log.info("delete(id={})", id);
        
        // DB replies 테이블에서 ID(고유키)로 엔터티 삭제하기:
        replyRepository.deleteById(id);
    }
    
    @Transactional // -> DB에서 검색한 엔터티를 수정하면, 트랜젝션이 끝나는 시점에 update 쿼리가 자동으로 실행됨.
    public void update(long id, ReplyUpdateDto dto) {
        log.info("update(id={}, dto={})", id, dto);
        
        // 1. 댓글 아이디로 DB에서 엔터디를 검색(select):
        Reply entity = replyRepository.findById(id).orElseThrow();
        
        // 2. 검색한 엔터티의 property를 수정:
        entity.update(dto.getReplyText());
    }
    
}
