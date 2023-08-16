package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AircraftService {

    List<Aircraft> getAllAircrafts();

    Aircraft getAircraftById(Integer id);

    Aircraft saveAircraft(Aircraft aircraft);

    Aircraft updateAircraft(Integer id, Aircraft aircraft);

    void deleteAircraft(Integer id);
    Page<Aircraft> findPaginated(int pageNo, int pageSize);
}
