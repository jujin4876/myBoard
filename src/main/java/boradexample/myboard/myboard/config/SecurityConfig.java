package boradexample.myboard.myboard.config;

import boradexample.myboard.myboard.config.auth.CustomAuthFailureHandler;
import boradexample.myboard.myboard.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
//@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final CorsFilter corsFilter;

    /* 로그인 실패 핸들러 의존성 주입 */
    //private final AuthenticationFailureHandler customFailureHandler;
    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new CustomAuthFailureHandler();
    }
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter) //@CrossOrigin(인증x), 시큐리티 필터에 등록인증(o)
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hsaRole(ROLE_USER)or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();*/



        //oauth 인증
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/", "/login/createForm").permitAll()
                //.anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/login")//로그인 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행
                    .defaultSuccessUrl("/board/list")
                    .failureHandler(failureHandler()) // 로그인 실패 핸들러
                    //.failureUrl("/login/error")
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/board/list")
                    .loginPage("/")
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService); //구글 로그인이 완료 된 뒤 후처리


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/auth/**","/assets/**","/img/**","/favicon.ico","/error");
    }
   /* @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }*/


}
