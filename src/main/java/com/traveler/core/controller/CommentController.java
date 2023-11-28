package com.traveler.core.controller;

import com.traveler.core.dto.CommentRequestDTO;
import com.traveler.core.dto.CommentResponseDTO;
import com.traveler.core.repository.CommentRepository;
import com.traveler.core.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    public CommentController(CommentRepository commentRepository, CommentService commentService) {
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    @GetMapping(value = "/board/{boardId}/comment")
    public Page<CommentResponseDTO> boardList(@PageableDefault(page = 1) Pageable pageable, @PathVariable int boardId){
        Page<CommentResponseDTO> commentResponseDTOList = commentService.paging(pageable, boardId);
        return commentResponseDTOList;
    }
    @PostMapping(value = "/board/{boardId}/comment/save")
    public void saveComment(@PathVariable int boardId, @RequestBody CommentRequestDTO commentRequestDTO){
        commentService.saveComment(boardId, commentRequestDTO);
    }
    @DeleteMapping(value = "/board/comment/delete/{commentId}")
    public void deleteComment(@PathVariable int commentId){
        commentService.deleteComment(commentId);}
}