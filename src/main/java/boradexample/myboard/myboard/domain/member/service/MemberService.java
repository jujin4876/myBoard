package boradexample.myboard.myboard.domain.member.service;

import boradexample.myboard.myboard.domain.member.Member;
import boradexample.myboard.myboard.domain.member.dto.MemberRequestDto;
import boradexample.myboard.myboard.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; //추가

    /**
     * 회원 생성
     */
    @Transactional
    public Long save(final MemberRequestDto params) {
        var password = passwordEncoder.encode(params.getPassword());
        Member entity = memberRepository.save(params.toEntity(password));
        return entity.getId();
    }
}
