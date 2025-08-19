package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.searchDTOs.UserSearchRequest;
import com.deathexxsize.TheTwitterKiller.dto.searchDTOs.UserSearchResponse;
import com.deathexxsize.TheTwitterKiller.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
@Tag(name = "Search-controller", description = "методы управления поиском")
public class SearchController {

    private final SearchService searchService;

    @Operation(
            summary = "Поиск пользователя",
            description = "Пользователь ищет другого автора по никнейму",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь найден",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserSearchResponse.class)
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
    @GetMapping("/users")
    public ResponseEntity< List<UserSearchResponse> > userSearch(@RequestBody UserSearchRequest userSearchRequest) {
        return ResponseEntity.ok( searchService.userSearch(userSearchRequest));
    }

}

