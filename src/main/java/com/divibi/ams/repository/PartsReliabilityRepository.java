package com.divibi.ams.repository;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.PartsReliability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PartsReliabilityRepository extends JpaRepository<PartsReliability, Long> {
    @Query("SELECT DISTINCT i FROM PartsReliability i " +

            "WHERE " +
            "CONCAT(i.description, i.pn, i.ATA, i.acGroup, i.unitHours, " +
            "i.usRem, i.rateUsRem, i.failures, i.rateFailures, " +
            "i.noFault, i.MTBF, i.MTBUR, i.MTTUR,i.nffRate) " +
            "LIKE %:keyWord%")
    List<PartsReliability> searchByKeyword(@Param("keyWord") String keyWord);
}
