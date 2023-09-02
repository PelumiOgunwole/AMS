package com.divibi.ams.repository;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // Аннотация @Repository говорит Spring, что это репозиторий, который взаимодействует с базой данных.
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    // В этом интерфейсе нет необходимости определять методы.
    @Query("SELECT DISTINCT i FROM Worker i " +
            "LEFT JOIN FETCH i.aircraft c " +
            "WHERE " +
            "CONCAT(i.workerId, i.Name, i.contactInfo, i.contactInfo, i.position) " +
            "LIKE %:keyword%")
    List<Worker> searchByKeyword(@Param("keyword") String keyword);
    // JpaRepository предоставляет стандартные методы CRUD, которые мы можем использовать.
}
