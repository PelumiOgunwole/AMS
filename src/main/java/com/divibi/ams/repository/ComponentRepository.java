package com.divibi.ams.repository;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // аннотация, указывающая, что это репозиторий Spring
public interface ComponentRepository extends JpaRepository<Component, Long> {
    // JpaRepository предоставляет нам методы для общих операций CRUD
    // мы указываем тип сущности (Component) и тип идентификатора (Long)
//   @Query("SELECT i FROM Component i WHERE " +
//            "CONCAT(i.componentId,i.componentName,i.manufacturer,i.status,i.description,i.installationDate,i.cost," +
//            "i.numberRemovals,i.lifeSpan,i.complianceCertificates,i.critical_component,i.damageNumbers,i.nextMaintenanceDate)" +
//            " LIKE %?1%")
    @Query("SELECT DISTINCT i FROM Component i " +
            "LEFT JOIN FETCH i.suppliers s " +
            "LEFT JOIN FETCH i.aircraft a " +
            "WHERE " +
            "CONCAT(i.componentId, i.componentName, i.manufacturer, i.status, i.description, i.installationDate, " +
            "i.warrantyExpirationDate, i.unscheduledRemoval, i.MTBF, i.MTBUR, i.lifeSpan, i.conditions, i.critical_component, " +
            "i.maintenanceCycle, i.lastMaintenanceDate, i.nextMaintenanceDate, i.repairHistory, i.overhaulHistory, i.cost, " +
            "i.warrantyTerms, i.location, i.complianceCertificates, i.workOrderList, i.damageNumbers, " +
            "i.operatingHoursFirstFailure, i.operatingHoursSecondFailure, i.operatingHoursThirdFailure, " +
            "i.failureReplacementFrequency, i.scheduledMmaintenanceReplacementFrequency, i.status, i.numberRemovals, " +
            "i.ATANumber, i.MTTF) " +
            "LIKE %?1%")

    List<Component> searchByKeyword(@Param("keyword") String keyword);
}
