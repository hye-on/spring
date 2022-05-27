package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private  static Map<Long,Member> store =new HashMap<>();//실무에서는 동시성 문제가 있을 수 있다.
                                                            //공유되는 변수일때는 다른것을 써야함
    private  static long sequence =0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return store.get(id);   //결과가 없으면 NULL. 어떻게?
        return Optional.ofNullable(store.get(id));

    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
               .filter(member -> member.getName().equals(name))
               .findAny();
       //이름이 같은 경우만 필터링 된다. 찾으면 반환한다. 끝까지 돌렸는데 없으면 Optional로 널을 감싸서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore()
    {
        store.clear();
    }
}
