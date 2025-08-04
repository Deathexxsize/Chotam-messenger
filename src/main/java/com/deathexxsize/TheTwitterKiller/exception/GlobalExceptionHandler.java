package com.deathexxsize.TheTwitterKiller.exception;
import com.deathexxsize.TheTwitterKiller.dto.ErrorResponseDTO;
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
}
