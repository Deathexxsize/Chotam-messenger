package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.ErrorResponse;
import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CreateCommentRequest;
import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CreateCommentResponse;
import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
@Tag(name = "Comment-controller", description = "методы управления комментариями")
public class CommentController {
    private final CommentService commentService;


    @Operation(
            summary = "Создание комментария",
            description = "Создает новый комментарий к твиту и возвращает информацию о созданном комментарии",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Comment successfully created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateCommentResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Tweet not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @PostMapping("/{tweetId}")
    public ResponseEntity<CreateCommentResponse> writeComment(
            @PathVariable int tweetId,
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody CreateCommentRequest createRequest
            ) {
        return ResponseEntity.ok(commentService.createComment(tweetId, userPrincipal.getId(), createRequest));
    }

    @Operation(
            summary = "Удаление комментария",
            description = "Удаляет комментарий по его идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарий успешно удален",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class)
                            )
                    )
            }

    )
    @DeleteMapping("/{commentId}")
    public ResponseEntity< String > deleteComment(
            @PathVariable int commentId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(commentService.removeComment(commentId, userPrincipal.getId()));
    }
}
