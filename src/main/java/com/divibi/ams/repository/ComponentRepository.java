package com.divibi.ams.repository;

import com.divibi.ams.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // аннотация, указывающая, что это репозиторий Spring
public interface ComponentRepository extends JpaRepository<Component, Integer> {
    // JpaRepository предоставляет нам методы для общих операций CRUD
    // мы указываем тип сущности (Component) и тип идентификатора (Long)
}
