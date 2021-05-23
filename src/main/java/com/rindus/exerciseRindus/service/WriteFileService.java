package com.rindus.exerciseRindus.service;

import com.rindus.exerciseRindus.model.Post;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface WriteFileService {
    public HttpStatus writeFile(List<Post> objects);
}
