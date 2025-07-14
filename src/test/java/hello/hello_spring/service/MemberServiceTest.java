package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// main의 MemberService 클래스에서 cmd+shift+t(test)를 누르면 test 폴더와 파일이 자동으로 생김
class MemberServiceTest {

    MemberService memberService/* = new MemberService()*/;
    /*MemoryMemberRepository memberRepository = new MemoryMemberRepository();
     -> 이거도 new ~~로 새로운 repository 객체를 생성해버린 것 (이렇게 하면 안 됨 원래)
    */
    MemoryMemberRepository memberRepository;

    /** 새로 리포지토리 객체를 만들어서 서로 다른 객체를 쓰는 것이 아니라
     * beforeEach를 통해 test 전에 미리 공유 객체를 만들도록 함
     * DI (Dependency Injection) : 어떤 객체가 필요로하는 의존 객체를 생성하는 게 아니라, 외부에서 주입(inject) 받는 방식
     * => 여기서는 service에 repository 객체를 주입하는 방식(새로 만드는 게 아니라)
    */
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // test는 전체 실행 코드에 영향을 주지 않기 때문에 보다 직관적으로 한글로 이름을 적어도 무방!
        //given : 뭔가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        //when : 이걸 실행했을 때
        // memberService.join(member); // memberService에 join을 검증하는 것 -> cmd+shift+v해서 리턴값 뽑아내
        Long saveId = memberService.join(member);

        //then : 결과가 이게 나와야돼
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    };

    @Test // 위의 회원가입 test는 정상 경우만 있음. join의 핵심은 예외를 확인해야함
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member(); // shift+F6 or 우클릭해서 전체 rename 가능
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // 이름이 똑같은 member2를 또 join하면 예외에서 걸려서 터짐

        /**
        try{
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         // 같은 이름의 member2가 들어와서 실패한 게 catch로 넘어감
        // > getmessage가 리턴하는 값이 우리가 service에 써둔 문장과 일치하는지를 확인
        /* jUnit 스타일은 assertEqauls(expected, actual); 이렇게 쓰지만
            assertJ 라이브러리를 이용해 assertThat(actual).isEqualTo(expected); 이런식으로 쓸 수 있음
         */

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}