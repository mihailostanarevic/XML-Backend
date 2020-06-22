package com.rentacar.carservice.controller;

import com.rentacar.carservice.dto.request.AddCommentRequest;
import com.rentacar.carservice.dto.response.CommentResponse;
import com.rentacar.carservice.service.ICommentService;
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
    public CommentResponse addComment(@RequestBody AddCommentRequest request) throws Exception{
        return _commentService.addComment(request);
    }

    @DeleteMapping("/{id}/comment")
    public void deleteComment(@PathVariable UUID id) throws Exception{
        _commentService.deleteComment(id);
    }

    @GetMapping("/{id}/comment")
    public CommentResponse getComment(@PathVariable UUID id) throws Exception{
        return _commentService.getComment(id);
    }

    @GetMapping
    public List<CommentResponse> getAllComments() throws Exception{
        return _commentService.getAllComments();
    }

    @GetMapping("/{id}/car")
    public List<CommentResponse> getAllCommentsByCar(@PathVariable UUID id) throws Exception{
        return _commentService.getAllCommentsByCar(id);
    }
}
