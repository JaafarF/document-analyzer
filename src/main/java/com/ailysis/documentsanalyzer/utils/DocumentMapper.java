package com.ailysis.documentsanalyzer.utils;

import com.ailysis.documentsanalyzer.domain.dto.CategoryDto;
import com.ailysis.documentsanalyzer.domain.dto.DocumentResponseDto;
import com.ailysis.documentsanalyzer.domain.model.Document;
import com.ailysis.documentsanalyzer.domain.model.Hierarchy;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentMapper {


    public static List<Hierarchy> mapHierarchy(List<Hierarchy> hierarchies, List<String> apiHierarchies) {

        return hierarchies;
    }

    public static DocumentResponseDto mapToDocumentResponseDto(Document document) {
        DocumentResponseDto documentResponseDto = new DocumentResponseDto();

        /*documentResponseDto.setId(document.getId());
        documentResponseDto.setTitle(document.getTitle());
        documentResponseDto.setContent(document.getContent());
        documentResponseDto.setPath(document.getPath());
        documentResponseDto.setSize(document.getSize());
        documentResponseDto.setLanguage(document.getLanguage());
        documentResponseDto.setVersion(document.getVersion());
        documentResponseDto.set(document.get);
        documentResponseDto.set(document.get);*/

        BeanUtils.copyProperties(document, documentResponseDto);
        documentResponseDto.setCategories(new ArrayList<>());
        document.getCategories().forEach(category -> {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            categoryDto.setLabel(category.getCategoryBase().getLabel());
            categoryDto.setCategoryBaseId(category.getCategoryBase().getId());
            String hierarchies = category.getCategoryBase().getHierarchies().stream().map(Hierarchy::getName).collect(Collectors.joining("/"));
            categoryDto.setHierarchy(hierarchies);
            documentResponseDto.getCategories().add(categoryDto);
        });
        return documentResponseDto;
    }
}
