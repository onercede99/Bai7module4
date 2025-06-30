package com.codegym.service.impl;

import com.codegym.model.Category;
import com.codegym.repository.ICategoryRepository;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository icategoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return icategoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        icategoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return icategoryRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        icategoryRepository.deleteById(id);
    }
}
