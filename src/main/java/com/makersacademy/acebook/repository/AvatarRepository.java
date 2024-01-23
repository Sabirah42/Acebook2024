package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Avatar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    public Avatar findByFileName(String fileName);
}
