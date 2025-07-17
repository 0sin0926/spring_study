package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


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

    @GetMapping("/members/new") // get 방식 : 웹 주소창에 /memberds/new라고 치면 밑의 함수기능을 수행
    public String createForm() {
        return "members/createMemberForm"; // 리턴하는 이름을 가진 파일을 templates에서 찾아서 실행
    }

    /* <form action="/members/new" method="post"> 이런식으로
        method를 post방식으로 설정한다면,
     */
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {

        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // controller에서 view로 넘겨줌
        return "members/memberList";
    }


}