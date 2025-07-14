package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // 다른 데서 (test) 또 새로운 repository 객체를 만들게 하는 게 아니라, 이 함수에다가 넣게해서 초기화
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 x
        // memberRepository.findByName(member.getName()); 까지만 치고,
        // cmd+option+v 치면 밑에처럼 리턴해줌
        /*Optional<Member> result = memberRepository.findByName(member.getName());

        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); => 이거를 밑에처럼 쓸 수 있다*/
        validateDuplicateMember(member);
        // cmd+t 눌러서 extract method를 하면, 이렇게 내가 원하는 함수를 바깥에다 만들어주고 기존 코드는 새로운 함수의 바디가 됨

        memberRepository.save(member);
        return member.getId();
        // 따라서 join함수는 중복회원을 검사하고, 통과하면 저장하고 리턴함
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
        // memberRepository.findByName(member.getName()) 얘가 optional 값을 반환해주니까 이렇게 쓸수있는것임
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findByID(memberId);
    }
}
