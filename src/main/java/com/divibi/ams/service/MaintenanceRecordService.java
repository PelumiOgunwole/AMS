package com.divibi.ams.service;

import com.divibi.ams.model.MaintenanceRecord;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaintenanceRecordService {

    List<MaintenanceRecord> getAllMaintenanceRecords();

    MaintenanceRecord getMaintenanceRecordById(Integer id);

    MaintenanceRecord saveMaintenanceRecord(MaintenanceRecord maintenanceRecord);

    MaintenanceRecord updateMaintenanceRecord(Integer id, MaintenanceRecord maintenanceRecord);

    void deleteMaintenanceRecord(Integer id);

    Page<MaintenanceRecord> findMaintenancePaginated(int pageNo, int pageSize);
}
