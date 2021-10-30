package com.ailysis.documentsanalyzer.service;

import com.ailysis.documentsanalyzer.domain.model.Hierarchy;
import com.ailysis.documentsanalyzer.repository.HierarchyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HierarchyServiceImpl implements HierarchyService{

    @Autowired
    HierarchyRepository hierarchyRepository;

    @Override
    public Hierarchy findByName(String name) {
        return hierarchyRepository.findByName(name);
    }
}
