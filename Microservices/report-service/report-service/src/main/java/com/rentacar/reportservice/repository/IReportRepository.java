package com.rentacar.reportservice.repository;

import com.rentacar.reportservice.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IReportRepository extends JpaRepository<Report, UUID> {
}
