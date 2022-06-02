package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private  final MemberRepository memberRepository;// =new MemoryMemberRepository();


    public MemberService(MemberRepository memberRepository) {//외부에서 넣어주도록 바꾸기
        this.memberRepository = memberRepository;                   //스프링 컨테이너에 있는 멤버리포지토리를 넣어준다.
    }

    /**
     * 회원가입
     */
    public  Long join(Member member){//같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);//중복회원 검증

        memberRepository.save(member);
        return  member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw  new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원조회
     */
    public List<Member> findMembers(){
        return  memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
