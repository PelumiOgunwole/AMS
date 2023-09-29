package com.divibi.ams.repository;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.AircraftUtilization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirCraftUtilizationRepository extends JpaRepository<AircraftUtilization,Long> {
    @Query("SELECT DISTINCT i FROM AircraftUtilization i " +

            "WHERE " +
            "CONCAT(i.id, i.depDate, i.arrivalDate, i.AC, i.acType, " +
            "i.operation, i.flight, i.servType, i.flightLog,i.blockHours,i.cycles,i.tah,i.tac,i.dep,i.arr,i.departureTime, " +
            "i.perDay, i.hours,i.arrivalTime) " +
            "LIKE %:keyWord%")
    List<AircraftUtilization> searchByKeyword(@Param("keyWord") String keyWord);
}
