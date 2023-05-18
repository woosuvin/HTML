package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // log 출력
@Controller // DispatcherServlet에게 controller component로 등록.
@RequiredArgsConstructor // 생성자에 의한 의존성 주입
@RequestMapping("/post") // PostController 클래스의 메서드들은 요청 주소가 "/post"로 시작.
public class PostController {

    private final PostService postService; // 생성자에 의한 의존성 주입.
    
//    @GetMapping("/list") // GET 방식의 /post/list 요청 주소를 처리하는 메서드.
//    public String list() {
//        log.info("list()");
//        return "list";
//    }
    
    @GetMapping("/list")
    public void list(Model model) {
        log.info("list()");
        
        // controller는 service 계층의 메서드를 호출해서 서비스 기능을 수행
        List<Post> list = postService.read();
        
        // view에 보여줄 데이터를 Model에 저장.
        model.addAttribute("posts", list);
        
        // 리턴 값이 없는 경우 view의 이름은 요청 주소와 같음.
        // /WEB-INF/views/post/list.jsp
    }
    
    @PostMapping("/create")
    public void create() {
        
    }
    
}
