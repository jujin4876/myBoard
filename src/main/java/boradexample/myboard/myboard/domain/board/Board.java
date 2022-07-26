package boradexample.myboard.myboard.domain.board;


import boradexample.myboard.myboard.domain.member.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    private String title; // 제목

    private String content; // 내용

    private String writer; // 작성자

    private int hits; // 조회 수

    private boolean isDeleted; // 삭제 여부

    @Builder
    public Board(String title,String content,String writer, int hits, boolean isDeleted){

        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
        this.isDeleted = isDeleted;
    }

    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.setLastModifiedDate(LocalDateTime.now());
    }

    /**
     * 조회 수 증가
     */
    public void increaseHits() {
        this.hits++;
    }

    /**
     * 게시글 삭제
     */
    public void delete() {
        this.isDeleted = true;
    }
}
