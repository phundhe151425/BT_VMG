package com.example.buoi4_bt1.Controllers;

import com.example.buoi4_bt1.Models.Blog;
import com.example.buoi4_bt1.Repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/index")
    public String listBlogs(Model model) {
        model.addAttribute("listBlogs", blogRepository.findAll());
        return "index";
    }

    @GetMapping("/addform")
    public String showAddBlogForm(Blog blog, Model model) {
        return "add-blog";
    }

    @PostMapping("/addblog")
    public String addBlog(@Validated Blog blog, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-blog";
        }

        blogRepository.save(blog);
        return "redirect:/index";
    }

    @GetMapping("/updateform/{id}")
    public String showUpdateBlogForm(@PathVariable("id") int id, Model model){
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog id:" + id));

        model.addAttribute("blog",blog);
        return "update-blog";
    }

    @PostMapping("updateblog/{id}")
    public String updateBlog(@PathVariable("id") int id, @Validated Blog blog, BindingResult result, Model model){
        if(result.hasErrors()){
            return "update-blog";
        }

        blogRepository.save(blog);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") int id, Model model) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        blogRepository.delete(blog);
        return "redirect:/index";
    }
}
