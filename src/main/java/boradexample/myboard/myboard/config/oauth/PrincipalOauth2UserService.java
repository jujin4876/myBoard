package boradexample.myboard.myboard.config.oauth;

import boradexample.myboard.myboard.config.auth.PrincipalDetails;
import boradexample.myboard.myboard.domain.member.Member;
import boradexample.myboard.myboard.domain.member.Role;
import boradexample.myboard.myboard.domain.member.repository.MemberRepository;
import boradexample.myboard.myboard.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    private final MemberRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest: " + userRequest);
        System.out.println("getAccessToken: " + userRequest.getAccessToken());
        //구글로그인 버튼 클릭 - 구글로그인창 - 로그인완료 - code리턴 - access token요청
        //user Request 정보 - loaduser 함수 - 회원프로필
        System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientId();
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + providerId;
        String email = oAuth2User.getAttribute("email");
        String password = "겟인데어";
        String name = oAuth2User.getAttribute("name");
        //String password = memberService.encodePassword("aaaaaaaaa");

        Member memberEntity = memberRepository.searchByUserName(username);
        if(memberEntity == null){
            memberEntity = Member.builder()
                    .username(username)
                    .name(name)
                    .password(password)
                    .email(email)
                    .role(Role.ROLE_MEMBER)
                    .provider(provider)
                    .providerId(providerId)
                    .isDeleted(false)
                    .build();
            memberRepository.save(memberEntity);
        }
        return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());

        //return super.loadUser(userRequest);
    }
}
