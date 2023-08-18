package com.divibi.ams.service.impl;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import com.divibi.ams.model.Worker;
import com.divibi.ams.repository.ComponentRepository;
import com.divibi.ams.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service  // Аннотация @Service говорит Spring, что это сервисный класс.
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository componentRepository;

    // Spring автоматически внедрит реализацию репозитория здесь
    @Autowired
    public ComponentServiceImpl(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public List<Component> getAllComponents() {
        return componentRepository.findAll();  // Используем метод JpaRepository для получения всех компонентов
    }

    @Override
    public Component getComponentById(Integer id) {
        Optional<Component> component = componentRepository.findById(id);  // Используем метод JpaRepository для получения компонента по его ID

        if (component.isPresent()) {
            return component.get();
        } else {
            throw new RuntimeException("Component not found for id :: " + id);
        }
    }

    @Override
    public Component saveComponent(Component component) {
        return componentRepository.save(component);  // Используем метод JpaRepository для сохранения компонента
    }

    @Override
    public Component updateComponent(Integer id, Component componentDetails) {
        Optional<Component> component = componentRepository.findById(id);

        if (component.isPresent()) {
            Component originalComp = component.get();

            if (Objects.nonNull(componentDetails.getComponentName()) && !"".equalsIgnoreCase(originalComp.getComponentName())) {
                originalComp.setComponentName(componentDetails.getComponentName());
            }
            if (Objects.nonNull(componentDetails.getManufacturer()) && !"".equalsIgnoreCase(originalComp.getManufacturer())) {
                originalComp.setManufacturer(componentDetails.getManufacturer());
            }
            if (Objects.nonNull(componentDetails.getStatus()) && !"".equalsIgnoreCase(originalComp.getStatus())) {
                originalComp.setStatus(componentDetails.getStatus());
            }
            if (Objects.nonNull(componentDetails.getFlightHours()) && componentDetails.getFlightHours()!=0) {
                originalComp.setFlightHours(componentDetails.getFlightHours());
            }

            return componentRepository.save(originalComp);
        }
        return null;
    }

    @Override
    public List<Component> findComponentByKeyWord(String keyword) {
        if(keyword!=null){
            return componentRepository.searchByKeyword(keyword);
        }
        return (List<Component>) componentRepository.findAll();
    }

    @Override
    public void deleteComponent(Integer id) {
        Component component = getComponentById(id);  // Используем наш метод для получения компонента по его ID

        componentRepository.delete(component);  // Используем метод JpaRepository для удаления компонента
    }

    @Override
    public Page<Component> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return componentRepository.findAll(pageable);
    }
}
