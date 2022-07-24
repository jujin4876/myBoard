package boradexample.myboard.myboard.domain.member.dto;


import boradexample.myboard.myboard.domain.member.Member;
import boradexample.myboard.myboard.domain.member.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequestDto {

    private String username;
    private String password;
    private String name;
    private String email;
    private Role role;

    private boolean isDeleted;

    public Member toEntity(String encodePassword){
        return Member.builder()
                .username(username)
                .password(encodePassword)
                .name(name)
                .email(email)
                .role(role.User)
                .isDeleted(false)
                .build();

    }
}
