package com.deathexxsize.TheTwitterKiller.dto.commentDTOs;

import java.util.List;

public record AllCommentsResponse(
        List<CommentDTO> commentDTOList
) { }
