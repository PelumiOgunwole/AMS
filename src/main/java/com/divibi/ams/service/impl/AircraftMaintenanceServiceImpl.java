package com.divibi.ams.service.impl;

import com.divibi.ams.model.AirCraftMaintenances;
import com.divibi.ams.repository.AircraftMaintenanceRepository;
import com.divibi.ams.service.AircraftMaintenanceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AircraftMaintenanceServiceImpl implements AircraftMaintenanceService {
    private  final AircraftMaintenanceRepository repository;

    public AircraftMaintenanceServiceImpl(AircraftMaintenanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AirCraftMaintenances> getAllAircraftMaintenances() {
        return repository.findAll();
    }

    @Override
    public AirCraftMaintenances getAircraftMaintenanceById(Long id) {
        AirCraftMaintenances aircraft = repository.findById(id).orElse(null);
        return aircraft;
    }

    @Override
    public AirCraftMaintenances saveAircraftMaintenance(AirCraftMaintenances aircraft) {
        return repository.save(aircraft);
    }

    @Override
    public List<AirCraftMaintenances> findAirCraftMaintenanceByKeyWord(String keyWord) {
        if (keyWord != null ) {
            // Perform the search using the provided keyword and data types
            return repository.searchByKeyword(keyWord);
        } else {
            // If no keyword provided, perform a search without any filters
            return repository.findAll();
        }

    }

    @Override
    public void deleteAircraftMaintenance(Long id) {
        repository.deleteById(id);

    }

    @Override
    public Page<AirCraftMaintenances> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return repository.findAll(pageable);
    }
}
