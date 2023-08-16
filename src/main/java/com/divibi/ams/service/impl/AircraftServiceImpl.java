package com.divibi.ams.service.impl;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.repository.AircraftRepository;
import com.divibi.ams.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service  // аннотация, указывающая, что это сервис Spring
public class AircraftServiceImpl implements AircraftService {

    // Инъекция зависимостей для репозитория

    private AircraftRepository aircraftRepository;

    //@Autowired  // аннотация, указывающая, что это место для автоматической вставки зависимости
    public AircraftServiceImpl(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    @Override
    public Aircraft getAircraftById(Integer id) {
        Aircraft aircraft = aircraftRepository.findById(id).orElse(null);
        return aircraft;
    }

    @Override
    public Aircraft saveAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    @Override
    public Aircraft updateAircraft(Integer id, Aircraft aircraft) {
        // Реализация будет зависеть от ваших требований к обновлению
        Optional<Aircraft> aircraft1 = aircraftRepository.findById(id);

        if (aircraft1.isPresent()) {
            Aircraft originalAircrat = aircraft1.get();

            if (Objects.nonNull(aircraft.getAircraftCode()) && !"".equalsIgnoreCase(originalAircrat.getAircraftCode())) {
                originalAircrat.setAircraftCode(aircraft.getAircraftCode());
            }
            if (Objects.nonNull(aircraft.getAircraftModel()) && !"".equalsIgnoreCase(originalAircrat.getAircraftModel())) {
                originalAircrat.setAircraftModel(aircraft.getAircraftModel());
            }
            if (Objects.nonNull(aircraft.getTotalFlightHours()) && aircraft.getTotalFlightHours()!=0) {
                originalAircrat.setAircraftModel(aircraft.getAircraftModel());
            }

            return aircraftRepository.save(originalAircrat);
        }
        return null;

    }

    @Override
    public void deleteAircraft(Integer id) {
        aircraftRepository.deleteById(id);
    }

    @Override
    public Page<Aircraft> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return aircraftRepository.findAll(pageable);
    }
}
