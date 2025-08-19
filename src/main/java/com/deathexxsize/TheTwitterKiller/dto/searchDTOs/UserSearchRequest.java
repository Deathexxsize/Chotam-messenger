package com.deathexxsize.TheTwitterKiller.dto.searchDTOs;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на поиск пользователя по никнейму")
public record UserSearchRequest (
        @Schema(description = "имя пользователя", example = "Clark")
        String username
) {}
