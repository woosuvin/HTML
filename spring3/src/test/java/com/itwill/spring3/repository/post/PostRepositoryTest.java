package com.itwill.spring3.repository.post;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.spring3.dto.PostUpdateDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {
    
    @Autowired // 의존성 주입
    private PostRepository postRepository;
    
    //@Test
    public void testSelectAll() {
        List<Post> list = postRepository.findByOrderByIdDesc(); // postRepository.findAll();
        for (Post p : list) {
            log.info(p.toString());
        }
    }
    
    //@Test
    public void testSave() { //insert
        // DB 테이블에 insert 할 레코드(엔터티)를 생성:
        Post entity = Post.builder()
                .title("배고프당")
                .content("JPA 라이브러리를 사용한 insert")
                .author("woosuvin")
                .build();
        
        log.info("insert 전: {}", entity);
        log.info("created: {}, modified: {}", entity.getCreatedTime(), entity.getModifiedTime()); //created: null, modified: null
        
        // DB 테이블에 insert:
        postRepository.save(entity);
        // -> save 메서드는 테이블에 삽입할 엔터티를 파라미터에 전달하면, 테이블에 저장된 엔터티 객체를 리턴.
        //    -> 파라미터에 전달된 엔터티 필드들을 변경해서 리턴.
        
        log.info("insert 후: {}", entity);
        log.info("created: {}, modified: {}", entity.getCreatedTime(), entity.getModifiedTime()); //created: 2023-06-27T10:51:52.749627900, modified: 2023-06-27T10:51:52.749627900
    }
    
    @Test
    public void update() {
        // 업데이트 하기 전의 엔터티 검색:
        Post entity =postRepository.findById(43L).orElseThrow();
        log.info("update 전: {}", entity);
        log.info("update 전 수정 시간: {}", entity.getModifiedTime());
        
        // 엔터티를 변경할 내용을 가지고 있는 객체 생성:
        PostUpdateDto dto = new PostUpdateDto();
        dto.setTitle("JPA 업데이트 테스트0000");
        dto.setContent("JPA Hibernate를 사용한 DB 테이블 업데이트");
        
        // 엔터티를 수정:
        entity.update(dto);
        
        // DB 테이블 업데이트:
        // JPA에서는 insert와 update 메서드가 구분되어 있지 않음.
        // save() 메서드의 argument가 DB에 없는 엔터티이면 insert, DB에 있는 엔터티이면 update를 실행.
        postRepository.saveAndFlush(entity);
    }
    
    //@Test
    public void testDelete() {
        long count = postRepository.count(); // DB 테이블의 행읠 개수(엔터티 개수)
        log.info("삭제 전 count = {}", count);
        
        postRepository.deleteById(3L);
        
        count = postRepository.count();
        log.info("삭제 후 count = {}", count);
    }


}
