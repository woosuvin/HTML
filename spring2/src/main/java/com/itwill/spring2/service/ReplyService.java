package com.itwill.spring2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Reply;
import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.dto.ReplyReadDto;
import com.itwill.spring2.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final field 초기화하는 생성자
@Service // 스프링 컨텍스트에 서비스 컴포넌트 객체로 등록
public class ReplyService {
    
    private final ReplyRepository replyRepository;

    public int create(ReplyCreateDto dto) {
        log.info("create(dto= {})", dto);
        
        return replyRepository.insert(dto.toEntity());
    }

    public List<ReplyReadDto> read(long postId) {
        log.info("read(postId = {})", postId);
        
        List<Reply> list = replyRepository.selectByPostId(postId);
        
        return list.stream().map(ReplyReadDto::fromEntity).toList();
        // 들어온 원소가 fromEntity의 argument 이거나, 뭐지
    }
    
    public int delete(long id) {
        log.info("delete(id = {})", id);
        
        return replyRepository.delete(id);
    }
    
    
}
