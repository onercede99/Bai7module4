package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> listCategories(){
        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView listBlog(){
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        Iterable<Blog> blogs = blogService.findAll();
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("blog")  Blog blog, RedirectAttributes redirectAttributes){
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Create new blog successfully!");
        return "redirect:/blogs";
    }
 
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable("id") Long id){
        Optional<Blog> optionalBlog = blogService.findById(id);
        if(optionalBlog.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/blog/update");
            modelAndView.addObject("blog",optionalBlog.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("blog") Blog blog, RedirectAttributes redirectAttributes){
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Update blog successfully!");
        return "redirect:/blogs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        blogService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Delete blog successfully!");
        return "redirect:/blogs";
    }
}
