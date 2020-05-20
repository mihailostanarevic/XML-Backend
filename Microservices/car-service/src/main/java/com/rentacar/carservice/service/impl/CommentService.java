package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.dto.request.AddCommentRequest;
import com.rentacar.carservice.dto.response.CommentResponse;
import com.rentacar.carservice.repository.ICommentRepository;
import com.rentacar.carservice.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService implements ICommentService {

    private final ICommentRepository _commentRepository;

    public CommentService(ICommentRepository commentRepository) {
        _commentRepository = commentRepository;
    }

    @Override
    public CommentResponse addComment(AddCommentRequest request) throws Exception {
        return null;
    }

    @Override
    public void deleteComment(UUID id) throws Exception {

    }

    @Override
    public CommentResponse getComment(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<CommentResponse> getAllComments() throws Exception {
        return null;
    }

    @Override
    public List<CommentResponse> getAllCommentsByCar(UUID id) throws Exception {
        return null;
    }
}
