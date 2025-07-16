package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // 그냥 첫 화면, local host:8080으로 들어오면 이게 호출됨
    public String home(){
        return "home"; // home.html 호출됨
    }


}
