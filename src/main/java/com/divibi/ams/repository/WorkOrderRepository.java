package com.divibi.ams.repository;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkOrderRepository extends JpaRepository<WorkOrder,Long> {
    @Query("SELECT DISTINCT i FROM WorkOrder i " +
            "WHERE CONCAT(i.AC, i.dueDate, i.IssueDate, i.flags) LIKE %:keyWord%")
    List<WorkOrder> searchByKeyword(@Param("keyWord") String keyWord);


}
