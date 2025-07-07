package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller // @Controller해서 annotation 적어줘야함
public class HelloController {
    @GetMapping("hello") // 웹 브라우저에서 /hello라고 들어오면 이걸 자동으로 호출
    public String hello(Model model){
        model.addAttribute("data", "hello!");
        return "hello";
    }

}
