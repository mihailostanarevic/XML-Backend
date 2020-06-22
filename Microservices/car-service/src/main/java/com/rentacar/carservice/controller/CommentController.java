package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.AddCommentRequest;
import com.rentacar.carservice.dto.request.ApproveOrDenyCommentRequest;
import com.rentacar.carservice.dto.response.CommentResponse;
import com.rentacar.carservice.service.ICommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final ICommentService _commentService;

    public CommentController(ICommentService commentService) {
        _commentService = commentService;
    }

    @PostMapping
    public CommentResponse addComment(@RequestBody AddCommentRequest request) throws Exception{
        return _commentService.addComment(request);
    }

    @PutMapping("/approve-comment")
    void approveComment(@RequestBody ApproveOrDenyCommentRequest request) throws Exception {
        _commentService.approveComment(request);
    }

    @PutMapping("/deny-comment")
    void denyComment(@RequestBody ApproveOrDenyCommentRequest request) throws Exception {
        _commentService.denyComment(request);
    }

    @GetMapping("/{id}/ad")
    List<CommentResponse> getAllCommentsByAd(@PathVariable UUID id) throws Exception {
        return _commentService.getAllCommentsByAd(id);
    }

    @GetMapping("/pending")
    List<CommentResponse> getAllPendingComments() throws Exception {
        return _commentService.getAllPendingComments();
    }
}
