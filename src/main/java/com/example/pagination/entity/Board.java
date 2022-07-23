package com.example.pagination.entity;

import com.example.pagination.common.entity.BaseEntity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Getter
@Entity
@NoArgsConstructor
public class Board extends BaseEntity {

    @Column
    private String title;
    @Column
    private String writer;
    @Column
    private String content;
    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    private Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public void addComments(Comment comment) {
        comment.addBoard(this);
        this.comments.add(comment);
    }

    public Comment findLastComment() {
        return this.getComments()
            .stream()
            .sorted(Comparator.comparing(BaseEntity::getId).reversed())
            .findFirst()
            .orElse(null);
    }
}


