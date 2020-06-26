package com.rentacar.carservice.service.impl;

import com.rentacar.carservice.client.AuthClient;
import com.rentacar.carservice.client.RentClient;
import com.rentacar.carservice.dto.feignClient.AgentDTO;
import com.rentacar.carservice.dto.feignClient.ReqDTO;
import com.rentacar.carservice.dto.feignClient.RequestAdDTO;
import com.rentacar.carservice.dto.feignClient.SimpleUserDTO;
import com.rentacar.carservice.dto.request.RateAdRequest;
import com.rentacar.carservice.dto.response.AvgRatingResponse;
import com.rentacar.carservice.dto.response.RatingResponse;
import com.rentacar.carservice.entity.Ad;
import com.rentacar.carservice.entity.Rating;
import com.rentacar.carservice.repository.IAdRepository;
import com.rentacar.carservice.repository.IRatingRepository;
import com.rentacar.carservice.service.IRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingService implements IRatingService {

    private final AuthClient _authClient;

    private final RentClient _rentClient;

    private final IRatingRepository _ratingRepository;

    private final IAdRepository _adRepository;

    private final Logger logger = LoggerFactory.getLogger("Ad service app: " + AdService.class);

    public RatingService(AuthClient authClient, RentClient rentClient, IRatingRepository ratingRepository, IAdRepository adRepository) {
        _authClient = authClient;
        _rentClient = rentClient;
        _ratingRepository = ratingRepository;
        _adRepository = adRepository;
    }

    @Override
    public RatingResponse rateAd(RateAdRequest request) throws Exception {
        SimpleUserDTO simpleUser = _authClient.getSimpleUser(request.getSimpleUserId());
        Ad ad = _adRepository.findOneById(request.getAdId());

        List<ReqDTO> simpleUsersRequests = _rentClient.getAllPaidRequestsByCustomer(simpleUser.getId());
        if(simpleUsersRequests.isEmpty()){
            logger.debug("Simple user with id: " + simpleUser.getId() + " can't rate ads");
            throw new Exception("You cannot rate any ad because you did not have any paid rents.");
        }

        Rating temp = _ratingRepository.findOneBySimpleUserAndAd_Id(simpleUser.getId(), ad.getId());
        if(temp != null){
            logger.debug("Simple user with id: " + simpleUser.getId() + " has already rated ad with id: " + ad.getId());
            throw new Exception("You have already rated this ad.");
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
            logger.debug("Simple user with id: " + simpleUser.getId() + " can't rate this particular ad (id: " + ad.getId() + ")");
            throw new Exception("You cannot rate this ad.");
        }

        Rating rating = new Rating();
        rating.setGrade(request.getGrade());
        rating.setSimpleUser(simpleUser.getId());
        rating.setAd(ad);
        Rating savedRating = _ratingRepository.save(rating);
        ad.getRatings().add(savedRating);
        _adRepository.save(ad);
        logger.info("Simple user with id: " + simpleUser.getId() + " has rated ad with id: " + ad.getId());
        return mapRatingToRatingResponse(savedRating);
    }

    @Override
    public List<RatingResponse> getAllRatingsByCustomer(UUID id) throws Exception {
        List<Rating> ratings = _ratingRepository.findAllBySimpleUser(id);
        return ratings.stream()
                .map(rating -> mapRatingToRatingResponse(rating))
                .collect(Collectors.toList());
    }

    @Override
    public List<RatingResponse> getAllRatingsByAd(UUID id) throws Exception {
        List<Rating> ratings = _ratingRepository.findAllByAd_Id(id);
        return ratings.stream()
                .map(rating -> mapRatingToRatingResponse(rating))
                .collect(Collectors.toList());
    }

    @Override
    public AvgRatingResponse getAvgRatingByAd(UUID id) throws Exception {
        List<Rating> ratings = _ratingRepository.findAllByAd_Id(id);
        float sum = 0;
        float counter = 0;
        for(Rating r: ratings){
            sum += Float.valueOf(r.getGrade());
            counter++;
        }
        AvgRatingResponse response = new AvgRatingResponse();
        AgentDTO agentDTO = _authClient.getAgent(_adRepository.findOneById(id).getAgent());
        response.setAgentName(agentDTO.getAgentName());
        response.setCarBrandName(_adRepository.findOneById(id).getCar().getCarModel().getCarBrand().getName());
        response.setCarModelName(_adRepository.findOneById(id).getCar().getCarModel().getName());
        if(counter == 0){
            response.setAvgRating("0");
            return response;
        }
        response.setAvgRating(String.valueOf(sum / counter));
        return response;
    }

    private RatingResponse mapRatingToRatingResponse(Rating rating){
        RatingResponse response = new RatingResponse();
        AgentDTO agentDTO = _authClient.getAgent(rating.getAd().getAgent());
        response.setAgentName(agentDTO.getAgentName());
        response.setGrade(rating.getGrade());
        response.setRatingId(rating.getId());
        SimpleUserDTO simpleUserDTO = _authClient.getSimpleUser(rating.getSimpleUser());
        response.setCustomerFirstName(simpleUserDTO.getFirstName());
        response.setCustomerLastName(simpleUserDTO.getLastName());
        response.setCarBrandName(rating.getAd().getCar().getCarModel().getCarBrand().getName());
        response.setCarModelName(rating.getAd().getCar().getCarModel().getName());
        return response;
    }
}
