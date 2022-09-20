package boradexample.myboard.myboard.domain.board.dto;

import boradexample.myboard.myboard.domain.board.entity.Board;
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

    private Long fileId;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.hits = entity.getHits();
        this.isDeleted = entity.isDeleted();
        this.fileId = entity.getFileId();
        this.setCreateDate(entity.getCreateDate());
        this.setLastModifiedDate(entity.getLastModifiedDate());
    }

}
