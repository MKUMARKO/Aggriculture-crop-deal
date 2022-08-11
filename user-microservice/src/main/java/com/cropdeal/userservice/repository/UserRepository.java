package com.cropdeal.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cropdeal.userservice.models.User;


public interface UserRepository extends MongoRepository<User, String> {

	boolean existsByUserName(String userName);

	User findByUserName(String username);


} 