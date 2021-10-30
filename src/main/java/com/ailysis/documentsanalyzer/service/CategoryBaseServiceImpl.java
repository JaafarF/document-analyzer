package com.ailysis.documentsanalyzer.service;

import com.ailysis.documentsanalyzer.domain.model.CategoryBase;
import com.ailysis.documentsanalyzer.repository.CategoryBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryBaseServiceImpl implements CategoryBaseService{

    @Autowired
    private CategoryBaseRepository categoryBaseRepository;
    @Override
    public CategoryBase findById(String id) {
        return this.categoryBaseRepository.findById(id).orElse(null);
    }
}
