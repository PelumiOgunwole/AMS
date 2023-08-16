package com.divibi.ams.repository;

import com.divibi.ams.model.MaintenanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Аннотация @Repository говорит Spring, что это репозиторий, который взаимодействует с базой данных.
public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Integer> {
    // В этом интерфейсе нет необходимости определять методы.
    // JpaRepository предоставляет стандартные методы CRUD, которые мы можем использовать.
}
