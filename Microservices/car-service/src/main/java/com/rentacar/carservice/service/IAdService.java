package com.rentacar.carservice.service;

import com.rentacar.carservice.dto.client.AdClientResponse;
import com.rentacar.carservice.dto.request.AddAdRequest;
import com.rentacar.carservice.dto.request.UpdateAdRequest;
import com.rentacar.carservice.dto.request.UpdateCarAvailability;
import com.rentacar.carservice.dto.response.AdResponse;
import com.rentacar.carservice.dto.response.AgentResponse;
import com.rentacar.carservice.dto.response.PhotoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IAdService {

    AdResponse createAd(List<MultipartFile> fileList, AddAdRequest request) throws Exception;

    PhotoResponse getPhoto(UUID adId);

    List<AdResponse> getAgentAds(UUID id);

    List<PhotoResponse> getAllPhotos(UUID adID);

    AdClientResponse getAd(UUID adId);

    AgentResponse getAgentByAdID(UUID id);
}
