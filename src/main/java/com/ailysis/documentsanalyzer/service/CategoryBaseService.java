package com.ailysis.documentsanalyzer.service;

import ai.expert.nlapi.v2.model.Category;
import com.ailysis.documentsanalyzer.domain.model.CategoryBase;

import java.util.List;

public interface CategoryBaseService {

    CategoryBase findById(String id);

    CategoryBase mapCategoryBaseFromApi(Category apiCategory);
}
