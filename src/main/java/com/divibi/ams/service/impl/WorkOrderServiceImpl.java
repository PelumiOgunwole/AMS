package com.divibi.ams.service.impl;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.WorkOrder;
import com.divibi.ams.repository.AircraftRepository;
import com.divibi.ams.repository.WorkOrderRepository;
import com.divibi.ams.service.WorkOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class WorkOrderServiceImpl implements WorkOrderService {
    private WorkOrderRepository workOrderRepository;
    public WorkOrderServiceImpl(WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    @Override
    public List<WorkOrder> getAllWorkOrder() {
        return workOrderRepository.findAll();
    }

    @Override
    public WorkOrder getWorkOrderById(Long id) {
        WorkOrder workOrder = workOrderRepository.findById(id).orElse(null);
        return workOrder;
    }

    @Override
    public WorkOrder saveWorkOrder(WorkOrder workOrder) {
        return workOrderRepository.save(workOrder);
    }

    @Override
    public List<WorkOrder> findWorkOrderByKeyWord(String keyWord) {
        if (keyWord != null ) {
            // Perform the search using the provided keyword and data types
            return workOrderRepository.searchByKeyword(keyWord);
        } else {
            // If no keyword provided, perform a search without any filters
            return workOrderRepository.findAll();
        }
    }

    @Override
    public void deleteWorkOrder(Long id) {
        workOrderRepository.deleteById(id);
    }

    @Override
    public Page<WorkOrder> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return workOrderRepository.findAll(pageable);
    }
}
