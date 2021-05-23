package com.rindus.exerciseRindus.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rindus.exerciseRindus.model.Comment;
import com.rindus.exerciseRindus.model.Post;

import java.util.Optional;

public interface ExtractDataService {
    public Optional<String> getPostJson(Post post);

    public Optional<String> getCommentJson(Comment comment);
}
