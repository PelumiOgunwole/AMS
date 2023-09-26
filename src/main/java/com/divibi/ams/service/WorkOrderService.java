package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.WorkOrder;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorkOrderService {
    List<WorkOrder> getAllWorkOrder();

    WorkOrder getWorkOrderById(Long id);

    WorkOrder saveWorkOrder(WorkOrder workOrder);

    List<WorkOrder> findWorkOrderByKeyWord(String keyWord);
    void deleteWorkOrder(Long id);
    Page<WorkOrder> findPaginated(int pageNo, int pageSize);
}
