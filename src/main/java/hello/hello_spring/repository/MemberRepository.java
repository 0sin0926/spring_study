package hello.hello_spring.repository;


import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // option + enter 쳐서 자동 import
    Optional<Member> findByID(Long id); // findBy~~ 로 가져온 값이 null일 때, Optionam로 감싼다
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 지금까지 저장된 모든 회원 리스트를 반환



}
