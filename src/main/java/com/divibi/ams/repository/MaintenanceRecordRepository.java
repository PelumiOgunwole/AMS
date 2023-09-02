//package com.divibi.ams.repository;
//
//import com.divibi.ams.model.Aircraft;
//import com.divibi.ams.model.Component;
//import com.divibi.ams.model.MaintenanceRecord;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository  // Аннотация @Repository говорит Spring, что это репозиторий, который взаимодействует с базой данных.
//public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Integer> {
//    // В этом интерфейсе нет необходимости определять методы.
//    // JpaRepository предоставляет стандартные методы CRUD, которые мы можем использовать.
//    @Query("SELECT i FROM MaintenanceRecord i WHERE LOWER(i.maintenanceId) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.maintenanceDate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.componentId) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    List<MaintenanceRecord> searchByKeyword(String keyword);
//}
