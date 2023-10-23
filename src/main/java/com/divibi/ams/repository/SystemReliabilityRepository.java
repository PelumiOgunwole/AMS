package com.divibi.ams.repository;

import com.divibi.ams.model.SystemReliability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemReliabilityRepository extends JpaRepository<SystemReliability,Long> {
    @Query("SELECT DISTINCT i FROM SystemReliability i " +

            "WHERE " +
            "CONCAT(i.description, i.pireps, i.ATA, i.maintenanceDef, i.cabinDef, " +
            "i.schedWO, i.totalWo, i.pirepsRate, i.pirepsRateUCL,i.totalUsWoRate,i.schedWoRate," +
            "i.pirepsRateTwelveMonths, i.maintDefRate, i.maintDefRateUCL, i.maintDefRateTwelveMonths,i.cabinDefRate,i.cabinDefRateUCL,i.cabinDefRateTwelveMonths) " +
            "LIKE %:keyWord%")
    List<SystemReliability> searchByKeyword(@Param("keyWord") String keyWord);

}
