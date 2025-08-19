package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.LikeService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/likes")
@Tag(name = "Like-controller", description = "методы управления лайками")
public class LikeController {
    private final LikeService likeService;

    @Operation(
            summary = "Поставить лайк ",
            description = "Текущий пользователь ставит лайк на твит другого автора",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Лайк успешно поставлен",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class, example = "The like was successfully delivered")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Лайк уже поставлен",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @PostMapping("/{tweetId}")
    public ResponseEntity< String > putLike(
            @PathVariable int tweetId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(likeService.putLike(tweetId, userPrincipal.getId()));
    }

    @Operation(
            summary = "Убрать лайк ",
            description = "Текущий пользователь убирает лайк от твита другого автора",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Лайк успешно убран",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class, example = "The like was successfully delivered")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Лайк не найден",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{tweetId}")
    public ResponseEntity< String > removeLike(
            @PathVariable int tweetId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(likeService.removeLike(tweetId, userPrincipal.getId()));
    }
}
