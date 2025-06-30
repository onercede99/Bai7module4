package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface IBlogRepository extends CrudRepository<Blog, Long> {
    Iterable<Blog> findAllByCategory(Category category);
}
