package com.ailysis.documentsanalyzer.service;

import ai.expert.nlapi.v2.model.Category;
import com.ailysis.documentsanalyzer.domain.model.CategoryBase;
import com.ailysis.documentsanalyzer.repository.CategoryBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryBaseServiceImpl implements CategoryBaseService{

    @Autowired
    private CategoryBaseRepository categoryBaseRepository;

    @Autowired
    private HierarchyService hierarchyService;
    
    @Override
    public CategoryBase findById(String id) {
        return this.categoryBaseRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryBase mapCategoryBaseFromApi(Category apiCategory) {

        CategoryBase categoryBase = this.findById(apiCategory.getId());
        if (Objects.isNull(categoryBase)) {
            categoryBase = new CategoryBase();
            categoryBase.setId(apiCategory.getId());
            categoryBase.setLabel(apiCategory.getLabel());
            categoryBase.setHierarchies(hierarchyService.mapHierarchyFromApi(apiCategory.getHierarchy()));
        }
        return categoryBase;
    }
}
