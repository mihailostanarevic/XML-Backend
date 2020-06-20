package com.rentacar.rentservice.service;

import com.rentacar.rentservice.dto.request.CreateReportRequest;
import com.rentacar.rentservice.dto.response.ReportResponse;
import com.rentacar.rentservice.dto.response.RequestAdResponse;

import java.util.List;
import java.util.UUID;

public interface IReportService {

    ReportResponse createReport(CreateReportRequest request, UUID requestAdId) throws Exception;

    List<RequestAdResponse> getAllRequestAdsWhichNeedReport(UUID id) throws Exception;

}
