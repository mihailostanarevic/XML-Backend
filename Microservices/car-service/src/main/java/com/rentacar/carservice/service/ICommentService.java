package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.request.AddCommentRequest;
import com.rentacar.carservice.dto.request.ApproveOrDenyCommentRequest;
import com.rentacar.carservice.dto.response.CommentResponse;

import java.util.List;
import java.util.UUID;

public interface ICommentService {

    CommentResponse addComment(AddCommentRequest request) throws Exception;

    void approveComment(ApproveOrDenyCommentRequest request) throws Exception;

    void denyComment(ApproveOrDenyCommentRequest request) throws Exception;

    List<CommentResponse> getAllCommentsByAd(UUID id) throws Exception;

    List<CommentResponse> getAllPendingComments() throws Exception;
}
