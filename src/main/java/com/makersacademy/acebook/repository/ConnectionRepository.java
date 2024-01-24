package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Connection;
import com.makersacademy.acebook.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface ConnectionRepository extends CrudRepository<Connection, Long> {
    Iterable<Connection>  findByUserId (Long userId);
}



