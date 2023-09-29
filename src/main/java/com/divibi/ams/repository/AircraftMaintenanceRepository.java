package com.divibi.ams.repository;

import com.divibi.ams.model.AirCraftMaintenances;
import com.divibi.ams.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftMaintenanceRepository extends JpaRepository<AirCraftMaintenances,Long> {
    @Query("SELECT DISTINCT i FROM AirCraftMaintenances i " +

            "WHERE " +
            "CONCAT(i.maintenanceId, i.maintenanceDescription, i.maintenanceType, i.performedBy, i.scheduledDate,i.faultDescription, " +
            "i.performedDate, i.status, i.ATAChapter, i.faultCode,i.correctiveAction,i.routine,i.manHoursRequired,i.priority,i.toolsRequired,i.riskAssesment,i.additionalNotes, " +
            " i.partsRequired,i.costEstimation,i.deferred,i.deferalJustification,i.deferralDate,i.complianceCertificates,i.safetyMeasures,i.environmentalImpact) " +
            "LIKE %:keyWord%")
    List<AirCraftMaintenances> searchByKeyword(@Param("keyWord") String keyWord);

}
