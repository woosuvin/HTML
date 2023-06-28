package com.itwill.spring3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.reply.Reply;
import com.itwill.spring3.repository.reply.ReplyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ReplyService {
    
    private final ReplyRepository replyRepository;
    
    public List<Reply> read(Post post) {
        log.info("read(Post={})", post);
        List<Reply> list = replyRepository.findByPost(post);
        return list;
    }
    
    
    
}
