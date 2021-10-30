package com.ailysis.documentsanalyzer.repository;

import com.ailysis.documentsanalyzer.domain.model.Hierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HierarchyRepository  extends JpaRepository<Hierarchy, Long> {

    Hierarchy findByName(String name);
}
