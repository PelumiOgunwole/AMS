package com.divibi.ams.service.impl;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Worker;
import com.divibi.ams.repository.WorkerRepository;
import com.divibi.ams.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Аннотация @Service говорит Spring, что это сервисный класс.
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    // Spring автоматически внедрит реализацию репозитория здесь
    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();  // Используем метод JpaRepository для получения всех работников
    }

    @Override
    public Worker getWorkerById(Integer id) {
        Optional<Worker> worker = workerRepository.findById(id);  // Используем метод JpaRepository для получения работника по его ID

        if (worker.isPresent()) {
            return worker.get();
        } else {
            throw new RuntimeException("Worker not found for id :: " + id);
        }
    }

    @Override
    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);  // Используем метод JpaRepository для сохранения работника
    }

    @Override
    public Worker updateWorker(Integer id, Worker workerDetails) {
        Worker worker = getWorkerById(id);  // Используем наш метод для получения работника по его ID

        worker.setFirstName(workerDetails.getFirstName());
        worker.setLastName(workerDetails.getLastName());
        worker.setJobTitle(workerDetails.getJobTitle());

        return workerRepository.save(worker);  // Используем метод JpaRepository для сохранения обновленного работника
    }

    @Override
    public void deleteWorker(Integer id) {
        Worker worker = getWorkerById(id);  // Используем наш метод для получения работника по его ID

        workerRepository.delete(worker);  // Используем метод JpaRepository для удаления работника
    }

    @Override
    public Page<Worker> findPaginated(Integer pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return workerRepository.findAll(pageable);
    }

}
