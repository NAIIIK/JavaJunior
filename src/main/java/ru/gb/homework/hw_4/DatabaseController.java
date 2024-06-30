package ru.gb.homework.hw_4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;

public class DatabaseController {

    public DatabaseController() {
    }

    public void insertNewPost(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()) {
            Post newPost = new Post();
            newPost.setId(id);
            newPost.setTimestamp(new Timestamp(System.currentTimeMillis()));

            Transaction transaction = session.beginTransaction();
            session.persist(newPost);
            transaction.commit();
        }
    }

    public void insertNewPost(SessionFactory sessionFactory, Long id, String title) {
        try (Session session = sessionFactory.openSession()) {
            Post newPost = new Post();
            newPost.setId(id);
            newPost.setTitle(title);
            newPost.setTimestamp(new Timestamp(System.currentTimeMillis()));

            Transaction transaction = session.beginTransaction();
            session.persist(newPost);
            transaction.commit();
        }
    }

    public void insertNewPost(SessionFactory sessionFactory, Post post) {
        try (Session session = sessionFactory.openSession()) {
            Post newPost = new Post();
            newPost.setId(post.getId());
            if (post.getTitle() != null) {
                newPost.setTitle(post.getTitle());
            }
            newPost.setTimestamp(new Timestamp(System.currentTimeMillis()));

            Transaction transaction = session.beginTransaction();
            session.persist(newPost);
            transaction.commit();
        }
    }

    public void getPostById(SessionFactory sessionFactory, Long id) {
        try(Session session = sessionFactory.openSession()) {
            Post post = session.find(Post.class, id);

            System.out.println(post);
        }
    }

    public void deletePostById(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()) {
            Post toDelete = session.find(Post.class, id);

            Transaction transaction = session.beginTransaction();
            session.remove(toDelete);
            transaction.commit();
        }
    }

    public void insertNewPostComment(SessionFactory sessionFactory, Long id, Long post_id) {
        try (Session session = sessionFactory.openSession()) {
            PostComment newPostComment = new PostComment();
            newPostComment.setId(id);
            newPostComment.setPost(session.find(Post.class, post_id));
            newPostComment.setTimestamp(new Timestamp(System.currentTimeMillis()));

            Transaction transaction = session.beginTransaction();
            session.persist(newPostComment);
            transaction.commit();
        }
    }

    public void insertNewPostComment(SessionFactory sessionFactory, Long id, String text, Long post_id) {
        try (Session session = sessionFactory.openSession()) {
            PostComment newPostComment = new PostComment();
            newPostComment.setId(id);
            newPostComment.setText(text);
            newPostComment.setPost(session.find(Post.class, post_id));
            newPostComment.setTimestamp(new Timestamp(System.currentTimeMillis()));

            Transaction transaction = session.beginTransaction();
            session.persist(newPostComment);
            transaction.commit();
        }
    }

    public void insertNewPostComment(SessionFactory sessionFactory, PostComment postComment) {
        try (Session session = sessionFactory.openSession()) {
            PostComment newPostComment = new PostComment();
            newPostComment.setId(postComment.getId());
            newPostComment.setPost(session.find(Post.class, postComment.getPost().getId()));
            newPostComment.setTimestamp(new Timestamp(System.currentTimeMillis()));

            Transaction transaction = session.beginTransaction();
            session.persist(newPostComment);
            transaction.commit();
        }
    }

    public void getPostCommentById(SessionFactory sessionFactory, Long id) {
        try(Session session = sessionFactory.openSession()) {
            PostComment postComment = session.find(PostComment.class, id);

            System.out.println(postComment);
        }
    }

    public void deletePostCommentById(SessionFactory sessionFactory, Long id) {
        try (Session session = sessionFactory.openSession()) {
            PostComment toDelete = session.find(PostComment.class, id);

            Transaction transaction = session.beginTransaction();
            session.remove(toDelete);
            transaction.commit();
        }
    }
}
