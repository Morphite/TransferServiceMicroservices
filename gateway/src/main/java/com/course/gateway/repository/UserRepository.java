package com.course.gateway.repository;

import com.course.gateway.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByLogin(String login);
}
