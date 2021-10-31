package com.ailysis.documentsanalyzer.service;

import com.ailysis.documentsanalyzer.domain.model.Hierarchy;

import java.util.List;
import java.util.Objects;

public interface HierarchyService {

    Hierarchy findByName(String name);

    List<Hierarchy> mapHierarchyFromApi(List<String> apiHierarchies);

    Hierarchy saveHierarchy(Hierarchy hierarchy);
}
