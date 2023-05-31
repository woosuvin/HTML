package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }
)
public class RepositoryTest {
    
    @Autowired
    private PostRepository postRepository;
    
    //@Test
    public void testPostRepository () {
        Assertions.assertNotNull(postRepository);
        log.info("postRepository = {}", postRepository);
        
        Post post = Post.builder()
                .title("myBatis 테스트")
                .content("myBatis 이용한 insert")
                .author("admin")
                .build(); // post 객체 만들어줌.
        log.info(post.toString());
        
        int result = postRepository.insert(post);
        Assertions.assertEquals(1, result);
        log.info("result = {}", result);
    }
    
    //@Test
    public void testSelectOrderByIdDesc() {
        List<Post> list = postRepository.selectOrderByIdDesc();
        for(Post p : list) {
            log.info(p.toString());
        }
    }
    
    //@Test
    public void testSelectById() {
        Post post = postRepository.selectById(6);
        Assertions.assertNotNull(post);
        log.info(post.toString());
        
        post = postRepository.selectById(-1);
        Assertions.assertNull(post);
    }
    
    @Test
    public void testUpdate() {
        Post post = Post.builder()
                .id(1) // 업데이트 할 포스트 아이디
                .title("업데이트 test") // 업데이트 할 제목
                .content("목요일") // 업데이트 할 내용
                .build();
        int result = postRepository.updateTitleAndContent(post);
        Assertions.assertEquals(1, result);
    }
    
    //@Test
    public void testDelete() {
        int result = postRepository.deleteById(5);
        Assertions.assertEquals(0, result);
        
    }
}
