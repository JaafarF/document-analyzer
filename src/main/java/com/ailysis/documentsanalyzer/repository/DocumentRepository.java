package com.ailysis.documentsanalyzer.repository;

import com.ailysis.documentsanalyzer.domain.model.Category;
import com.ailysis.documentsanalyzer.domain.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value="select d.* from document d join category c on c.document_id = d.id join category_base cb on cb.id = c.category_base_id where cb.id = ?1",nativeQuery=true)
    List<Document> findByCategoriesCategoryBaseId(String categoryBaseId);
}
