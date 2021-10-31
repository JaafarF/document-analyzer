package com.ailysis.documentsanalyzer.service;

import ai.expert.nlapi.v2.model.DocumentPosition;
import com.ailysis.documentsanalyzer.domain.model.Category;
import com.ailysis.documentsanalyzer.domain.model.CategoryBase;
import com.ailysis.documentsanalyzer.domain.model.Document;
import com.ailysis.documentsanalyzer.domain.model.Position;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryBaseService categoryBaseService;

    public List<Category> mapCategoriesFromApi(List<ai.expert.nlapi.v2.model.Category> apiCategories, Document document) {
        List<Category> categories = new ArrayList<>();

        for (ai.expert.nlapi.v2.model.Category apiCategory : apiCategories) {
            Category category = new Category();
            category.setScore(apiCategory.getScore());
            category.setWinner(apiCategory.getWinner());
            category.setNamespace(apiCategory.getNamespace());
            category.setFrequency(apiCategory.getFrequency());
            for (DocumentPosition dp : apiCategory.getPositions()) {
                Position p = new Position();
                BeanUtils.copyProperties(dp, p);
                category.addPosition(p);
            }

            CategoryBase categoryBase = categoryBaseService.mapCategoryBaseFromApi(apiCategory);
            categoryBase.addCategory(category);
            document.addCategory(category);
            categories.add(category);
        }

        /*apiCategories.forEach(apiCategory -> {
            Category category = new Category();
            category.setScore(apiCategory.getScore());
            category.setWinner(apiCategory.getWinner());
            category.setNamespace(apiCategory.getNamespace());
            category.setFrequency(apiCategory.getFrequency());

            apiCategory.getPositions().forEach(apiPosition -> {
                Position position = new Position();
                BeanUtils.copyProperties(apiPosition, position);
                category.addPosition(position);
            });

            CategoryBase categoryBase = categoryBaseService.mapCategoryBaseFromApi(apiCategory);
            categoryBase.addCategory(category);

            categories.add(category);
        });*/
        return categories;
    }
}
