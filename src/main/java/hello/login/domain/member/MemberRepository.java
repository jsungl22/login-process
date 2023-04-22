package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {

        member.setId(++sequence);
        log.info("save: member={}",member);
        store.put(member.getId(),member);
        return member;

    }

    // 회원 조회
    public Member findById(Long id) {
        return store.get(id);
    }

    // 회원 목록 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 회원 조회(로그인 아이디)
    public Optional<Member> findByLoginId(String loginId) {
        // 리스트를 스트림으로 변경 후 루프를 돌면서 필터 조건에 맞는 값 중 가장 먼저 나오는 값을 반환한다
        return findAll().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findFirst();

    }


    // 테스트용 초기화 코드
    public void clearStore() {
        store.clear();
    }


}
