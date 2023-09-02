package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Worker;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface AircraftService {

    List<Aircraft> getAllAircrafts();

    Aircraft getAircraftById(Long id);

    Aircraft saveAircraft(Aircraft aircraft);

    List<Aircraft> findAirCraftByKeyWord(String keyWord);
    void deleteAircraft(Long id);
    Page<Aircraft> findPaginated(int pageNo, int pageSize);
}
