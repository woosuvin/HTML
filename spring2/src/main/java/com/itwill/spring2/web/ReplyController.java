package com.itwill.spring2.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.domain.Reply;
import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.dto.ReplyReadDto;
import com.itwill.spring2.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/reply")
public class ReplyController {
    
    private final ReplyService replyService;
    
    @PostMapping
    public ResponseEntity<Integer> createReply(@RequestBody ReplyCreateDto dto) {
        log.info("createReply(dto= {})", dto);
        
        int result = replyService.create(dto);
        //TODO 
//        if (result == 0) {
//            return ResponseEntity.status(500).build();
//        }
//        return ResponseEntity.ok(result);
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/all/{postId}")
    public ResponseEntity<List<ReplyReadDto>> read(@PathVariable long postId) {
        log.info("read(postId = {})", postId);
        
        List<ReplyReadDto> list = replyService.read(postId);
        log.info("# of replies = {}", list.size());
        
        return ResponseEntity.ok(list);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteReply(@PathVariable long id) {
        log.info("delete(id = {})", id);
        
        int result = replyService.delete(id);
        
        return ResponseEntity.ok(result);
    }
}
