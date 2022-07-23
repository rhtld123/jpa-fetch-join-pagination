package com.example.pagination.repository;

import com.example.pagination.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(
        value =
            "SELECT distinct b "
                + "FROM Board b JOIN b.comments c "
                + "WHERE b.deletedAt IS NULL "
                + "ORDER BY b.id asc",
        countQuery = "SELECT count(b)"
            + "FROM Board b JOIN b.comments "
            + "WHERE b.deletedAt IS NULL "
    )
    Page<Board> findAllByBoard(Pageable pageable);
}

