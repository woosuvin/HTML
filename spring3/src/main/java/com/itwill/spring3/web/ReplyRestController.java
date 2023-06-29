package com.itwill.spring3.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring3.dto.reply.ReplyCreateDto;
import com.itwill.spring3.dto.reply.ReplyUpdateDto;
import com.itwill.spring3.repository.reply.Reply;
import com.itwill.spring3.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController // controller -> view 이름 return restcontroller -> client로 직접 전달되는 데이터 return
@RequestMapping("/api/reply")
public class ReplyRestController {
    
    private final ReplyService replyService;
    
    @GetMapping("/all/{postId}") // {} -> pathVariable
    public ResponseEntity<List<Reply>> allReplies(@PathVariable long postId) { // 중괄호 안과 이름 같아야 함
        log.info("allReplies(postId={})", postId);
        
        List<Reply> list = replyService.read(postId);
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<Reply> create(@RequestBody ReplyCreateDto dto) {
        log.info("create(dto={})", dto);
        Reply reply = replyService.create(dto);
        log.info(reply.toString());
        return ResponseEntity.ok(reply);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        log.info("delete(id={})", id);
        replyService.delete(id);
        return ResponseEntity.ok("Success");
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody ReplyUpdateDto dto) {
        log.info("update(dto={})", dto);
        replyService.update(dto);
        return ResponseEntity.ok("Success");
    }
    
}
