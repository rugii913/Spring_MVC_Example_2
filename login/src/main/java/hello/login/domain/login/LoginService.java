package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null이면 로그인 실패
     */
    public Member login(String loginId, String password) {
        // 람다, 스트림 사용 안 한 경우
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get(); //get 말고 다른 거 쓰는 게 좋은데 쉽게 설명하려고 get 사용함, 없으면 예외 터짐
//        if (member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }

        // 람다, Optional 스트림 사용 // Optional로 받아온 것은 filter()라는 메서드 사용 가능함
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
