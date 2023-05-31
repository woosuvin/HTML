package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Reply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
)
public class ReplyRepositoryTest {
    
    // Repository 객체를 주입받음(의존성 주입, DI)
    @Autowired // field에 의한 의존성 주입
    private ReplyRepository replyRepository;
    
    //@Test
    public void testReplyRepository () {
        
        Assertions.assertNotNull(replyRepository);
        log.info(replyRepository.toString());
        
        List<Reply> list = replyRepository.selectByPostId(43);
        for (Reply reply : list) {
            log.info(reply.toString());
        }
    }
    
    @Test
    public void testInsertReply() {
        Reply entity = Reply.builder()
                    .post_id(43)
                    .reply_text("insert 테스트 테스트~~~^^  testtesttest")
                    .writer("test count test")
                    .build();
        log.info(entity.toString());
        
        int result = replyRepository.insert(entity);
        Assertions.assertEquals(1, result);
        log.info("result = {}", result);
    }
    
    //@Test
    public void testUpdateReply() {
        Reply entity = Reply.builder()
                .reply_text("댓글 수정 update 테스트")
                .id(1)
                .build();
        log.info(entity.toString());
        
        int result = replyRepository.update(entity);
        Assertions.assertEquals(1, result);
        log.info("result = {}", result);
    }
    
    //@Test
    public void testDeleteReply() {
        int result = replyRepository.delete(2);
        Assertions.assertEquals(1, result);
    }
    
    //@Test
    public void testCountReply() {
        long result = replyRepository.selectReplyCountWithPostId(43);
        log.info("count = {}", result);
    }
    
    
}
