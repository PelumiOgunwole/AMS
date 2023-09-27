package com.divibi.ams.service.impl;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Worker;
import com.divibi.ams.repository.AircraftRepository;
import com.divibi.ams.repository.WorkOrderRepository;
import com.divibi.ams.repository.WorkerRepository;
import com.divibi.ams.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service  // аннотация, указывающая, что это сервис Spring
public class AircraftServiceImpl implements AircraftService {

    // Инъекция зависимостей для репозитория

    private AircraftRepository aircraftRepository;
    private WorkerRepository workerRepository;

    //@Autowired  // аннотация, указывающая, что это место для автоматической вставки зависимости
    public AircraftServiceImpl(AircraftRepository aircraftRepository,WorkerRepository workerRepository) {
        this.aircraftRepository = aircraftRepository;
        this.workerRepository=workerRepository;
    }

    @Override
    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    @Override
    public Aircraft getAircraftById(Long id) {
        Aircraft aircraft = aircraftRepository.findById(id).orElse(null);
        return aircraft;
    }

    @Override
//    @Transactional
    public Aircraft saveAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }



    @Override
    public List<Aircraft> findAirCraftByKeyWord(String keyWord) {
        if (keyWord != null ) {
            // Perform the search using the provided keyword and data types
            return aircraftRepository.searchByKeyword(keyWord);
        } else {
            // If no keyword provided, perform a search without any filters
            return aircraftRepository.findAll();
        }

    }

    @Override
    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);
    }

    @Override
    public Page<Aircraft> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return aircraftRepository.findAll(pageable);
    }
}
