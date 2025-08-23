package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.ErrorResponse;
import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CommentDTO;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.AllTweetsResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.CreateTweetRequest;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.CreateTweetResponse;
import com.deathexxsize.TheTwitterKiller.dto.tweetDTOs.TweetFeedResponse;
import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.TweetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tweets")
@Tag(name = "Tweet-controller", description = "методы управления комментариями")
public class TweetController {
    private final TweetService tweetService;

    @Operation(
            summary = "Создание твита",
            description = "Создает новый твит от имени аутентифицированного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Твит успешно создан",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateTweetResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Аккаунт деактивирован",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @PostMapping()
    public ResponseEntity<CreateTweetResponse> createTweet(
            @RequestBody CreateTweetRequest createTweetRequest,
            @AuthenticationPrincipal UserPrincipal userPrincipal
            ) {
        return ResponseEntity.ok(tweetService.createPost(userPrincipal.getId(), createTweetRequest.title(), createTweetRequest.content()));
    }

    @Operation(
            summary = "Получение всех твитов",
            description = "Получает все твиты пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Твиты успешно получены",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AllTweetsResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Аккаунт деактивирован",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/{userId}/tweets")
    public ResponseEntity< List<AllTweetsResponse> > getAllTweets(@PathVariable int userId) {
        return ResponseEntity.ok(tweetService.getAllUserTweets(userId));
    }

    @Operation(
            summary = "Получить твит",
            description = "Получить твит пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Твит успешно получены",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AllTweetsResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Аккаунт деактивирован",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Твит не найден",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/{userId}/{tweetId}")
    public ResponseEntity< AllTweetsResponse > getTweet(@PathVariable int userId, @PathVariable int tweetId) {
        return ResponseEntity.ok(tweetService.getUserTweet(userId, tweetId));
    }

    @Operation(
            summary = "Получить дневные твиты",
            description = "Получить твиты подписок за последние 24 часа",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Твиты за 24 часа успешно получены",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TweetFeedResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Аккаунт деактивирован",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Твит не найден",
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
    @GetMapping("/feed")
    public ResponseEntity< List<TweetFeedResponse> > getNewsFeed(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<TweetFeedResponse> feed = tweetService.getDailyNews(userPrincipal.getUsername());
        return ResponseEntity.ok(feed);
    }

    @Operation(
            summary = "Удаляет твит",
            description = "Удалить твит текущего пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Твит успешно удален",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class, example = "Tweet successfully created")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Аккаунт деактивирован",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Твит не найден",
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
    @DeleteMapping("/{tweetId}")
    public ResponseEntity< String  > deleteTweet(@PathVariable int tweetId , @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(tweetService.deleteTweet(tweetId, userPrincipal.getId()));
    }

    @Operation(
            summary = "Получает все комментарии ",
            description = "Получить все комменты твита",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "комментарии успешно получены",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CommentDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Аккаунт деактивирован",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Твит не найден",
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
    @GetMapping("/{tweetId}/comments")
    public ResponseEntity< List<CommentDTO> > getAllComments(@PathVariable int tweetId) {
        return ResponseEntity.ok(tweetService.getComments(tweetId));
    }
}
