package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // spring이 이거 보고 인지
public class SpringConfig {
    // 의존관계 : memberController -> memberService -> memberRepository

    @Bean // 스프링 빈을 등록하겠단 의미
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    // @Bean을 보고 각각 memberService와 리포지토리 빈을 만들어서 넣어놓고
    // service 생성자 body에 있는 parameter를 보고 알아서 빈에서 꺼내서 매핑해줌

}
