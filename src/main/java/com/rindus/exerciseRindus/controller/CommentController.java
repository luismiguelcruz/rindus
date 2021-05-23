package com.rindus.exerciseRindus.controller;

import com.rindus.exerciseRindus.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class CommentController {
    final static String resourceUrl = "https://jsonplaceholder.typicode.com/comments";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(path="comments/postId={postId}")
    public ResponseEntity<Object[]> getCommentsByPostInUrl(@PathVariable String postId) {
        return restTemplate.getForEntity(resourceUrl + "?postId=" + postId, Object[].class);
    }

    @GetMapping(path="comments/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable String commentId) {
        return restTemplate.getForEntity(resourceUrl + "/" + commentId, Comment.class);
    }


    @PostMapping("/comments")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        final HttpEntity<Comment> request = new HttpEntity<>(comment);

        return restTemplate.postForEntity(resourceUrl,
                request,
                Comment.class);
    }

    @PutMapping("/comments/{postId}")
    public ResponseEntity<Comment> updateComment(@PathVariable String postId, @RequestBody Comment comment) {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<Comment> requestUpdate = new HttpEntity<>(comment, headers);

        final ResponseEntity<Comment> response = restTemplate.exchange(
                resourceUrl + "/" + postId,
                HttpMethod.PUT,
                requestUpdate,
                Comment.class);

        return response;
    }

    @DeleteMapping("/comments/{postId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String postId) {
        final HttpHeaders headers = new HttpHeaders();

        return restTemplate.exchange(
                resourceUrl + "/" + postId,
                HttpMethod.DELETE,
                new HttpEntity<>(postId, headers),
                Void.class);
    }
}
