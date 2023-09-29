package com.divibi.ams.service.impl;

import com.divibi.ams.model.AircraftUtilization;
import com.divibi.ams.repository.AirCraftUtilizationRepository;
import com.divibi.ams.service.AircraftUtilizationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AircraftUtilizationServiceImpl implements AircraftUtilizationService {

    public final AirCraftUtilizationRepository airCraftUtilizationRepository;

    public AircraftUtilizationServiceImpl(AirCraftUtilizationRepository airCraftUtilizationRepository) {
        this.airCraftUtilizationRepository = airCraftUtilizationRepository;
    }

    @Override
    public List<AircraftUtilization> getAllAircraftUtilizations() {
        return airCraftUtilizationRepository.findAll();
    }

    @Override
    public AircraftUtilization getAircraftUtilizationById(Long id) {
        AircraftUtilization aircraft = airCraftUtilizationRepository.findById(id).orElse(null);
        return aircraft;
    }

    @Override
    public AircraftUtilization saveAircraftUtilization(AircraftUtilization aircraft) {
        return airCraftUtilizationRepository.save(aircraft);
    }

    @Override
    public List<AircraftUtilization> findAircraftUtilizationByKeyWord(String keyWord) {
        if (keyWord != null ) {
            // Perform the search using the provided keyword and data types
            return airCraftUtilizationRepository.searchByKeyword(keyWord);
        } else {
            // If no keyword provided, perform a search without any filters
            return airCraftUtilizationRepository.findAll();
        }

    }

    @Override
    public void deleteAircraftUtilization(Long id) {
        airCraftUtilizationRepository.deleteById(id);

    }

    @Override
    public Page<AircraftUtilization> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return airCraftUtilizationRepository.findAll(pageable);
    }
}
