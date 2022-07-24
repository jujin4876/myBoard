package boradexample.myboard.myboard.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class MemberPageController {

    @GetMapping("createForm")
    public String createForm(){
        return "member/createForm";
    }
}
