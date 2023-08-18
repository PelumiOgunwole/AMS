package com.divibi.ams.repository;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // аннотация, указывающая, что это репозиторий Spring
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {
    // JpaRepository предоставляет нам методы для общих операций CRUD
    // мы указываем тип сущности (Aircraft) и тип идентификатора (Long)
    @Query("SELECT i FROM Aircraft i WHERE LOWER(i.aircraftId) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.aircraftCode) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.aircraftModel) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Aircraft> searchByKeyword(String keyword);
}
