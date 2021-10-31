package com.ailysis.documentsanalyzer.service;

import com.ailysis.documentsanalyzer.domain.model.Category;
import com.ailysis.documentsanalyzer.domain.model.Document;

import java.util.List;

public interface CategoryService {

    List<Category> mapCategoriesFromApi(List<ai.expert.nlapi.v2.model.Category> apiCategories, Document document);
}
