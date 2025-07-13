package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

// 마찬가지로, MemberRepository를 상속받을거니까 이거 클릭하고 option+enter하면 method들을 자동 추가할 수 있음
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long seqeunce = 0L; //012.. 등의 key값 생성

    @Override
    public Member save(Member member) {
        member.setId(++seqeunce); // id를 set하고
        store.put(member.getId(), member); // store(Map)에 저장
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        return Optional.ofNullable(store.get(id)); // 값이 null이어도 감쌀 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name)) // getName으로 넘어온 게 parameter에 있는 name과 같은지 판단
                .findAny(); // 루프를 돌면서 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
