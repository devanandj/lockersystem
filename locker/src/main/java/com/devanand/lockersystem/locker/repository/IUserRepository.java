package com.devanand.lockersystem.locker.repository;

import com.devanand.lockersystem.locker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {
}
