package com.divibi.ams.service;

import com.divibi.ams.model.Suppliers;
import com.divibi.ams.model.Worker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupplierService {
    List<Suppliers> getAllSuppliers();

    Suppliers getSupplierById(Long id);

    Suppliers saveSupplier(Suppliers supplier);

    Suppliers updateSupplier(Long id, Suppliers supplierDetails);

    void deleteSupplier(Long id);

    List<Suppliers> findSupplierByKeyWord(String keyword);

    Page<Suppliers> findPaginated(Integer pageNo, int pageSize);

}
