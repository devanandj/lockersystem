package com.devanand.lockersystem.locker.exceptions;

public class DuplicateEmailException extends RuntimeException{

  public DuplicateEmailException(String message) {
    super(message);
  }
}
