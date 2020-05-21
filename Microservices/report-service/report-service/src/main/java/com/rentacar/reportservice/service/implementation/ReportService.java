package com.rentacar.reportservice.service.implementation;

import com.rentacar.reportservice.dto.request.ReportRequest;
import com.rentacar.reportservice.dto.response.ReportResponse;
import com.rentacar.reportservice.repository.IReportRepository;
import com.rentacar.reportservice.service.IReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReportService implements IReportService {

    private final IReportRepository _reportrepository;

    public ReportService(IReportRepository reportrepository) {
        _reportrepository = reportrepository;
    }

    @Override
    public ReportResponse sendReport(ReportRequest request) {
        return null;
    }

    @Override
    public ReportResponse getReport(UUID report_id) {
        return null;
    }

    @Override
    public ReportResponse changeReport(UUID report_id) {
        return null;
    }

    @Override
    public ReportResponse deleteReport(UUID report_id) {
        return null;
    }

    @Override
    public List<ReportResponse> getAllReports() {
        return null;
    }

    @Override
    public List<ReportResponse> getAllReportsByCar(UUID car_id) {
        return null;
    }
}
