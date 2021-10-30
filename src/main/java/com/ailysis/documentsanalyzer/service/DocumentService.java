package com.ailysis.documentsanalyzer.service;

import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.domain.model.Document;

import java.util.List;

public interface DocumentService {
    List<Document> findAllDocuments();
    Document saveDocument(DocumentRequestDto document);
    List<Document> findDocumentsByCategory(String categoryId);
}
