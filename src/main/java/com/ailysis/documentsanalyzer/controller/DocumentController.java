package com.ailysis.documentsanalyzer.controller;

import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.domain.dto.DocumentResponseDto;
import com.ailysis.documentsanalyzer.exception.DocumentNameExistsException;
import com.ailysis.documentsanalyzer.exception.ExceptionHandlerAdvice;
import com.ailysis.documentsanalyzer.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/document")
public class DocumentController extends ExceptionHandlerAdvice {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<DocumentResponseDto>> findAllDocuments() {
        List<DocumentResponseDto> documentResponseDtos = this.documentService.findAllDocuments();
        return new ResponseEntity<>(documentResponseDtos, OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentResponseDto> saveDocument(@RequestParam(value = "document") MultipartFile document) throws IOException , DocumentNameExistsException {
        DocumentResponseDto documentResponseDto = documentService.saveDocument(document);
        return new ResponseEntity<>(documentResponseDto, OK);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<List<DocumentResponseDto>> delecteDocument(@PathVariable("documentId") Long documentId) throws IOException {
        this.documentService.delecteDocument(documentId);
        List<DocumentResponseDto> documentResponseDtos = this.documentService.findAllDocuments();
        return new ResponseEntity<>(documentResponseDtos, OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<DocumentResponseDto>> findDocumentsByCategory(@PathVariable("categoryId") String categoryId) {
        List<DocumentResponseDto> documentResponseDtos = this.documentService.findDocumentsByCategory(categoryId);
        return new ResponseEntity<>(documentResponseDtos, OK);
    }

    private DocumentRequestDto constructDocument(Path newFile) throws IOException {

        DocumentRequestDto document = new DocumentRequestDto();
        document.setTitle(newFile.getFileName().toString());
        document.setContent(Files.readString(newFile));

        return document;
    }
}
