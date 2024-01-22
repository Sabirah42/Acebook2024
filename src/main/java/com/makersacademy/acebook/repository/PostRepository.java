package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
        Iterable<Post>  findByUserId (Long userId);
        Optional<Post> findById (Long id);
}
