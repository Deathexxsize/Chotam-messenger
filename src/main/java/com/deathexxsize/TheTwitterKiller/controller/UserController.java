package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.FollowDTO;
import com.deathexxsize.TheTwitterKiller.dto.UserProfileResponse;
import com.deathexxsize.TheTwitterKiller.exception.InvalidVerificationCodeException;
import com.deathexxsize.TheTwitterKiller.model.UserPrincipal;
import com.deathexxsize.TheTwitterKiller.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Получить свой профиль",
            description = "Возвращает профиль текущего аутентифицированного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Профиль успешно получен"),
                    @ApiResponse(responseCode = "401", description = "Требуется аутентификация")
            }
    )
    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getMyProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.getUserProfile(userPrincipal.getId()));
    }

    @Operation(
            summary = "Получить профиль пользователя",
            description = "Возвращает профиль пользователя по его ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Профиль успешно получен"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
    )
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @Operation(
            summary = "Получить подписчиков пользователя",
            description = "Возвращает список подписчиков указанного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список подписчиков получен"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
    )
    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<FollowDTO>> getFollowers(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getFollowers(userId));
    }

    @Operation(
            summary = "Получить подписки пользователя",
            description = "Возвращает список пользователей, на которых подписан указанный пользователь",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список подписок получен"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
    )
    @GetMapping("/{userId}/following")
    public ResponseEntity<List<FollowDTO>> getFollowing(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getFollowing(userId));
    }

    @Operation(
            summary = "Редактировать данные профиля",
            description = "Обновляет данные текущего пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные успешно обновлены"),
                    @ApiResponse(responseCode = "400", description = "Неверные данные для обновления"),
                    @ApiResponse(responseCode = "401", description = "Требуется аутентификация")
            }
    )
    @PatchMapping("/edit")
    public ResponseEntity<String> editData(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody Map<String, Object> edits
    ) {
        return ResponseEntity.ok(userService.editUserData(userPrincipal.getId(), edits));
    }

    @Operation(
            summary = "Удалить профиль",
            description = "Удаляет профиль текущего пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Профиль успешно удален"),
                    @ApiResponse(responseCode = "401", description = "Требуется аутентификация")
            }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(userService.deleteProfile(userPrincipal.getId()));
    }
}
