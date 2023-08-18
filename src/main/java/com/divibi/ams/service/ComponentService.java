package com.divibi.ams.service;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import com.divibi.ams.model.Worker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComponentService {

    List<Component> getAllComponents();

    Component getComponentById(Integer id);

    Component saveComponent(Component component);

    Component updateComponent(Integer id, Component component);
    List<Component> findComponentByKeyWord(String keyword);
    void deleteComponent(Integer id);
    Page<Component> findPaginated(int pageNo, int pageSize);
}
