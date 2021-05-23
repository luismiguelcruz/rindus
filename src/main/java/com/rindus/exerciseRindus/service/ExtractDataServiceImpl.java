package com.rindus.exerciseRindus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rindus.exerciseRindus.model.Comment;
import com.rindus.exerciseRindus.model.Post;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExtractDataServiceImpl implements ExtractDataService {
    @Override
    public Optional<String> getPostJson(Post post) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Optional.of(objectMapper.writeValueAsString(post));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<String> getCommentJson(Comment comment) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Optional.of(objectMapper.writeValueAsString(comment));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
