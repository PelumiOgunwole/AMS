package com.divibi.ams.repository;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // аннотация, указывающая, что это репозиторий Spring
public interface ComponentRepository extends JpaRepository<Component, Integer> {
    // JpaRepository предоставляет нам методы для общих операций CRUD
    // мы указываем тип сущности (Component) и тип идентификатора (Long)
    @Query("SELECT i FROM Component i WHERE LOWER(i.componentId) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.componentName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.manufacturer) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.flightHours) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Component> searchByKeyword(String keyword);
}
