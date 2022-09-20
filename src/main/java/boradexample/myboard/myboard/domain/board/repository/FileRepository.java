package boradexample.myboard.myboard.domain.board.repository;

import boradexample.myboard.myboard.domain.board.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
