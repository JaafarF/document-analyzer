package com.ailysis.documentsanalyzer.repository;

import com.ailysis.documentsanalyzer.domain.model.CategoryBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryBaseRepository extends JpaRepository<CategoryBase, String> {
}
