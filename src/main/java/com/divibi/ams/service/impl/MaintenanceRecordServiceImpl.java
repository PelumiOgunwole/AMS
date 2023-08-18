package com.divibi.ams.service.impl;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.MaintenanceRecord;
import com.divibi.ams.repository.MaintenanceRecordRepository;
import com.divibi.ams.service.MaintenanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service  // Аннотация @Service говорит Spring, что это сервисный класс.
public class MaintenanceRecordServiceImpl implements MaintenanceRecordService {

    private final MaintenanceRecordRepository maintenanceRecordRepository;

    // Spring автоматически внедрит реализацию репозитория здесь
    @Autowired
    public MaintenanceRecordServiceImpl(MaintenanceRecordRepository maintenanceRecordRepository) {
        this.maintenanceRecordRepository = maintenanceRecordRepository;
    }

    @Override
    public List<MaintenanceRecord> getAllMaintenanceRecords() {
        return maintenanceRecordRepository.findAll();  // Используем метод JpaRepository для получения всех записей об обслуживании
    }

    @Override
    public MaintenanceRecord getMaintenanceRecordById(Integer id) {
        Optional<MaintenanceRecord> maintenanceRecord = maintenanceRecordRepository.findById(id);  // Используем метод JpaRepository для получения записи об обслуживании по ее ID

        if (maintenanceRecord.isPresent()) {
            return maintenanceRecord.get();
        } else {
            throw new RuntimeException("MaintenanceRecord not found for id :: " + id);
        }
    }

    @Override
    public MaintenanceRecord saveMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
        return maintenanceRecordRepository.save(maintenanceRecord);  // Используем метод JpaRepository для сохранения записи об обслуживании
    }

    @Override
    public MaintenanceRecord updateMaintenanceRecord(Integer id, MaintenanceRecord maintenanceRecordDetails) {
        Optional<MaintenanceRecord> maintenanceRecord = maintenanceRecordRepository.findById(id);

        if (maintenanceRecord.isPresent()) {
            MaintenanceRecord originalMaintenance = maintenanceRecord.get();

            if (Objects.nonNull(maintenanceRecordDetails.getMaintenanceDate())) {
                originalMaintenance.setMaintenanceDate(maintenanceRecordDetails.getUpDatedMaintenanceDate());
            }
            if (Objects.nonNull(maintenanceRecordDetails.getComponentId()) && originalMaintenance.getComponentId()!=0) {
                originalMaintenance.setComponentId(maintenanceRecordDetails.getComponentId());
            }
            if (Objects.nonNull(maintenanceRecordDetails.getDescription()) && !"".equalsIgnoreCase(maintenanceRecordDetails.getDescription())) {
                originalMaintenance.setDescription(maintenanceRecordDetails.getDescription());
            }

            return maintenanceRecordRepository.save(originalMaintenance);

        }
        return null;
    }

    @Override
    public List<MaintenanceRecord> findMaintenanceByKeyWord(String keyword) {
        if(keyword!=null){
            return maintenanceRecordRepository.searchByKeyword(keyword);
        }
        return (List<MaintenanceRecord>) maintenanceRecordRepository.findAll();
    }

    @Override
    public void deleteMaintenanceRecord(Integer id) {
        MaintenanceRecord maintenanceRecord = getMaintenanceRecordById(id);  // Используем наш метод для получения записи об обслуживании по ее ID

        maintenanceRecordRepository.delete(maintenanceRecord);  // Используем метод JpaRepository для удаления записи об обслуживании
    }
    @Override
    public Page<MaintenanceRecord> findMaintenancePaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return maintenanceRecordRepository.findAll(pageable);
    }
}
