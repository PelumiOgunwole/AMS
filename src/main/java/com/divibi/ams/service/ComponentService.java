package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import com.divibi.ams.model.Worker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComponentService {

    List<Component> getAllComponents();

    Component getComponentById(Long id);

    Component saveComponent(Component component);

//    Component updateComponent(Long id, Component component);
    List<Component> findComponentByKeyWord(String keyword);
    void deleteComponent(Long id);
    Page<Component> findPaginated(int pageNo, int pageSize);
}
