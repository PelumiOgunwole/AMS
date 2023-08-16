package com.divibi.ams.repository;

import com.divibi.ams.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // аннотация, указывающая, что это репозиторий Spring
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {
    // JpaRepository предоставляет нам методы для общих операций CRUD
    // мы указываем тип сущности (Aircraft) и тип идентификатора (Long)
}
