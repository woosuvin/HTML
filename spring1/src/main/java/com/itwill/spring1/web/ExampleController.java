package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

// POJO(Plain Old Java Object):
// 특정 클래스를 상속해야만 하거나, 상속 후에 메서드들을 override 해야만 하는 클래스가 아님.
// 스프링 프레임워크는 POJO로 작성된 클래스를 controller로 사용할 수 있음.


@Slf4j // Logger 객체(log)를 생성.
@Controller 
// Spring MVC component 애너테이션(@Component, @Controller, @Service, @Repository, ...) 중 하나. 
// Controller component라는 것을 dispatcherServlet에게 알려줌.
public class ExampleController {
    
    @GetMapping("/") // GET 방식의 요청 주소가 "/"(context root)인 요청을 처리하는 메서드.
    public String home(Model model) {
        log.info("home()");
        
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now);
        
        return "index"; // view의 이름
    }
    
    @GetMapping("/ex1")
    public void example1() {
        log.info("example1() 호출");
        // controller 메서드가 void인 경우(view의 이름을 리턴하지 않는 경우)
        // 요청 주소의 이름이 view의 이름이 됨.
    }
    
}
