package boradexample.myboard.myboard.config.oauth;

import boradexample.myboard.myboard.config.auth.PrincipalDetails;
import boradexample.myboard.myboard.config.oauth.provider.FacebookUserInfo;
import boradexample.myboard.myboard.config.oauth.provider.GoogleUserInfo;
import boradexample.myboard.myboard.config.oauth.provider.OAuth2UserInfo;
import boradexample.myboard.myboard.domain.member.Member;
import boradexample.myboard.myboard.domain.member.Role;
import boradexample.myboard.myboard.domain.member.dto.MemberRequestDto;
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


    //private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest: " + userRequest);
        System.out.println("getAccessToken: " + userRequest.getAccessToken());
        //구글로그인 버튼 클릭 - 구글로그인창 - 로그인완료 - code리턴 - access token요청
        //user Request 정보 - loaduser 함수 - 회원프로필
        System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes());
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            System.out.println("페이스북");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }
        else{
            System.out.println("구글과 페이스북만 지원");
        }


        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String email = oAuth2UserInfo.getEmail();
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String name = oAuth2UserInfo.getName();

        Member memberEntity = memberRepository.searchByUserName(username);
        if(memberEntity == null){
            MemberRequestDto memberRequestDto;
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
        else{
            System.out.println("로그인을 이미 한적이있습니다");
        }
        return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());

        //return super.loadUser(userRequest);
    }
}
