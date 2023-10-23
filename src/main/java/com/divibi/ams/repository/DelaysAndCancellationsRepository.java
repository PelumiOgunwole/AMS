package com.divibi.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DelaysAndCancellationsRepository extends JpaRepository<DelaysAndCancellationsRepository,Long> {

    @Query("SELECT DISTINCT i FROM DelaysAndCancellations i " +

            "WHERE " +
            "CONCAT(i.ac, i.date, i.station, i.flightNo, i.type, " +
            "i.delay, i.irCode, i.eventId, i.delayDesc) " +
            "LIKE %:keyWord%")
    List<DelaysAndCancellationsRepository> searchByKeyword(@Param("keyWord") String keyWord);

}
