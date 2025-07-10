package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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

    @GetMapping("hello-string")
    @ResponseBody // http에서 body에 리턴 값을 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // API는 view 이런 거 없이 요청한 문자가 그대로 내려감
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // cmd+shift+enter하면 자동완성됨
        hello.setName(name);
        return hello;
    }
    // JSON : { key : value }로 이루어진 구조
    // 리턴값으로 객체를 넘기면 위와 같이 나옴
    static class Hello{
        private String name;

        // ctrl + enter : getter/setter 자동생성 단축키 (우클릭->generate 들어가도 됨!)
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
