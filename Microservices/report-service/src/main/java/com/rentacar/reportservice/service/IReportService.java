package com.rentacar.reportservice.service;

import com.rentacar.reportservice.dto.request.ReportRequest;
import com.rentacar.reportservice.dto.response.ReportResponse;

import java.util.List;
import java.util.UUID;

public interface IReportService {

     ReportResponse sendReport(ReportRequest request);

     ReportResponse getReport(UUID report_id);

     ReportResponse changeReport(UUID report_id);

     ReportResponse deleteReport(UUID report_id);

     List<ReportResponse> getAllReports();

     List<ReportResponse> getAllReportsByCar(UUID car_id);


}
