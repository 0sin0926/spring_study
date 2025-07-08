package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller // @Controller해서 annotation 적어줘야함
public class HelloController {
    @GetMapping("hello") // 웹 브라우저에서 /hello라고 들어오면 이걸 자동으로 호출
    public String hello(Model model){
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name); // parameter로 넘어온 "name"을 넘겨줌
        return "hello-template";
    }
    // 여기서 name은 request했으니까 웹브라우저에서 주소에 ?name=spring!@ 이런식으로 따로 써서 넘겨줘야함
    // ?~=~ 이런식으로 치면 파라미터에 String name과 model.addAttribute에 name이 입력한 그 값으로 바뀜
    // template에 ${name}도 같이 이거로 바뀐다
}
