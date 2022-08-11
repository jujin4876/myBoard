package boradexample.myboard.myboard.domain.member.controller;

import boradexample.myboard.myboard.domain.member.dto.MemberRequestDto;
import boradexample.myboard.myboard.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    /**
     * 회원 생성
     */
    @PostMapping("/members")
    public Long save(@RequestBody final MemberRequestDto params){
        return memberService.save(params);
    }

    @GetMapping("/v1/admin")
    public @ResponseBody String bbb(){
        return "admin";
    }

    @GetMapping("/v1/manager")
    public @ResponseBody String aaa(){
        return "manager";
    }

    @GetMapping("/v1/user")
    public @ResponseBody String ccc(){
        return "user";
    }
}
