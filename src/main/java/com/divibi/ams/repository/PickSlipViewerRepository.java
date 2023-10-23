package com.divibi.ams.repository;

import com.divibi.ams.model.PickSlipViewer;
import com.divibi.ams.model.PickSlipViewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickSlipViewerRepository extends JpaRepository<PickSlipViewer,Long> {
    @Query("SELECT DISTINCT i FROM PickSlipViewer i " +

            "WHERE " +
            "CONCAT(i.pickSlipNo, i.stationFrom, i.storeFrom, i.stationTo, i.storeTo, " +
            "i.mech, i.receiver, i.project, i.wo,i.planned,i.issued," +
            "i.timeIssued, i.booked, i.timeBooked, i.status,i.remarks,i.print,i.availability) " +
            "LIKE %:keyWord%")
    List<PickSlipViewer> searchByKeyword(@Param("keyWord") String keyWord);

}
