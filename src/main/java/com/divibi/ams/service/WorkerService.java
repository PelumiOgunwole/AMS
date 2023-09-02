package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Worker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorkerService {

    List<Worker> getAllWorkers();

    Worker getWorkerById(Long id);

    Worker saveWorker(Worker worker);

    Worker updateWorker(Long id, Worker worker);

    void deleteWorker(Long id);
    List<Worker> findWorkerByKeyWord(String keyword);

    Page<Worker> findPaginated(Integer pageNo, int pageSize);
}
