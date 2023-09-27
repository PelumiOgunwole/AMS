package com.divibi.ams.service.impl;

import com.divibi.ams.model.Suppliers;
import com.divibi.ams.repository.SupplierRepository;
import com.divibi.ams.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    // Spring автоматически внедрит реализацию репозитория здесь
    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Suppliers> getAllSuppliers() {
        return supplierRepository.findAll();  // Используем метод JpaRepository для получения всех работников
    }

    @Override
    public Suppliers getSupplierById(Long id) {
        Optional<Suppliers> supplier = supplierRepository.findById(id);  // Используем метод JpaRepository для получения работника по его ID

        if (supplier.isPresent()) {
            return supplier.get();
        } else {
            throw new RuntimeException("Worker not found for id :: " + id);
        }
    }

    @Override
    public Suppliers saveSupplier(Suppliers supplier) {
        return supplierRepository.save(supplier);  // Используем метод JpaRepository для сохранения работника
    }



    @Override
    public void deleteSupplier(Long id) {
        Suppliers supplier = getSupplierById(id);  // Используем наш метод для получения работника по его ID

        supplierRepository.delete(supplier);  // Используем метод JpaRepository для удаления работника
    }

    @Override
    public List<Suppliers> findSupplierByKeyWord(String keyword) {
        if(keyword!=null){
            return supplierRepository.searchByKeyword(keyword);
        }
        return (List<Suppliers>) supplierRepository.findAll();
    }

    @Override
    public Page<Suppliers> findPaginated(Integer pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return supplierRepository.findAll(pageable);
    }
}
