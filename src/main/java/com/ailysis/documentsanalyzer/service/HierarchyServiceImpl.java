package com.ailysis.documentsanalyzer.service;

import com.ailysis.documentsanalyzer.domain.model.Hierarchy;
import com.ailysis.documentsanalyzer.repository.HierarchyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HierarchyServiceImpl implements HierarchyService{

    @Autowired
    HierarchyRepository hierarchyRepository;

    @Override
    public Hierarchy findByName(String name) {
        return hierarchyRepository.findByName(name);
    }

    @Override
    public Hierarchy saveHierarchy(Hierarchy hierarchy) {
        return hierarchyRepository.save(hierarchy);
    }

    @Override
    public List<Hierarchy> mapHierarchyFromApi(List<String> apiHierarchies) {

        List<Hierarchy> hierarchies = new ArrayList<>();
        for (String apiHierarchy : apiHierarchies) {
            Hierarchy hierarchy = this.findByName(apiHierarchy);
            if (Objects.isNull(hierarchy)) {
                hierarchy = new Hierarchy();
                hierarchy.setName(apiHierarchy);
                this.saveHierarchy(hierarchy);
            }
            hierarchies.add(hierarchy);
        }
        /*apiHierarchies.forEach(apiHierarchy -> {
            Hierarchy hierarchy = this.findByName(apiHierarchy);
            if (Objects.isNull(hierarchy)) {
                hierarchy = new Hierarchy();
                hierarchy.setName(apiHierarchy);
                this.saveHierarchy(hierarchy);
            }
            hierarchies.add(hierarchy);
        });*/

        return hierarchies;
    }
}
