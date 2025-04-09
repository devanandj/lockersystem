package com.devanand.lockersystem.locker.service;

import com.devanand.lockersystem.locker.exceptions.DuplicateEmailException;
import com.devanand.lockersystem.locker.model.User;
import com.devanand.lockersystem.locker.repository.IUserRepository;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserService {




  private final IUserRepository userRepository;
  private final RedisTemplate<String, Object> redisTemplate;

 @Autowired
  public UserService(IUserRepository userRepository, RedisTemplate<String, Object> redisTemplate) {
    this.userRepository = userRepository;
   this.redisTemplate = redisTemplate;
 }

  public User createUser(User user) {
   try {

     return this.userRepository.insert(user);
   }
   catch (Exception ex ) {
      if (ex.getMessage().contains("duplicate key error") && ex.getMessage().contains("dup key: { email:")) {
        throw new DuplicateEmailException("Email already exists. Please use a different email.");
      }
      throw new RuntimeException("An unexpected error occurred while saving the user.");
    }
  }

 @Async
  public CompletableFuture<User> getUser(String id) {

    User cached = (User) this.redisTemplate.opsForValue().get("user_" + id);
    if(cached==null) {
      Optional<User> result = this.userRepository.findById(id);
      result.ifPresent(user -> this.redisTemplate.opsForValue().set("user_" + id, user, 1, TimeUnit.MINUTES));
      return CompletableFuture.completedFuture(result.orElse(null));
    }
     else {
       return CompletableFuture.completedFuture(cached);
    }
  }





}
