package com.divibi.ams.service.impl;

import com.divibi.ams.model.PartsReliability;
import com.divibi.ams.repository.PartsReliabilityRepository;
import com.divibi.ams.service.PartsReliabilityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PartsReliabilityServiceImpl implements PartsReliabilityService {

    private  final PartsReliabilityRepository partsReliabilityRepository;

    public PartsReliabilityServiceImpl(PartsReliabilityRepository partsReliabilityRepository) {
        this.partsReliabilityRepository = partsReliabilityRepository;
    }

    @Override
    public List<PartsReliability> getAllPartsReliability() {
        return partsReliabilityRepository.findAll();
    }

    @Override
    public PartsReliability getPartsReliabilityById(Long id) {
        PartsReliability partsReliability =partsReliabilityRepository.findById(id).orElse(null);
        return partsReliability;
    }

    @Override
    public PartsReliability savePartsReliability(PartsReliability PartsReliability) {
        return partsReliabilityRepository.save(PartsReliability);
    }

    @Override
    public List<PartsReliability> findPartsReliabilityByKeyWord(String keyWord) {
        if (keyWord != null ) {
            // Perform the search using the provided keyword and data types
            return partsReliabilityRepository.searchByKeyword(keyWord);
        } else {
            // If no keyword provided, perform a search without any filters
            return partsReliabilityRepository.findAll();
        }

    }

    @Override
    public void deletePartsReliability(Long id) {
        partsReliabilityRepository.deleteById(id);
    }

    @Override
    public Page<PartsReliability> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return partsReliabilityRepository.findAll(pageable);
    }
}
