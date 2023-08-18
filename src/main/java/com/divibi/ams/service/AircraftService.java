package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Worker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AircraftService {

    List<Aircraft> getAllAircrafts();

    Aircraft getAircraftById(Integer id);

    Aircraft saveAircraft(Aircraft aircraft);

    Aircraft updateAircraft(Integer id, Aircraft aircraft);
    List<Aircraft> findAirCraftByKeyWord(String keyword);
    void deleteAircraft(Integer id);
    Page<Aircraft> findPaginated(int pageNo, int pageSize);
}
