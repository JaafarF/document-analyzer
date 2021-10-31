package com.ailysis.documentsanalyzer.service;

import ai.expert.nlapi.v2.cloud.Categorizer;
import ai.expert.nlapi.v2.message.CategorizeResponse;
import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.domain.model.Document;
import com.ailysis.documentsanalyzer.exception.DocumentNameExistsException;
import com.ailysis.documentsanalyzer.repository.DocumentRepository;
import com.ailysis.documentsanalyzer.utils.FilesUtils;
import com.ailysis.documentsanalyzer.utils.NlpAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

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
    public List<Document> findAllDocuments() {
        return this.documentRepository.findAll();
    }

    @Override
    public List<Document> findDocumentsByCategory(String categoryBaseId) {
        List<Document> documents = this.documentRepository.findByCategories_CategoryBase_Id(categoryBaseId);
        return documents;
    }

    @Override
    public Document saveDocument(DocumentRequestDto documentDto) throws IOException, DocumentNameExistsException {
        Document document = new Document();
        // Reduce content size
        documentDto = FilesUtils.reduceFile(documentDto);

        try {
            // Call nlp API Categorize
            Categorizer categorizer = nlpAuthentication.createCategorizer();
            CategorizeResponse categorization = categorizer.categorize(documentDto.getContent());

            // Fill document object from api response
            document.setLanguage(categorization.getData().getLanguage());
            document.setVersion(categorization.getData().getVersion());
            document.setCategories(categoryService.mapCategoriesFromApi(categorization.getData().getCategories(), document));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Save the document to the file system
        DocumentRequestDto fileDto = FilesUtils.saveFile(documentDto.getContent(), documentDto.getTitle());

        // Fill document data
        document.setContent(fileDto.getContent());
        document.setTitle(fileDto.getTitle());
        document.setSize(fileDto.getSize());
        document.setPath(fileDto.getPath());
        // Save document to DB
        return this.documentRepository.save(document);
    }

    @Override
    public void delecteDocument(Long documentId) throws IOException {
        Document document = this.documentRepository.findById(documentId).orElseThrow(() -> new NoResultException("Document not found."));
        FilesUtils.deleteFile(document.getPath());
        this.documentRepository.delete(document);
    }

}
