package com.ailysis.documentsanalyzer.service;

import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.domain.model.Document;
import com.ailysis.documentsanalyzer.exception.DocumentNameExistsException;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    List<Document> findAllDocuments();
    Document saveDocument(DocumentRequestDto document) throws IOException, DocumentNameExistsException;
    void delecteDocument(Long documentId) throws IOException;
    List<Document> findDocumentsByCategory(String categoryId);
}
