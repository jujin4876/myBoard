package boradexample.myboard.myboard.config.jwt;

import boradexample.myboard.myboard.config.auth.PrincipalDetails;
import boradexample.myboard.myboard.domain.member.Member;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

// /login요청시 username,password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 동작
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter: 로그인 시도중" );
        try {
            /*BufferedReader br = request.getReader();
            String input = null;
            while ((input = br.readLine()) != null){
                System.out.println(input);
            }*/
            //System.out.println(request.getInputStream());
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(),Member.class);
            System.out.println(member);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getUsername(),member.getPassword());

            // loadbyusername 실행
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            //authentication 객체가 session영역에 저장됨
            PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
            System.out.println(principalDetails.getMember().getUsername());
            System.out.println("============================================================");
            return authentication;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
    * attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수 실행
    * jwt 토큰을만들어서 request요청한 사용자에게 jwt 토큰을 response 해주면 됨
    * */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        System.out.println("successfulAuthentication : 실행");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getMember().getId())
                .withClaim("username", principalDetails.getMember().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}
