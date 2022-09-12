package com.example.buoi4_bt1.Controllers;

import com.example.buoi4_bt1.Form.BlogForm;
import com.example.buoi4_bt1.Models.Blog;
import com.example.buoi4_bt1.Repositories.BlogRepository;
import com.example.buoi4_bt1.Services.IBlogService;
import com.example.buoi4_bt1.Services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private ICategoryService iCategoryService;

    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping("/index")
    public String listBlogs(Model model) {
        model.addAttribute("listBlogs", iBlogService.getAll());
        return "index";
    }

//    @GetMapping("/addblogform")
//    public String showAddBlogForm(BlogForm blog, Model model) {
//        model.addAttribute("listCategory", iCategoryService.getAll());
//        return "add-blog";
//    }

    //    @PostMapping("/addblog")
//    public String addBlog(@Validated Blog blog, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-blog";
//
//            iBlogService.save(blog);
//        return "redirect:/blogs/index";
//    }


    @GetMapping("/addblog")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/add-blog");
        modelAndView.addObject("blog", new BlogForm());
        modelAndView.addObject("listCategory", iCategoryService.getAll());
        return modelAndView;
    }

    @PostMapping("/addblog")
    public RedirectView createProduct(@ModelAttribute BlogForm blog){
        Blog blog1 = new Blog.BlogBuilder(blog.getTitle())
                .content(blog.getContent()).build();
        MultipartFile multipartFile = blog.getCover();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(blog.getCover().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog1.setCover(fileName);
        blog1.setCategory(blog.getCategory());
        iBlogService.save(blog1);
        return new RedirectView("/blogs/index");
    }

    @GetMapping("/editblog/{id}")
    public String showUpdateBlogForm(@PathVariable("id") int id, Model model){
        Blog blog = iBlogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog id:" + id));
        model.addAttribute("listCategory", iCategoryService.getAll());
        model.addAttribute("blog",blog);
        return "update-blog";
    }

    @PostMapping("updateblog/{id}")
    public String updateBlog(@PathVariable("id") int id, @Validated Blog blog, BindingResult result, Model model){
        if(result.hasErrors()){
            return "update-blog";
        }

        iBlogService.save(blog);
        return "redirect:/blogs/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") int id, Model model) {
        Blog blog = iBlogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        iBlogService.delete(id);
        return "redirect:/blogs/index";
    }
}
