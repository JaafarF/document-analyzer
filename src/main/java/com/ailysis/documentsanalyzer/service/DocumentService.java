package com.ailysis.documentsanalyzer.service;

import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.domain.dto.DocumentResponseDto;
import com.ailysis.documentsanalyzer.domain.model.Document;
import com.ailysis.documentsanalyzer.exception.DocumentNameExistsException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    List<DocumentResponseDto> findAllDocuments();
    DocumentResponseDto saveDocument(MultipartFile document) throws IOException, DocumentNameExistsException;
    void delecteDocument(Long documentId) throws IOException;
    List<DocumentResponseDto> findDocumentsByCategory(String categoryId);
}
