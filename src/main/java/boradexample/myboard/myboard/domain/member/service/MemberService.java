package boradexample.myboard.myboard.domain.member.service;

import boradexample.myboard.myboard.domain.board.Board;
import boradexample.myboard.myboard.domain.board.dto.BoardResponseDto;
import boradexample.myboard.myboard.domain.member.Member;
import boradexample.myboard.myboard.domain.member.dto.MemberRequestDto;
import boradexample.myboard.myboard.domain.member.dto.MemberResponseDto;
import boradexample.myboard.myboard.domain.member.repository.MemberRepository;
import boradexample.myboard.myboard.exception.CustomException;
import boradexample.myboard.myboard.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public Long save(final MemberRequestDto params) {
        var password = bCryptPasswordEncoder.encode(params.getPassword());
        Member entity = memberRepository.save(params.toEntity(password));
        return entity.getId();
    }
}
