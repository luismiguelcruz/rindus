package com.rindus.exerciseRindus.controller;

import com.rindus.exerciseRindus.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class PostController {
    final static String resourceUrl = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(path="/posts")
    public ResponseEntity<Object[]> getPosts() {
        return restTemplate.getForEntity(resourceUrl, Object[].class);
    }

    @GetMapping(path="/posts/{userId}")
    @ResponseBody
    public ResponseEntity<Post> getPost(@PathVariable String userId) {
        return restTemplate.getForEntity(resourceUrl + "/" + userId, Post.class);
    }

    @GetMapping(path="/posts/{userId}/comments")
    public ResponseEntity<Object[]> getCommentsByPost(@PathVariable String userId) {
        return restTemplate.getForEntity(resourceUrl + "/" + userId + "/comments", Object[].class);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        final HttpEntity<Post> request = new HttpEntity<>(post);

        return restTemplate.postForEntity(resourceUrl ,
                request,
                Post.class);
    }

    @PutMapping("/posts/{userId}")
    public ResponseEntity<Post> updatePost(@PathVariable String userId, @RequestBody Post post) {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<Post> requestUpdate = new HttpEntity<>(post, headers);

        final ResponseEntity<Post> response = restTemplate.exchange(
                resourceUrl + "/" + userId,
                HttpMethod.PUT,
                requestUpdate,
                Post.class);

        return response;
    }

    @PatchMapping("/posts/{userId}")
    public ResponseEntity<Post> patchPost(@PathVariable String userId, @RequestBody String updates) {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<String> requestUpdate = new HttpEntity<>(updates, headers);

        final ResponseEntity<Post> response = restTemplate.exchange(
                resourceUrl + "/" + userId,
                HttpMethod.PATCH,
                requestUpdate,
                Post.class);

        return response;
    }

    @DeleteMapping("/posts/{userId}")
    public ResponseEntity<Void> deletePost(@PathVariable String userId) {
        final HttpHeaders headers = new HttpHeaders();

        return restTemplate.exchange(
                resourceUrl + "/" + userId,
                HttpMethod.DELETE,
                new HttpEntity<>(userId, headers),
                Void.class);
    }
}
