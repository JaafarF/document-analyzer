package com.ailysis.documentsanalyzer.repository;

import com.ailysis.documentsanalyzer.domain.model.Category;
import com.ailysis.documentsanalyzer.domain.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByCategories_CategoryBase_Id(String categoryBaseId);
}
