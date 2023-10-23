package com.divibi.ams.service.impl;

import com.divibi.ams.model.PickSlipViewer;
import com.divibi.ams.repository.PartsReliabilityRepository;
import com.divibi.ams.service.PickSlipViewerService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickSlipViewerImpl implements PickSlipViewerService {
    private final PartsReliabilityRepository repository;

    public PickSlipViewerImpl(PartsReliabilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PickSlipViewer> getAllPickSlipViewer() {
        return null;
    }

    @Override
    public PickSlipViewer getPickSlipViewerById(Long id) {
        return null;
    }

    @Override
    public Page<PickSlipViewer> findPaginated(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public void savePickSlipViewer(PickSlipViewer pickSlipViewer) {

    }

    @Override
    public List<PickSlipViewer> findPickSlipViewerByKeyWord(String keyWord) {
        return null;
    }

    @Override
    public void deletePickSlipViewer(Long id) {

    }
}
