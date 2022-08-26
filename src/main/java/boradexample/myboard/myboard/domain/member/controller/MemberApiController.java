package boradexample.myboard.myboard.domain.member.controller;

import boradexample.myboard.myboard.domain.member.Member;
import boradexample.myboard.myboard.domain.member.dto.MemberRequestDto;
import boradexample.myboard.myboard.domain.member.service.MemberService;
import boradexample.myboard.myboard.exception.BadRequestException;
import boradexample.myboard.myboard.exception.EmailDuplicateException;
import boradexample.myboard.myboard.exception.ErrorCode;
import boradexample.myboard.myboard.exception.StatusCode;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    /**
     * 회원 생성
     */
    @PostMapping("/members")
    public long save(@RequestBody final MemberRequestDto params){
        var isEmail =memberService.findByEmail(params.getEmail());
        if(isEmail){
            throw new EmailDuplicateException("email duplicated", StatusCode.EMAIL_DUPLICATION);
        }

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
