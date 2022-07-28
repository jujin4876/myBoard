package boradexample.myboard.myboard.domain.board.service;

import boradexample.myboard.myboard.domain.board.Board;
import boradexample.myboard.myboard.domain.board.repository.BoardRepository;
import boradexample.myboard.myboard.domain.board.dto.BoardRequestDto;
import boradexample.myboard.myboard.domain.board.dto.BoardResponseDto;
import boradexample.myboard.myboard.exception.CustomException;
import boradexample.myboard.myboard.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(final BoardRequestDto params) {
        Board entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 게시글 리스트 조회
     */
    public List<BoardResponseDto> findAll() {

        Sort sort = Sort.by(Sort.Direction.DESC, "id", "LastModifiedDate");
        List<Board> list = boardRepository.findAllByIsDeleted(false, sort);
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }


    /**
     * 게시글 상세정보 조회
     */
    @Transactional
    public BoardResponseDto findById(final Long id) {

        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.increaseHits();
        return new BoardResponseDto(entity);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long id, final BoardRequestDto params) {

        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;

    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public Long delete(final Long id) {

        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete();
        return id;
    }

}
