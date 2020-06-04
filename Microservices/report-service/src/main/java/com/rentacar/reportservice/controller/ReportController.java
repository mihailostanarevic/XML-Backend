package com.rentacar.reportservice.controller;

import com.rentacar.reportservice.dto.request.ReportRequest;
import com.rentacar.reportservice.dto.response.ReportResponse;
import com.rentacar.reportservice.service.IReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final IReportService _reportservice;

    public ReportController(IReportService reportservice) {
        _reportservice = reportservice;
    }

    @PostMapping
    public ReportResponse sendReport(@RequestBody ReportRequest request){
        return _reportservice.sendReport(request);
    }

    @GetMapping("/{id}")
    public ReportResponse getReport(@PathVariable UUID id) {
        return _reportservice.getReport(id);
    }

    @GetMapping("/{id}/report")
    public ReportResponse changeReport(@PathVariable UUID id) {
        return _reportservice.changeReport(id);
    }

    @DeleteMapping("/{id}/report")
    public ReportResponse deleteReport(@PathVariable UUID id) {
        return _reportservice.deleteReport(id);
    }

    @GetMapping
    public List<ReportResponse> getAllReports(){
        return _reportservice.getAllReports();
    }

    @GetMapping("/{id}/car")
    public List<ReportResponse> getAllReportsByCar(@PathVariable UUID id){
        return _reportservice.getAllReportsByCar(id);
    }
}
