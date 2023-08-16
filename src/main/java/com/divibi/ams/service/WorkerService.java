package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Worker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorkerService {

    List<Worker> getAllWorkers();

    Worker getWorkerById(Integer id);

    Worker saveWorker(Worker worker);

    Worker updateWorker(Integer id, Worker worker);

    void deleteWorker(Integer id);

    Page<Worker> findPaginated(Integer pageNo, int pageSize);
}
