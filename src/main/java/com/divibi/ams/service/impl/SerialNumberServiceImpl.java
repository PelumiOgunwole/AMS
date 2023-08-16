package com.divibi.ams.service.impl;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.MaintenanceRecord;
import com.divibi.ams.model.SerialNumber;
import com.divibi.ams.repository.SerialNumberRepository;
import com.divibi.ams.service.SerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service  // Аннотация @Service говорит Spring, что это сервисный класс.
public class SerialNumberServiceImpl implements SerialNumberService {

    private final SerialNumberRepository serialNumberRepository;

    // Spring автоматически внедрит реализацию репозитория здесь
    @Autowired
    public SerialNumberServiceImpl(SerialNumberRepository serialNumberRepository) {
        this.serialNumberRepository = serialNumberRepository;
    }

    @Override
    public List<SerialNumber> getAllSerialNumbers() {
        return serialNumberRepository.findAll();  // Используем метод JpaRepository для получения всех серийных номеров
    }

    @Override
    public SerialNumber getSerialNumberById(Integer id) {
        Optional<SerialNumber> serialNumber = serialNumberRepository.findById(id);  // Используем метод JpaRepository для получения серийного номера по его ID

        if (serialNumber.isPresent()) {
            return serialNumber.get();
        } else {
            throw new RuntimeException("SerialNumber not found for id :: " + id);
        }
    }

    @Override
    public SerialNumber saveSerialNumber(SerialNumber serialNumber) {
        return serialNumberRepository.save(serialNumber);  // Используем метод JpaRepository для сохранения серийного номера
    }

    @Override
    public SerialNumber updateSerialNumber(Integer id, SerialNumber serialNumberDetails) {
        Optional<SerialNumber> serialNumber = serialNumberRepository.findById(id);

        if (serialNumber.isPresent()) {
            SerialNumber originalSerial = serialNumber.get();


            if (Objects.nonNull(serialNumberDetails.getComponentId()) && serialNumberDetails.getComponentId()!=0) {
                originalSerial.setComponentId(serialNumberDetails.getComponentId());
            }
            if (Objects.nonNull(serialNumberDetails.getSerialNumberValue()) && !"".equalsIgnoreCase(serialNumberDetails.getSerialNumberValue())) {
                originalSerial.setSerialNumberValue(serialNumberDetails.getSerialNumberValue());
            }

            return serialNumberRepository.save(originalSerial);

        }
        return null;
    }

    @Override
    public void deleteSerialNumber(Integer id) {
        SerialNumber serialNumber = getSerialNumberById(id);  // Используем наш метод для получения серийного номера по его ID

        serialNumberRepository.delete(serialNumber);  // Используем метод JpaRepository для удаления серийного номера
    }
    @Override
    public Page<SerialNumber> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return serialNumberRepository.findAll(pageable);
    }
}
