package com.divibi.ams.service;

import com.divibi.ams.model.AirCraftMaintenances;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AircraftMaintenanceService {
    List<AirCraftMaintenances> getAllAircraftMaintenances();

    AirCraftMaintenances getAircraftMaintenanceById(Long id);

    AirCraftMaintenances saveAircraftMaintenance(AirCraftMaintenances aircraft);

    List<AirCraftMaintenances> findAirCraftMaintenanceByKeyWord(String keyWord);
    void deleteAircraftMaintenance(Long id);
    Page<AirCraftMaintenances> findPaginated(int pageNo, int pageSize);
}
