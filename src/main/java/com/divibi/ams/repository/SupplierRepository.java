package com.divibi.ams.repository;

import com.divibi.ams.model.Suppliers;
import com.divibi.ams.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SupplierRepository extends JpaRepository<Suppliers, Long> {

    @Query("SELECT DISTINCT i FROM Suppliers i " +
            "LEFT JOIN FETCH i.component c " +
            "WHERE " +
            "CONCAT(i.supplier_id, i.Name, i.contactInfo) " +
            "LIKE %:keyword%")
    List<Suppliers> searchByKeyword(@Param("keyword") String keyword);
}
