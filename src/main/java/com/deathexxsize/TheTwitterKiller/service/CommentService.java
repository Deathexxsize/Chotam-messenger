package com.deathexxsize.TheTwitterKiller.service;

import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CreateCommentRequest;
import com.deathexxsize.TheTwitterKiller.dto.commentDTOs.CreateCommentResponse;
import com.deathexxsize.TheTwitterKiller.exception.AccessDeniedException;
import com.deathexxsize.TheTwitterKiller.exception.CommentNotFoundException;
import com.deathexxsize.TheTwitterKiller.exception.TweetNotFoundException;
import com.deathexxsize.TheTwitterKiller.exception.UserNotFoundException;
import com.deathexxsize.TheTwitterKiller.mapper.commentMappers.CreateCommentMapper;
import com.deathexxsize.TheTwitterKiller.model.Comment;
import com.deathexxsize.TheTwitterKiller.model.Tweet;
import com.deathexxsize.TheTwitterKiller.model.User;
import com.deathexxsize.TheTwitterKiller.repository.CommentRepository;
import com.deathexxsize.TheTwitterKiller.repository.TweetRepository;
import com.deathexxsize.TheTwitterKiller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepo;
    private final TweetRepository tweetRepo;
    private final UserRepository userRepo;
    private final CreateCommentMapper commentMapper;

    public CreateCommentResponse createComment(int tweetId, int userId, CreateCommentRequest createRequest) {

        Tweet tweet = tweetRepo.getTweetById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("tweet not found"));
        User user = userRepo.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        Comment comment = new Comment();
        comment.setContent(createRequest.getContent());
        comment.setTweet(tweet);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        commentRepo.save(comment);

        return commentMapper.toCreateCommentResponse(comment);
    }

    public String removeComment(int commentId, int userId) {
        Comment comment = commentRepo.getCommentById(commentId)
                        .orElseThrow(() -> new CommentNotFoundException("comment not found"));
        User user = userRepo.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        if (!(comment.getUser().getId() == userId)) {
            throw new AccessDeniedException("You can delete only your own comments");
        }
        commentRepo.deleteById(commentId);
        return "comment deleted";
    }
}
