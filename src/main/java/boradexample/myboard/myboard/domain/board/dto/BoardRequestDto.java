package boradexample.myboard.myboard.domain.board.dto;

import boradexample.myboard.myboard.domain.board.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String title;
    private String content;
    private String writer;
    private boolean isDeleted;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .hits(0)
                .isDeleted(false)
                .build();
    }

}
