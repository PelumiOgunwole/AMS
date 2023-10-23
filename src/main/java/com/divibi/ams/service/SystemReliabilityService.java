package com.divibi.ams.service;

import com.divibi.ams.model.PartsReliability;
import com.divibi.ams.model.SystemReliability;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SystemReliabilityService {
    List<SystemReliability> getAllSystemReliability();

    SystemReliability getSystemReliabilityById(Long id);


    Page<SystemReliability> findPaginated(int pageNo, int pageSize);

    void saveSystemReliability(SystemReliability systemReliability);


    List<SystemReliability> findSystemReliabilityByKeyWord(String keyWord);

    void deleteSystemReliability(Long id);

}
