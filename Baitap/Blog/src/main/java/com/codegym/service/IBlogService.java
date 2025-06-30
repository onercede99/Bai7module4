package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Category;

public interface IBlogService extends IGenerateService<Blog> {
    Iterable<Blog> findAllByCategory(Category category);
}
