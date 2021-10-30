package com.ailysis.documentsanalyzer.service;

import com.ailysis.documentsanalyzer.domain.model.Hierarchy;

public interface HierarchyService {

    Hierarchy findByName(String name);
}
