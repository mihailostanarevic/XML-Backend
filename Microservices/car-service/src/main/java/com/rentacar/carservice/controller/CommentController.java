package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.AddCommentRequest;
import com.rentacar.carservice.dto.request.ApproveOrDenyCommentRequest;
import com.rentacar.carservice.dto.response.CommentResponse;
import com.rentacar.carservice.service.ICommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final ICommentService _commentService;

    public CommentController(ICommentService commentService) {
        _commentService = commentService;
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('POST_COMMENT')")
    public CommentResponse addComment(@RequestBody AddCommentRequest request) throws Exception{
        return _commentService.addComment(request);
    }

    @PutMapping("/approve-comment")
   // @PreAuthorize("hasAuthority('APPROVE_COMMENT')")
    void approveComment(@RequestBody ApproveOrDenyCommentRequest request) throws Exception {
        _commentService.approveComment(request);
    }

    @PutMapping("/deny-comment")
    //@PreAuthorize("hasAuthority('DENY_COMMENT')")
    void denyComment(@RequestBody ApproveOrDenyCommentRequest request) throws Exception {
        _commentService.denyComment(request);
    }

    @GetMapping("/{id}/ad")
    //@PreAuthorize("hasAuthority('VIEW_AD')")
    List<CommentResponse> getAllCommentsByAd(@PathVariable UUID id) throws Exception {
        return _commentService.getAllCommentsByAd(id);
    }

    @GetMapping("/pending")
    //@PreAuthorize("hasAuthority('VIEW_AD')")
    List<CommentResponse> getAllPendingComments() throws Exception {
        return _commentService.getAllPendingComments();
    }
}
