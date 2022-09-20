package boradexample.myboard.myboard.domain.board.dto;

import boradexample.myboard.myboard.domain.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String title;
    private String content;
    private String writer;

    private boolean isDeleted;

    private Long fileId;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .fileId(fileId)
                .hits(0)
                .isDeleted(false)
                .build();
    }

}
