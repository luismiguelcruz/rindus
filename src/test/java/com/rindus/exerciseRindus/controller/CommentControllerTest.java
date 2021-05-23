package com.rindus.exerciseRindus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rindus.exerciseRindus.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentControllerTest {

    @Autowired
    private CommentController commentController;

    public void before() {
    }

    @Test
    public void testGetCommentsByPost() {
        final ResponseEntity<Object[]> responseEntity = commentController.getCommentsByPostInUrl("1");

        final List<Comment> comments = Arrays.stream(responseEntity.getBody())
                .map(object -> new ObjectMapper().convertValue(object, Comment.class))
                .collect(Collectors.toList());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(comments.isEmpty()).isEqualTo(false);
        assertThat(comments.size()).isEqualTo(5);
    }

    @Test
    public void testGetComment() {
        final ResponseEntity<Comment> responseEntity = commentController.getComment("1");

        final Comment comment = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(comment.getPostId()).isEqualTo(1);
    }

    @Test
    public void testAddComment() {
        final ResponseEntity<Comment> responseEntity = commentController.addComment(
                new Comment(500, 500, "Test", "Test", "Test"));

        final Comment comment = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(comment.getPostId()).isEqualTo(500);
    }

    @Test
    public void testUpdateComment() {
        final ResponseEntity<Comment> responseEntity = commentController.updateComment(
                "1", new Comment(500, 500, "Test", "Test", "Test"));

        final Comment comment = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(comment.getPostId()).isEqualTo(500);
        assertThat(comment.getId()).isEqualTo(1);
        assertThat(comment.getName()).isEqualTo("Test");
        assertThat(comment.getEmail()).isEqualTo("Test");
        assertThat(comment.getBody()).isEqualTo("Test");
    }

    @Test
    public void testDeleteComment() {
        final ResponseEntity<Void> responseEntity = commentController.deleteComment("1");

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
