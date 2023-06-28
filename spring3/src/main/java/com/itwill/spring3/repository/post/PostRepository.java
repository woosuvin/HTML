package com.itwill.spring3.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    // id 내림차순 정렬
    // select * from POSTS order by ID desc
    List<Post> findByOrderByIdDesc();
    
    // 제목으로 검색:
    // select * from POSTS p where lower(p.TITLE) like lower('%' || ? || '%') order by p.ID desc
    List<Post> findByTitleContainsIgnoreCaseOrderByIdDesc(String title);
    
    // 내용으로 검색:
    // select * from POSTS p where lower(p.CONTENT) like lower('%' || ? || '%') order by p.ID desc
    List<Post> findByContentContainsIgnoreCaseOrderByIdDesc(String content);
    
    // 작성자로 검색:
    // select * from POSTS p where lower(p.AUTHOR) like lower('%' || ? || '%') order by p.ID desc
    List<Post> findByAuthorContainsIgnoreCaseOrderByIdDesc(String author);
    
    // 제목 또는 내용으로 검색:
    // select * from POSTS p where lower(p.TITLE) like lower('%' || ? || '%') or lower(p.CONTENT) like lower('%' || ? || '%') order by p.ID desc
    List<Post> findByTitleContainsIgnoreCaseOrContentContainsIgnoreCaseOrderByIdDesc(String title, String content);
    
    // JPQL(JPA Query Language) 문법으로 쿼리를 작성하고, 그 쿼리를 실행하는 메서드 이름을 설정:
    // JPQL은 Entity 클래스의 이름과 필드 이름들을 사용해서 작성.
    // DB 테이블의 이름과 컬럼 이름을 사용하지 않음!!!
    @Query(
            "select p from Post p " +
            "where lower(p.title) like lower('%' || :keyword || '%') " +
            "or lower(p.content) like lower('%' || :keyword || '%') " +        
            "order by p.id desc"
    )
    List<Post> searchByKeyword(@Param("keyword") String keyword);
    
}
