package com.course.gateway.repository;

import model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByLogin(String login);
}
