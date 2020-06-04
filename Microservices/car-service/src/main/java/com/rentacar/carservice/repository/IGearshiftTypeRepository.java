package com.rentacar.carservice.repository;

import com.rentacar.carservice.entity.GearshiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IGearshiftTypeRepository extends JpaRepository<GearshiftType, UUID> {

        GearshiftType findOneById(UUID id);
        List<GearshiftType> findAllByDeleted(boolean deleted);
        GearshiftType findOneByType(String type);
}
