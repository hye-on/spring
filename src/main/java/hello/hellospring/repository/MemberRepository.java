package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장하면 저장된 회원이 반환된다.
    Optional<Member> findById(Long id); //id로 회원을 찾는것
    Optional<Member> findByName(String name);
    List<Member> findAll(); //지금까지 저장된 모든 회원 리스트
}
