package boradexample.myboard.myboard.domain.member;


import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "Member2")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //primary Key

    @Column(nullable = false, length = 30, unique = true)
    private String username;//아이디

    private String password;//비밀번호

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    private String name;//이름(실명)

    @Enumerated(EnumType.STRING)
    private Role role;//권한 -> USER, ADMIN

    @Column(nullable = false)
    private boolean isDeleted;// 삭제 여부 -> true면 삭제

    //== 정보 수정 ==//
    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
        this.setLastModifiedDate(LocalDateTime.now());
    }

    public void updateName(String name){
        this.name = name;
    }

    //== 패스워드 암호화 ==//
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    @Builder
    public Member(PasswordEncoder passwordEncoder, String username, String password, String email,String name, Role role, boolean isDeleted){
        this.username = username;
        this.password = passwordEncoder.encode(password);
        this.email = email;
        this.name = name;
        this.role = role.User;
        this.isDeleted = isDeleted;
    }

}
