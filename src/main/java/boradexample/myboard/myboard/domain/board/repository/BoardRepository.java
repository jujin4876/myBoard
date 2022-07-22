package boradexample.myboard.myboard.domain.board.repository;

import boradexample.myboard.myboard.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
