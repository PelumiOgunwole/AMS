package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.PartsReliability;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PartsReliabilityService {
    List<PartsReliability> getAllPartsReliability();

    PartsReliability getPartsReliabilityById(Long id);

    PartsReliability savePartsReliability(PartsReliability PartsReliability);

    List<PartsReliability> findPartsReliabilityByKeyWord(String keyWord);
    void deletePartsReliability(Long id);
    Page<PartsReliability> findPaginated(int pageNo, int pageSize);

}
