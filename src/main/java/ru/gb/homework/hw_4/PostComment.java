package ru.gb.homework.hw_4;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "post_comments")
public class PostComment {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "created_at")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public PostComment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
