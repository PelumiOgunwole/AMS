package com.divibi.ams.service;

import com.divibi.ams.model.PickSlipViewer;
import com.divibi.ams.model.PickSlipViewer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PickSlipViewerService {
    List<PickSlipViewer> getAllPickSlipViewer();

    PickSlipViewer getPickSlipViewerById(Long id);


    Page<PickSlipViewer> findPaginated(int pageNo, int pageSize);

    void savePickSlipViewer(PickSlipViewer pickSlipViewer);


    List<PickSlipViewer> findPickSlipViewerByKeyWord(String keyWord);

    void deletePickSlipViewer(Long id);

}
