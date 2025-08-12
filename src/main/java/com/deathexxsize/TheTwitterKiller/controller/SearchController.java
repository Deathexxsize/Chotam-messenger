package com.deathexxsize.TheTwitterKiller.controller;

import com.deathexxsize.TheTwitterKiller.dto.searchDTOs.UserSearchRequest;
import com.deathexxsize.TheTwitterKiller.dto.searchDTOs.UserSearchResponse;
import com.deathexxsize.TheTwitterKiller.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/users")
    public ResponseEntity< List<UserSearchResponse> > userSearch(@RequestBody UserSearchRequest userSearchRequest) {
        return ResponseEntity.ok( searchService.userSearch(userSearchRequest));
    }

}

