package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.spring1.dto.UserDto;

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
    public String home(Model model) { // view에 전달할 데이터가 있으면 model로 parameter 선언해야됨.
        log.info("home()");
        
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now); // view에 전달할 데이터 세팅.
        
        return "index"; // view의 이름(WEB-INF/view/index.jsp)
    }
    
    @GetMapping("/ex1")
    public void example1() {
        log.info("example1() 호출");
        // controller 메서드가 void인 경우(view의 이름을 리턴하지 않는 경우)
        // 요청 주소의 이름이 view의 이름이 됨.
    }
    
    @GetMapping("/ex2")
    public void getParamEx(String username, int age) { // 변수: request parameter name과 같게
        log.info("getParamEx(username= {}, age= {})", username, age);
    }
    
    @PostMapping("/ex3") // ex3 처리하는 controller
    public String getParamEx2(
            @RequestParam(name = "username") String name, // (1) request parameter가 username인 것을 찾아서 name에 넣어줌
            @RequestParam(defaultValue = "0") int age // (2) request parameter는 항상 문자열임
    ) {
        // controller 메서드를 선언할 때, parameter 선언 앞에 @RequestParam 애너테이션을 사용하는 경우:
        // (1) 메서드 parameter와 요청 parameter 이름이 다를 때, name 속성으로 요청 parameter 이름을 설정.
        // (2) 요청 paramter 값이 없거나 비어있을 때, defaultValue 설정.
        log.info("getParamEx2(name= {}, age= {})", name, age);
        return "ex2"; // ex2 화면으로 출력
    }
    
    @GetMapping("/ex4")
    public String getParamEx3(UserDto user) {
        log.info("getParamEx3(user= {})", user);
        // DispatcherServlet은 컨트롤러의 메서드를 호출하기 위해서,
        // 1. 요청 파라미터들을 분석(request.getParameter()).
        // 2. UserDto의 기본 생성자를 호출해서 객체를 생성.
        // 3. 요청 파라미터들의 이름으로 UserDto의 setter 메서드를 찾아서 호출.
        // 4. UserDto 객체를 컨트롤러 메서드를 호출할 때 argument로 전달.
        
        return "ex2";
    }
    
    @GetMapping("/sample")
    public void sample() {
        log.info("sample()");
    }
    
    @GetMapping("/forward") // url value값
    public String forwardTest() {
        log.info("forwardTest()");
        
        // controller 메서드에서 다른 페이지(요청 주소)로 forward 하는 방법:
        // "forward:"로 시작하는 문자열을 리턴하면, DispatcherServlet은 리턴값이 view의 이름이 아니라 포워드 이동할 페이지 주소로 인식.
        return "forward:/sample";
    }
    
    @GetMapping("/redirect")
    public String redirectTest(RedirectAttributes attrs) {
        log.info("redirectTest()");
        
        // controller 메서드에서 redirect되는 페이지까지 유지되는 정보를 저장할 때, 한 번 redirect 될 때 까지만 유지되는 정보. 
        attrs.addFlashAttribute("redAttr", "test");
        
        // controller 메서드에서 다른 페이지(요청 주소)로 redirect 하는 방법:
        // "redirect:"로 시작하는 문자열을 리턴하면, DispatcherServlet은 리턴값이 view의 이름이 아니라 리다이렉스 이동할 페이지 주소로 인식.
        return "redirect:/sample";
    }
    
}
