package com.example.pagination;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.pagination.entity.Board;
import com.example.pagination.entity.Comment;
import com.example.pagination.repository.BoardRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
class JpaFetchjoinPaginationApplicationTests {

    @Autowired
    private BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    void addBoard() {
        for (int i = 0; i < 10; i++) {
            Board board = boardRepository.save(Board.builder()
                .title("제목" + i)
                .writer("작성자" + i)
                .content("본문" + i)
                .build());
            addComment(board);
        }
    }

    void addComment(Board board) {
        board.addComments(Comment.builder()
            .comment("내용")
            .writer("작성자")
            .build());
        boardRepository.save(board);
    }

    @Test
    void fetch_join_페이징_10건_조회() {
        //given
        addBoard();
        entityManager.clear();
        //when
        Page<Board> boards = boardRepository.findAllByBoard(PageRequest.of(0,10));
        List<Board> resultBoards = boards.getContent();
        resultBoards.forEach(result -> {
            result.findLastComment();
        });
        //then
        assertThat(boards.getTotalElements()).isEqualTo(10);
        assertThat(boards.getContent().get(0).getId()).isEqualTo(1L);
    }
}
