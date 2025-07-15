package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/** private final MemberService memberService = new MemberService();
    @Controller annotation이 있으면 새로운 객체를 생성하는 것이 아니라,
     spring container에 등록하고, spring이 관리하기 시작하면 contatiner로부터 받아서 쓰게 바꿔야함
 */
@Controller
public class MemberController {
    private final MemberService memberService;

    /* 생성자 앞에 @Autowired가 있으면, MemberController가 생성될 때
       스프링빈에 있는 memberService 객체를 가져다가 넣어줌 (연결)

     */
    @Autowired
    public MemberController(MemberService memberService) { // 여기서 MemberService의 원문 코드에서는 @Service가 있어야 스프링이 제대로 인식할 수 있음, 리포지토리도 마찬가지
        this.memberService = memberService;
    }





}
