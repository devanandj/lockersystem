package com.devanand.lockersystem.locker.controller;

import com.devanand.lockersystem.locker.exceptions.DuplicateEmailException;
import com.devanand.lockersystem.locker.model.User;
import com.devanand.lockersystem.locker.service.UserService;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {


  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }


  @PostMapping("/user")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return new ResponseEntity<>(this.userService.createUser(user),HttpStatus.CREATED);
  }


  @GetMapping("/user/{id}")
  public CompletableFuture<ResponseEntity<User>> getUser(@PathVariable String id) {
    return this.userService.getUser(id).thenApply(res -> {
      return new ResponseEntity<>(res, HttpStatus.OK);
    });
  }


  @ExceptionHandler(DuplicateEmailException.class)
  public ResponseEntity<String> handleDuplicateEmail(DuplicateEmailException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}

