package com.example.pagination.entity;

import com.example.pagination.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
    @Column
    private String comment;
    @Column
    private String writer;

    @Builder
    private Comment(String comment, String writer) {
        this.comment = comment;
        this.writer = writer;
    }

    public void addBoard(Board board) {
        this.board = board;
    }
}
