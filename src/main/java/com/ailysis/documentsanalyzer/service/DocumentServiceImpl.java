package com.ailysis.documentsanalyzer.service;

import ai.expert.nlapi.v2.cloud.Categorizer;
import ai.expert.nlapi.v2.message.CategorizeResponse;
import ai.expert.nlapi.v2.model.Category;
import ai.expert.nlapi.v2.model.DocumentPosition;
import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.domain.model.CategoryBase;
import com.ailysis.documentsanalyzer.domain.model.Document;
import com.ailysis.documentsanalyzer.domain.model.Hierarchy;
import com.ailysis.documentsanalyzer.domain.model.Position;
import com.ailysis.documentsanalyzer.repository.DocumentRepository;
import com.ailysis.documentsanalyzer.utils.FilesUtils;
import com.ailysis.documentsanalyzer.utils.NlpAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class DocumentServiceImpl implements DocumentService{

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    CategoryBaseService categoryBaseService;

    @Autowired
    HierarchyService hierarchyService;

    @Autowired
    NlpAuthentication nlpAuthentication;

    @Autowired
    FilesUtils filesUtils;


    @Override
    public List<Document> findAllDocuments() {
        return this.documentRepository.findAll();
    }

    @Override
    public List<Document> findDocumentsByCategory(String categoryBaseId) {
        return this.documentRepository.findByCategories_CategoryBase_Id(categoryBaseId);
    }

    @Override
    public Document saveDocument(DocumentRequestDto documentDto) {

        Document document = new Document();

        // Save the document to the file system
        document.setInitialContent(filesUtils.saveFile(documentDto.getInitialContent(), documentDto.getTitle()));

        // Call nlp analysis
        try {

            // Call nlp API Categorize
            Categorizer categorizer = nlpAuthentication.createCategorizer();
            CategorizeResponse categorization = categorizer.categorize(documentDto.getInitialContent());


            document.setLanguage(categorization.getData().getLanguage());
            document.setVersion(categorization.getData().getVersion());

            //document.setCategories();

            // Handle Categories
            List<com.ailysis.documentsanalyzer.domain.model.Category> categories = new ArrayList<>();

            for (Category cat : categorization.getData().getCategories()) {
                com.ailysis.documentsanalyzer.domain.model.Category cd = new com.ailysis.documentsanalyzer.domain.model.Category();
                cd.setScore(cat.getScore());
                cd.setWinner(cat.getWinner());
                cd.setNamespace(cat.getNamespace());
                cd.setFrequency(cat.getFrequency());
                for (DocumentPosition dp : cat.getPositions()) {
                    Position p = new Position();
                    BeanUtils.copyProperties(dp, p);
                    cd.addPosition(p);
                }

                CategoryBase cb = categoryBaseService.findById(cat.getId());
                if (Objects.isNull(cb)) {
                    CategoryBase categoryBase = new CategoryBase();
                    categoryBase.setId(cat.getId());
                    categoryBase.setLabel(cat.getLabel());

                    // Hierarchy management
                    for (String hierarch : cat.getHierarchy()) {
                        Hierarchy hierarchy = this.hierarchyService.findByName(hierarch);
                        if (Objects.isNull(hierarchy)) {
                            Hierarchy newHiarHierarchy = new Hierarchy();
                            newHiarHierarchy.setName(hierarch);
                            categoryBase.addHierarchy(newHiarHierarchy);
                        } else {
                            categoryBase.addHierarchy(hierarchy);
                        }
                    }

                    categoryBase.addCategory(cd);
                } else {
                    cb.addCategory(cd);
                }



                document.addCategory(cd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return this.documentRepository.save(document);
    }


}
