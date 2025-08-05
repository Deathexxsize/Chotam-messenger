package com.deathexxsize.TheTwitterKiller.exception;
import com.deathexxsize.TheTwitterKiller.dto.ErrorResponseDTO;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountDeactivatedException.class)
    public ResponseEntity<ErrorResponseDTO> handleDeactivated(
            AccountDeactivatedException exception,
            HttpServletRequest request
    ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                403,
                "Account is deactivated",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponseDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerNotFound(
            UserNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
          404,
          "not found",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(AlreadyLikedException.class)
    public ResponseEntity<ErrorResponseDTO> handlerNotFound(
            AlreadyLikedException exception,
            HttpServletRequest request
    ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                409,
                "You have already liked this post.",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(LikeNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerNotFound(
            LikeNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                404,
                "You've already taken a like.",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }

    @ExceptionHandler(TweetNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerNotFound(
            TweetNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                404,
                "tweet not found",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }
}
