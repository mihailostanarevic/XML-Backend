package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.client.AuthClient;
import com.rentacar.carservice.client.RentClient;
import com.rentacar.carservice.dto.feignClient.AgentDTO;
import com.rentacar.carservice.dto.feignClient.ReqDTO;
import com.rentacar.carservice.dto.feignClient.RequestAdDTO;
import com.rentacar.carservice.dto.feignClient.SimpleUserDTO;
import com.rentacar.carservice.dto.request.AddCommentRequest;
import com.rentacar.carservice.dto.request.ApproveOrDenyCommentRequest;
import com.rentacar.carservice.dto.request.RequestDTO;
import com.rentacar.carservice.dto.response.CommentResponse;
import com.rentacar.carservice.entity.Ad;
import com.rentacar.carservice.entity.Comment;
import com.rentacar.carservice.repository.IAdRepository;
import com.rentacar.carservice.repository.ICommentRepository;
import com.rentacar.carservice.service.ICommentService;
import com.rentacar.carservice.util.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    private final AuthClient _authClient;

    private final RentClient _rentClient;

    private final ICommentRepository _commentRepository;

    private final IAdRepository _adRepository;

    public CommentService(AuthClient authClient, RentClient rentClient, ICommentRepository commentRepository, IAdRepository adRepository) {
        _authClient = authClient;
        _rentClient = rentClient;
        _commentRepository = commentRepository;
        _adRepository = adRepository;
    }

    @Override
    public CommentResponse addComment(AddCommentRequest request) throws Exception {

        SimpleUserDTO simpleUser = _authClient.getSimpleUser(request.getUserId());
        Ad ad = _adRepository.findOneById(request.getAdId());

        if(simpleUser != null){
            List<ReqDTO> simpleUsersRequests = _rentClient.getAllPaidRequestsByCustomer(simpleUser.getId());
            if(simpleUsersRequests.isEmpty()){
                throw new Exception("You cannot comment this ad because you did not have any paid rents.");
            }
            ReqDTO ratingRequest = null; //flag
            for(ReqDTO r: simpleUsersRequests){
                List<RequestAdDTO> requestAdDTOS = _rentClient.getAllRequestAdsByRequest(r.getId());
                boolean flag = false;
                for(RequestAdDTO ra: requestAdDTOS){
                    if(!ra.getAd_id().equals(ad.getId())){
                        continue;
                    }
                    LocalDate currentDate = LocalDate.now();
                    if(ra.getReturnDate().isAfter(currentDate)){
                        continue;
                    }
                    ratingRequest = r;
                    flag = true;
                    break;
                }
                if(flag){
                    break;
                }
            }
            if(ratingRequest == null){
                throw new Exception("You cannot comment this ad.");
            }

            Comment comment = new Comment();
            comment.setComment(request.getComment());
            comment.setSimpleUser(simpleUser.getId());
            comment.setAd(ad);
            comment.setAgent(ad.getAgent());
            comment.setStatus(RequestStatus.PENDING);
            Comment savedComment = _commentRepository.save(comment);
            ad.getComments().add(savedComment);
            _adRepository.save(ad);

            return mapCommentToCommentResponse(savedComment);
        } else {
            AgentDTO agent = _authClient.getAgent(request.getUserId());
            if(!ad.getAgent().equals(agent.getAgentID())) {
                throw new Exception("You can only comment your ads.");
            }

            Comment comment = new Comment();
            comment.setComment(request.getComment());
            comment.setAgent(agent.getAgentID());
            comment.setAd(ad);
            comment.setStatus(RequestStatus.PENDING);
            Comment savedComment = _commentRepository.save(comment);
            ad.getComments().add(savedComment);
            _adRepository.save(ad);

            return mapCommentToCommentResponse(savedComment);
        }
    }

    @Override
    public void approveComment(ApproveOrDenyCommentRequest request) throws Exception {
        Comment comment = _commentRepository.findOneById(request.getCommentId());
        comment.setStatus(RequestStatus.APPROVED);
        _commentRepository.save(comment);
    }

    @Override
    public void denyComment(ApproveOrDenyCommentRequest request) throws Exception {
        Comment comment = _commentRepository.findOneById(request.getCommentId());
        comment.setStatus(RequestStatus.DENIED);
        _commentRepository.save(comment);
    }

    @Override
    public List<CommentResponse> getAllCommentsByAd(UUID id) throws Exception {
        List<Comment> comments = _commentRepository.findAllByAd_IdAndStatus(id, RequestStatus.APPROVED);
        return comments.stream()
                .map(comment -> mapCommentToCommentResponse(comment))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getAllPendingComments() throws Exception {
        List<Comment> comments = _commentRepository.findAllByStatus(RequestStatus.PENDING);
        return comments.stream()
                .map(comment -> mapCommentToCommentResponse(comment))
                .collect(Collectors.toList());
    }

    private CommentResponse mapCommentToCommentResponse(Comment comment){
        CommentResponse response = new CommentResponse();
        AgentDTO agentDTO = _authClient.getAgent(comment.getAgent());
        response.setAgentName(agentDTO.getAgentName());
        response.setComment(comment.getComment());
        response.setCommentId(comment.getSimpleUser());
        SimpleUserDTO simpleUserDTO = _authClient.getSimpleUser(comment.getSimpleUser());
        response.setCustomerFirstName(simpleUserDTO.getFirstName());
        response.setCustomerLastName(simpleUserDTO.getLastName());
        response.setCarBrandName(comment.getAd().getCar().getCarModel().getCarBrand().getName());
        response.setCarModelName(comment.getAd().getCar().getCarModel().getName());
        response.setCommentId(comment.getId());
        return response;
    }
}
