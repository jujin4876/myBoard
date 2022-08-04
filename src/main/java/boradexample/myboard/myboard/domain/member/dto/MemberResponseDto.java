package boradexample.myboard.myboard.domain.member.dto;

import boradexample.myboard.myboard.domain.member.BaseTimeEntity;
import boradexample.myboard.myboard.domain.member.Member;
import boradexample.myboard.myboard.domain.member.Role;

public class MemberResponseDto extends BaseTimeEntity {

    private long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Role role;
    private boolean isDeleted;

    private String providerId;

    private String provider;

    public MemberResponseDto(Member entity){
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.role = entity.getRole();
        this.isDeleted = entity.isDeleted();
        this.setCreateDate(entity.getCreateDate());
        this.setLastModifiedDate(entity.getLastModifiedDate());
    }
}
