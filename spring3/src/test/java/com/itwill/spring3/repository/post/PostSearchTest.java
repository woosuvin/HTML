package com.itwill.spring3.repository.post;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostSearchTest {

    @Autowired
    private PostRepository postRepository;
    
    @Test
    public void testSearchTitle() {
        //List<Post> list = postRepository.findByTitleContainsIgnoreCaseOrderByIdDesc("test");
        //List<Post> list = postRepository.findByContentContainsIgnoreCaseOrderByIdDesc("을");
        //List<Post> list = postRepository.findByAuthorContainsIgnoreCaseOrderByIdDesc("WOO");
        //List<Post> list = postRepository.findByTitleContainsIgnoreCaseOrContentContainsIgnoreCaseOrderByIdDesc("te", "te");
        List<Post> list = postRepository.searchByKeyword("te");
        for (Post p : list) {
            log.info(p.toString());
        }
    }
    
    
    
}
