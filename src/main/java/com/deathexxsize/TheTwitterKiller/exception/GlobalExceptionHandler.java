package com.deathexxsize.TheTwitterKiller.exception;
import com.deathexxsize.TheTwitterKiller.dto.ErrorResponseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountDeactivatedException.class)
    public ResponseEntity<ErrorResponseResponse> handleDeactivated(
            AccountDeactivatedException exception,
            HttpServletRequest request
    ) {
        ErrorResponseResponse errorResponseResponse = new ErrorResponseResponse(
                403,
                "Account is deactivated",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponseResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseResponse> handlerNotFound(
            UserNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponseResponse errorResponseResponse = new ErrorResponseResponse(
          404,
          "not found",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseResponse);
    }

    @ExceptionHandler(AlreadyLikedException.class)
    public ResponseEntity<ErrorResponseResponse> handlerNotFound(
            AlreadyLikedException exception,
            HttpServletRequest request
    ) {
        ErrorResponseResponse errorResponseResponse = new ErrorResponseResponse(
                409,
                "You have already liked this post.",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseResponse);
    }

    @ExceptionHandler(LikeNotFoundException.class)
    public ResponseEntity<ErrorResponseResponse> handlerNotFound(
            LikeNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponseResponse errorResponseResponse = new ErrorResponseResponse(
                404,
                "You've already taken a like.",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseResponse);
    }

    @ExceptionHandler(TweetNotFoundException.class)
    public ResponseEntity<ErrorResponseResponse> handlerNotFound(
            TweetNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponseResponse errorResponseResponse = new ErrorResponseResponse(
                404,
                "tweet not found",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseResponse);
    }
}
