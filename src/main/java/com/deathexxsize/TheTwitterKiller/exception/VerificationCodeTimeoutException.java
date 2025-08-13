package com.deathexxsize.TheTwitterKiller.exception;

public class VerificationCodeTimeoutException extends RuntimeException {
  public VerificationCodeTimeoutException(String message) {
    super(message);
  }
}
