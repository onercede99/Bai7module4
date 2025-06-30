package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.ICategoryService;
import com.codegym.service.impl.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public ModelAndView listCategory(){
        ModelAndView modelAndView = new ModelAndView("/category/list");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("categories", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("Category")  Category category, RedirectAttributes redirectAttributes){
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Create new category successfully!");
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/category/update");
            modelAndView.addObject("categories", category.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Update category successfully!");
        return "redirect:/categories";
    }

    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id){
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()){
            return new ModelAndView("/error_404");
        }

        Iterable<Blog> blogs = blogService.findAllByCategory(category.get());

        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }


}
