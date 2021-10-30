package com.ailysis.documentsanalyzer.controller;

import ai.expert.nlapi.v2.cloud.Categorizer;
import ai.expert.nlapi.v2.message.CategorizeResponse;
import ai.expert.nlapi.v2.model.Category;
import ai.expert.nlapi.v2.model.DocumentPosition;
import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.domain.model.CategoryBase;
import com.ailysis.documentsanalyzer.domain.model.Document;
import com.ailysis.documentsanalyzer.domain.model.Position;
import com.ailysis.documentsanalyzer.service.DocumentService;
import com.ailysis.documentsanalyzer.utils.NlpAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public List<Document> findAllDocuments() {
        return this.documentService.findAllDocuments();
    }

    @GetMapping("/save")
    public Document saveDocument() {
        return this.documentService.saveDocument(constructDocument());
    }

    @GetMapping("/category/{categoryId}")
    public List<Document> findDocumentsByCategory(@PathVariable("categoryId") String categoryId) {
        return this.documentService.findDocumentsByCategory(categoryId);
    }

    private DocumentRequestDto constructDocument() {
        String text = "NEW YORK -- James Harden was in his bag Friday night, dribbling, creating space, draining 3s and letting everyone know that he was starting to feel more like himself as he yelled out during a timeout while clenching his fists and flexing his arms.\n" +
                "\n" +
                "Harden also was finally back at a place he knows well -- the free throw line. After going to the line a combined 15 times in his first five games, Harden went 16-for-19 from the stripe and scored a season-high 29 points to go with eight assists and eight rebounds to help the Nets take a 105-98 win over the Indiana Pacers at Barclays Center.\n" +
                "\n" +
                "This was, by far, Harden's best game of the season after getting off to a slow start and averaging 16.6 points entering Friday.";

        DocumentRequestDto document = new DocumentRequestDto();
        document.setTitle("my-basket.txt");
        document.setSize(75);
        document.setInitialContent(text);

        return document;
    }
}
