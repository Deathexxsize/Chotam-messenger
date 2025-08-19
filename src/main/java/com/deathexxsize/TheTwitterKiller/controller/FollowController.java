package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
@Tag(name = "Follow-controller", description = "методы управления подпиской / отпиской")
public class FollowController {
    private final FollowService followService;

    @Operation(
            summary = "Подписка на пользователя",
            description = "Подписывает текущего пользователя на указанного автора",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная подписка",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class, example = "Successfully subscribed to user")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Неверный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @PostMapping("/{authorId}")
    public ResponseEntity< String > subscribe(
            @PathVariable int authorId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
            ) {
        return ResponseEntity.ok(followService.subscribe(authorId, userPrincipal.getId()));
    }

    @Operation(
            summary = "Отписка на пользователя",
            description = "Отписывает текущего пользователя от указанного автора",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная подписка",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class, example = "Successfully subscribed to user")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Неверный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{authorId}")
    public ResponseEntity< ? > unsubscribe(
            @PathVariable int authorId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
            ) {
        return ResponseEntity.ok(followService.unsubscribe(authorId, userPrincipal.getId()));
    }
}
