package boradexample.myboard.myboard.domain.member.service;

import boradexample.myboard.myboard.domain.member.Member;
import boradexample.myboard.myboard.domain.member.dto.MemberRequestDto;
import boradexample.myboard.myboard.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //추가

    /**
     * 회원 생성
     */
    @Transactional
    public long save(final MemberRequestDto params) {
        var password = encodePassword(params.getPassword());
        Member entity = memberRepository.save(params.toEntity(password));
        return entity.getId();
    }

    public String encodePassword(String pw){
        return bCryptPasswordEncoder.encode(pw);
    }

    /**
     * 이메일 중복 확인
     */

    public boolean findByEmail(String email){
        //중독 된 이메일 확인
        boolean isEmail =memberRepository.existsByEmail(email);
        return isEmail;
    }
}
