package com.rindus.exerciseRindus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.rindus.exerciseRindus.model.Comment;
import com.rindus.exerciseRindus.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostControllerTest {

    @Autowired
    private PostController postController;

    public void before() {
    }

    @Test
    public void testGetPosts() {
        final ResponseEntity<Object[]> responseEntity = postController.getPosts();

        final List<Post> posts = Arrays.stream(responseEntity.getBody())
                .map(object -> new ObjectMapper().convertValue(object, Post.class))
                .collect(Collectors.toList());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(posts.isEmpty()).isEqualTo(false);
        assertThat(posts.size()).isEqualTo(100);
    }

    @Test
    public void testGetPost() {
        final ResponseEntity<Post> responseEntity = postController.getPost("1");

        final Post post = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(post.getId()).isEqualTo(1);
    }

    @Test
    public void testGetCommentsByUser() {
        final ResponseEntity<Object[]> responseEntity = postController.getCommentsByPost("1");

        final List<Comment> comments = Arrays.stream(responseEntity.getBody())
                .map(object -> new ObjectMapper().convertValue(object, Comment.class))
                .collect(Collectors.toList());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(comments.isEmpty()).isEqualTo(false);
        assertThat(comments.size()).isEqualTo(5);
    }

    @Test
    public void testAddPost() {
        final ResponseEntity<Post> responseEntity = postController.addPost(new Post(500, 500, "Test", "Test"));

        final Post post = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(post.getUserId()).isEqualTo(500);
    }

    @Test
    public void testUpdatePost() {
        final Post updatePost = new Post(500, 500, "Test", "Test");
        final ResponseEntity<Post> responseEntity = postController.updatePost("1", updatePost);

        final Post post = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(post.getUserId()).isEqualTo(500);
        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("Test");
        assertThat(post.getBody()).isEqualTo("Test");
    }

    @Test
    public void testPatchPost() {
        final Map<String, Object> updatePost = ImmutableMap.of("title", "Test", "body", "test");

        final String testValue = "{ \"title\", \"Test\"}";
        final ResponseEntity<Post> responseEntity = postController.patchPost("1", testValue);
        final ResponseEntity<Post> responseGetEntity = postController.getPost("1");

        final Post post = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testDeletePost() {
        final ResponseEntity<Void> responseEntity = postController.deletePost("1");

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
