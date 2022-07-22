package boradexample.myboard.myboard.dto;

import boradexample.myboard.myboard.domain.board.Board;
import boradexample.myboard.myboard.domain.member.BaseTimeEntity;
import lombok.Getter;

@Getter
public class BoardResponseDto extends BaseTimeEntity {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private int hits; // 조회 수
    private boolean isDeleted; // 삭제 여부

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.hits = entity.getHits();
        this.isDeleted = entity.isDeleted();
        this.setCreateDate(entity.getCreateDate());
        this.setLastModifiedDate(entity.getLastModifiedDate());
    }
}
