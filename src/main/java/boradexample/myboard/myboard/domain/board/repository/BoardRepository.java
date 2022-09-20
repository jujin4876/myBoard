package boradexample.myboard.myboard.domain.board.repository;

import boradexample.myboard.myboard.domain.board.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    /**
     * 게시글 리스트 조회 - (삭제 여부 기준)
     */
    List<Board> findAllByIsDeleted(final boolean isDeleted, final Sort sort);
}
