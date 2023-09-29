package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.AircraftUtilization;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AircraftUtilizationService {
    List<AircraftUtilization> getAllAircraftUtilizations();

    AircraftUtilization getAircraftUtilizationById(Long id);

    AircraftUtilization saveAircraftUtilization(AircraftUtilization aircraft);

    List<AircraftUtilization> findAircraftUtilizationByKeyWord(String keyWord);
    void deleteAircraftUtilization(Long id);
    Page<AircraftUtilization> findPaginated(int pageNo, int pageSize);
}
