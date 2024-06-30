package ru.gb.homework.hw_4;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        DatabaseController controller = new DatabaseController();

        Post post = new Post();
        post.setId(3L);
        post.setTitle("Title");

        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            controller.insertNewPost(sessionFactory, 1L);
            controller.insertNewPost(sessionFactory, 2L, "New post");
            controller.insertNewPost(sessionFactory, post);
            controller.insertNewPost(sessionFactory, 4L);
            controller.deletePostById(sessionFactory, 1L);
            controller.insertNewPostComment(sessionFactory, 1L, 4L);
            controller.insertNewPostComment(sessionFactory, 2L, "New comment", 4L);
            controller.insertNewPostComment(sessionFactory, 3L, "Comment", 2L);
            controller.getPostById(sessionFactory, 4L);
            controller.deletePostCommentById(sessionFactory, 3L);
            controller.getPostCommentById(sessionFactory, 2L);
        }
    }
}
