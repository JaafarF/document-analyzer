package com.ailysis.documentsanalyzer.service;

import ai.expert.nlapi.v2.cloud.Categorizer;
import ai.expert.nlapi.v2.message.CategorizeResponse;
import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.domain.dto.DocumentResponseDto;
import com.ailysis.documentsanalyzer.domain.model.Document;
import com.ailysis.documentsanalyzer.exception.DocumentNameExistsException;
import com.ailysis.documentsanalyzer.repository.DocumentRepository;
import com.ailysis.documentsanalyzer.utils.DocumentMapper;
import com.ailysis.documentsanalyzer.utils.FilesUtils;
import com.ailysis.documentsanalyzer.utils.NlpAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.ailysis.documentsanalyzer.constant.Contants.USER_FOLDER;

@Service
@Transactional
@Slf4j
public class DocumentServiceImpl implements DocumentService{

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    NlpAuthentication nlpAuthentication;


    @Override
    public List<DocumentResponseDto> findAllDocuments() {
        List<Document> documents = this.documentRepository.findAll();
        List<DocumentResponseDto> dtos = new ArrayList<>();
        documents.forEach(doc -> {
            DocumentResponseDto dto = DocumentMapper.mapToDocumentResponseDto(doc);
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public List<DocumentResponseDto> findDocumentsByCategory(String categoryBaseId) {
        List<Document> documents = this.documentRepository.findByCategoriesCategoryBaseId(categoryBaseId);
        List<DocumentResponseDto> dtos = new ArrayList<>();
        documents.forEach(doc -> {
            DocumentResponseDto dto = DocumentMapper.mapToDocumentResponseDto(doc);
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public DocumentResponseDto saveDocument(MultipartFile documentDto) throws IOException, DocumentNameExistsException {
        Document document = new Document();

        DocumentRequestDto drdto = FilesUtils.processAndSaveFile(documentDto);

        // Fill document data
        document.setContent(drdto.getContent());
        document.setTitle(drdto.getTitle());
        document.setSize(drdto.getSize());
        document.setPath(drdto.getPath());



        try {
            // Call nlp API Categorize
            Categorizer categorizer = nlpAuthentication.createCategorizer();
            CategorizeResponse categorization = categorizer.categorize(drdto.getContent());

            // Fill document object from api response
            document.setLanguage(categorization.getData().getLanguage());
            document.setVersion(categorization.getData().getVersion());
            document.setCategories(categoryService.mapCategoriesFromApi(categorization.getData().getCategories(), document));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Save document to DB
        return DocumentMapper.mapToDocumentResponseDto(this.documentRepository.save(document));
    }

    @Override
    public void delecteDocument(Long documentId) throws IOException {
        Document document = this.documentRepository.findById(documentId).orElseThrow(() -> new NoResultException("Document not found."));
        FilesUtils.deleteFile(document.getPath());
        this.documentRepository.delete(document);
    }

}
