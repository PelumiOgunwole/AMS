package com.divibi.ams.service.impl;

import com.divibi.ams.model.PartsReliability;
import com.divibi.ams.model.SystemReliability;
import com.divibi.ams.repository.SystemReliabilityRepository;
import com.divibi.ams.service.SystemReliabilityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class SystemReliabilityServiceImpl implements SystemReliabilityService {
    private  final SystemReliabilityRepository repository;

    public SystemReliabilityServiceImpl(SystemReliabilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SystemReliability> getAllSystemReliability() {
        return repository.findAll();
    }
    @Override
    public SystemReliability getSystemReliabilityById(Long id) {
        SystemReliability sys =repository.findById(id).orElse(null);
        return sys;
    }

    @Override
    public void saveSystemReliability(SystemReliability systemReliability) {
        repository.save(systemReliability);
    }

    @Override
    public List<SystemReliability> findSystemReliabilityByKeyWord(String keyWord) {
        if (keyWord != null ) {
            // Perform the search using the provided keyword and data types
            return repository.searchByKeyword(keyWord);
        } else {
            // If no keyword provided, perform a search without any filters
            return repository.findAll();
        }

    }

    @Override
    public void deleteSystemReliability(Long id) {
        repository.deleteById(id);
    }



    @Override
    public Page<SystemReliability> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return repository.findAll(pageable);
    }
}
