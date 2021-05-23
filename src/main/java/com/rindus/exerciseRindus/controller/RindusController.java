package com.rindus.exerciseRindus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rindus.exerciseRindus.model.Post;
import com.rindus.exerciseRindus.service.ExtractDataService;
import com.rindus.exerciseRindus.service.WriteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RindusController {
    @Autowired
    private PostController postController;

    @Autowired
    private ExtractDataService extractDataService;

    @Autowired
    private WriteFileService writeFileService;

    @GetMapping(path="/extractData")
    public ResponseEntity<String> extractData() {
        final ResponseEntity<Object[]> responseEntity = postController.getPosts();

        final List<Post> posts = Arrays.stream(responseEntity.getBody())
                .map(object -> new ObjectMapper().convertValue(object, Post.class))
                .collect(Collectors.toList());

        final HttpStatus httpStatus = writeFileService.writeFile(posts);

        return ResponseEntity.status(httpStatus)
                .body(posts.toString());
    }
}
