package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.AddCommentRequest;
import com.rentacar.carservice.dto.response.CommentResponse;

import java.util.List;
import java.util.UUID;

public interface ICommentService {

    CommentResponse addComment(AddCommentRequest request) throws Exception;

    void deleteComment(UUID id) throws Exception;

    CommentResponse getComment(UUID id) throws Exception;

    List<CommentResponse> getAllComments() throws Exception;

    List<CommentResponse> getAllCommentsByCar(UUID id) throws Exception;
}
