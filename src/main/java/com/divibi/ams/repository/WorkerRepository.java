package com.divibi.ams.repository;

import com.divibi.ams.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // Аннотация @Repository говорит Spring, что это репозиторий, который взаимодействует с базой данных.
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    // В этом интерфейсе нет необходимости определять методы.
    // JpaRepository предоставляет стандартные методы CRUD, которые мы можем использовать.
    @Query("SELECT i FROM Worker i WHERE LOWER(i.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Worker> searchByKeyword(String keyword);
}
