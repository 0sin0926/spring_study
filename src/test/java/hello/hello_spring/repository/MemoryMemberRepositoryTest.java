package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 1. 다른 데서 갖다 쓸 게 아니니까 굳이 public 안 해도 됨
// 2. class 이름 관례 : 테스트 할 class 이름 뒤에 Test를 붙여서 만듦
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 테스트가 끝나고 작동
    public void afterEach(){ // 테스트할 때 만들어놓은 객체를 초기화 // 없애놓지 않으면 다음 테스트에 영향이 간다
        repository.clearStore();
    }

    @Test // org.junit.jupiter.api라고 뜨는 걸 선택
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findByID(member.getId()).get();
        /* Assertions.assertEquals(member, result);
        1. 같은지 확인해줌 (이거도 org.junit.jupiter.api)
        2. assertEquals(expected, actual) */

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // findByName 테스트

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
