package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostDetailDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.dto.PostUpdateDto;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // log 출력
@Controller // DispatcherServlet에게 controller component로 등록.
@RequiredArgsConstructor // 생성자에 의한 의존성 주입. final 변수를 argument로 갖는 생성자.
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
        List<PostListDto> list = postService.read();
        
        // view에 보여줄 데이터를 Model에 저장.
        model.addAttribute("posts", list);
        
        // 리턴 값이 없는 경우 view의 이름은 요청 주소와 같음.
        // /WEB-INF/views/post/list.jsp
    }
    
    @GetMapping("/create")
    public void create() {
        log.info("GET: create()");
       
    }
    
    @PostMapping("/create")
    public String create(PostCreateDto dto) {
        log.info("POST: create({})", dto);
        
        // service 계층의 method 호출 - 새 포스트 등록
        int result = postService.create(dto);
        log.info("포스트 등록 결과 = {}", result);
        
        // Post - Redirect - Get
        return "redirect:/post/list";
    }
    
    @GetMapping("/detail")
    public void detail(long id, Model model) {
        log.info("detail(id = {})", id);
        
        // service 계층의 method 호출해서 화면에 보여줄 PostDetialDto를 가져옴.
        PostDetailDto dto = postService.read(id);
        
        // view에 PostDetailDto를 전달.
        model.addAttribute("post", dto);
    }
    
    @GetMapping("/modify")
    public void modify(long id, Model model) {
        log.info("modify(id = {})", id);
        
        PostDetailDto dto = postService.read(id);
        model.addAttribute("post", dto);
    }
    
    @PostMapping("/delete")
    public String delete(long id) {
        log.info("delete(id = {})", id);
        
        int result = postService.delete(id);
        
        return "redirect:/post/list";
    }
    
    @PostMapping("/update")
    public String update(PostUpdateDto dto) {
        log.info("update({})", dto);
        
        int result = postService.update(dto);
        return "redirect:/post/detail?id=" + dto.getId();
    }
    
}
