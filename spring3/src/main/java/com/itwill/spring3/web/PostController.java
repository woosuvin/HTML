package com.itwill.spring3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring3.dto.PostCreateDto;
import com.itwill.spring3.dto.PostSearchDto;
import com.itwill.spring3.dto.PostUpdateDto;
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.reply.Reply;
import com.itwill.spring3.service.PostService;
import com.itwill.spring3.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;
    private final ReplyService replyService;
    
    @GetMapping
    public String read(Model model) {
        log.info("read()");
        
        // TODO: 포스트 목록 검색
        List<Post> list = postService.read();
        
        // Model에 검색 결과를 세팅.
        model.addAttribute("posts", list);
        
        return "/post/read"; // view 이름 return
    }
    
    @GetMapping("/create")
    public void create() {
        log.info("create:GET");
        // 리턴값이 없는 경우 view의 이름은 요청 주소와 같음
    }
    
    @PostMapping("/create")
    public String create(PostCreateDto dto) {
        log.info("create(dto={}) POST", dto);
        
        // TODO: form에서 submit(제출)된 내용을 DB 테이블에 insert
        postService.create(dto);
        
        // DB 테이블에 insert 후 포스트 목록 페이지로 redirect 이동.
        return "redirect:/post";
    }
    
    // "/post/detail", "/post/modify" 요청 주소들을 처리하는 controller method
    @GetMapping({"/detail", "/modify"})
    public void read(long id, Model model) {
        log.info("read(id={})", id);
        
        // TODO: id로 POSTS 테이블에서 id에 해당하는 포스트를 검색.
        Post post = postService.read(id);
        
        // REPLIES 테이블에서 해당 포스트에 달린 댓글 개수를 검색.
        long count = replyService.countByPost(post);
        
        // 결과를 model에 저장. -> view로 전달.
        model.addAttribute("post", post);
        model.addAttribute("replyCount", count);
        
        // 컨트롤러 메서드의 리턴값이 없는 경우(void인 경우), 뷰의 이름은 요청 주소와 같다!
        // detail -> detail.html, modify -> modify.html
    }
    
    @PostMapping("/delete")
    public String delete(long id) {
        log.info("delete(id={})", id);
        postService.delete(id);
        return "redirect:/post";
    }
    
    @PostMapping("/update")
    public String update(PostUpdateDto dto) {
        postService.update(dto);
        return "redirect:/post/detail?id=" + dto.getId();
    }
    
    @GetMapping("/search")
    public String search(PostSearchDto dto, Model model) {
        log.info("search(dto={})", dto);
        
        // postService의 검색 기능 호출:
        List<Post> list = postService.search(dto);
        
        // 검색 결과를 Model에 저장해서 view로 전달: 같은 html을 사용하므로 thymeleaf에서 사용하는 변수명을 같게 지정해 줘야 함.
        model.addAttribute("posts", list);
        
        return "/post/read";
    }
    
}
