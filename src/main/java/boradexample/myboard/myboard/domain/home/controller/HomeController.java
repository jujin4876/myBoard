package boradexample.myboard.myboard.domain.home.controller;

import boradexample.myboard.myboard.config.auth.PrincipalDetails;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping({ "", "/" })
    public String home(){
        return "login/Login";
        //return "board/newList";
    }

    @GetMapping("/loginForm")
    public String loginPage(){
        return "login/Login";
    }

    @GetMapping("/admin")
    public @ResponseBody String bbb(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String aaa(){
        return "manager";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("principalDetails : " + principalDetails.getMember());
        return "user";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "info";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "data";
    }

    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails){
        System.out.println("/test/login -----------");
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        System.out.println("authorization : " +principalDetails.getMember());
        System.out.println("userDetails : " + userDetails.getMember());
        return "세션 정보 확인";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(Authentication authentication, @AuthenticationPrincipal OAuth2User oauth){
        System.out.println("/test/oauth/login -----------");
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        System.out.println("authorization : " +oAuth2User.getAttributes());
        System.out.println("oauth : " + oauth.getAttributes());
        return "oauth 세션 정보 확인";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error",required = false)String error, @RequestParam(value = "exception",required = false)String exception, Model model){

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login/Login";
    }
}
