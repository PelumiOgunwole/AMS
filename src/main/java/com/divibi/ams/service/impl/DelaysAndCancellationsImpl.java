package com.divibi.ams.service.impl;

import com.divibi.ams.model.DelaysAndCancellations;
import com.divibi.ams.service.DelaysAndCancellationsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelaysAndCancellationsImpl implements DelaysAndCancellationsService {

    @Override
    public List<DelaysAndCancellations> getAllDelaysAndCancellations() {
        return null;
    }

    @Override
    public DelaysAndCancellations getDelaysAndCancellationsById(Long id) {
        return null;
    }

    @Override
    public Page<DelaysAndCancellations> findPaginated(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public void saveDelaysAndCancellations(DelaysAndCancellations DelaysAndCancellations) {

    }

    @Override
    public List<DelaysAndCancellations> findDelaysAndCancellationsByKeyWord(String keyWord) {
        return null;
    }

    @Override
    public void deleteDelaysAndCancellations(Long id) {

    }
}
