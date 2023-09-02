package com.divibi.ams.repository;

import com.divibi.ams.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

//    @Query("SELECT DISTINCT i FROM Aircraft i " +
//            "LEFT JOIN FETCH i.components c " +
//            "LEFT JOIN FETCH i.workers w " +
//            "WHERE " +
//            "CONCAT(i.aircraftId, i.tailNumber, i.serialNumber, i.capacity, i.baseLocation, " +
//            "i.currentLocation, i.nextMaintenanceDate, i.totalFlightHours, i.deferredMaintenanceTasks, " +
//            "i.technicalStatus, i.manufacturer) " +
//            "LIKE %?1%")
//    List<Aircraft> searchByKeyword(@Param("keyWord") String keyWord);


    @Query("SELECT DISTINCT i FROM Aircraft i " +
            "LEFT JOIN FETCH i.components c " +
            "LEFT JOIN FETCH i.workers w " +
            "WHERE " +
            "CONCAT(i.aircraftId, i.tailNumber, i.serialNumber, i.capacity, i.baseLocation, " +
            "i.currentLocation, i.nextMaintenanceDate, i.totalFlightHours, i.deferredMaintenanceTasks, " +
            "i.technicalStatus, i.manufacturer) " +
            "LIKE %:keyWord%")
    List<Aircraft> searchByKeyword(@Param("keyWord") String keyWord);



}




