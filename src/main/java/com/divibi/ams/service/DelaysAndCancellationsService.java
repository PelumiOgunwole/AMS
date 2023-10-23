package com.divibi.ams.service;

import com.divibi.ams.model.DelaysAndCancellations;
import com.divibi.ams.model.DelaysAndCancellations;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DelaysAndCancellationsService {
    List<DelaysAndCancellations> getAllDelaysAndCancellations();

    DelaysAndCancellations getDelaysAndCancellationsById(Long id);


    Page<DelaysAndCancellations> findPaginated(int pageNo, int pageSize);

    void saveDelaysAndCancellations(DelaysAndCancellations DelaysAndCancellations);


    List<DelaysAndCancellations> findDelaysAndCancellationsByKeyWord(String keyWord);

    void deleteDelaysAndCancellations(Long id);

}
