package com.deathexxsize.TheTwitterKiller.exception;
import com.deathexxsize.TheTwitterKiller.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ? Аккаунт деактивирован
    @ExceptionHandler(AccountDeactivatedException.class)
    public ResponseEntity<ErrorResponse> handleDeactivated(
            AccountDeactivatedException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                403,
                "Account is deactivated",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    // ? Пользователь не найден
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(
            UserNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
          404,
          "not found",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // ? Лайк уже поставлен
    @ExceptionHandler(AlreadyLikedException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(
            AlreadyLikedException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                409,
                "You have already liked this post.",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // ? Лайк не найден
    @ExceptionHandler(LikeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(
            LikeNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                "You've already taken a like.",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // ? Твит не найден
    @ExceptionHandler(TweetNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(
            TweetNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                "tweet not found",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // ? Невозможно удалить чужой коммент
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(
            AccessDeniedException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                403,
                "cannot_delete_comment",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // ? Комментарий не найден
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(
            CommentNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                "comment not found",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // ? Не верный код код верификации
    @ExceptionHandler(InvalidVerificationCodeException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(
            InvalidVerificationCodeException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                400,
                "invalid_verification_code",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // ? Пароли не совподают
    @ExceptionHandler(PasswordsDontMatchException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(
            PasswordsDontMatchException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                400,
                "passwords_mismatch",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // ? Время на верификацию прошла
    @ExceptionHandler(VerificationCodeTimeoutException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(
            VerificationCodeTimeoutException exception,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                410,
                "verification_code_expired",
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
