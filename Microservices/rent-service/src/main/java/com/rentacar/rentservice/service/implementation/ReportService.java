package com.rentacar.rentservice.service.implementation;

import com.rentacar.rentservice.dto.request.CreateReportRequest;
import com.rentacar.rentservice.dto.response.ReportResponse;
import com.rentacar.rentservice.dto.response.RequestAdResponse;
import com.rentacar.rentservice.repository.IReportRepository;
import com.rentacar.rentservice.repository.IRequestAdRepository;
import com.rentacar.rentservice.service.IReportService;

import java.util.List;
import java.util.UUID;

public class ReportService implements IReportService {

    private final IReportRepository _reportRepository;

    private final IRequestAdRepository _requestAdRepository;


    public ReportService(IReportRepository reportRepository, IRequestAdRepository requestAdRepository) {
        _reportRepository = reportRepository;
        _requestAdRepository = requestAdRepository;
    }

    @Override
    public ReportResponse createReport(CreateReportRequest request, UUID requestAdId) throws Exception {
        return null;
    }

    @Override
    public List<RequestAdResponse> getAllRequestAdsWhichNeedReport(UUID id) throws Exception {
        return null;
    }
}

