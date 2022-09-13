package com.example.buoi4_bt1.Controllers;

import com.example.buoi4_bt1.Form.BlogForm;
import com.example.buoi4_bt1.Models.Blog;
import com.example.buoi4_bt1.Models.Category;
import com.example.buoi4_bt1.Services.IBlogService;
import com.example.buoi4_bt1.Services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ModelAndView listBlogs(Model model, @RequestParam("cateid")Optional<Integer> cateid) {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Blog> listBlog = new ArrayList<>();
        model.addAttribute("listCategory", iCategoryService.getAll());

        if(cateid.isPresent()){
//            listBlog = iBlogService.getBlogByCategoryId(cateid);
            modelAndView.addObject("listBlogs", iCategoryService.findById(cateid.get()).get().getBlog());
        }
        else{
            listBlog = iBlogService.getAll();

        }
        model.addAttribute("listBlogs", listBlog);
        return modelAndView;
    }

    @PostMapping("/index/cateid/{cateid}")
    public String listBlogs(@PathVariable("cateid") int cateid, Model model) {
        List<Blog> listBlog = new ArrayList<>();
        if(cateid == 0){
            listBlog = iBlogService.getAll();
        }
        else{
            listBlog  = iBlogService.getBlogByCategoryId(cateid);
        }
        String categoryid = (String) model.getAttribute("categoryid");
        int categoryid1 = 0;
        try {
            categoryid1 = Integer.parseInt(categoryid);
        }catch (Exception e){}
        if (categoryid1==0){
            listBlog  = iBlogService.getAll();
        }else{
            listBlog = iBlogService.getBlogByCategoryId(categoryid1);
        }

        model.addAttribute("listCategory", iCategoryService.getAll());
        model.addAttribute("listBlogs", listBlog);
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
    public String addBLog(@ModelAttribute("blog") @Valid @RequestBody BlogForm blog, BindingResult bindingResult, Model model) throws Exception {
        if(bindingResult.hasErrors()){

            model.addAttribute("listCategory", iCategoryService.getAll());
            return "add-blog";
        }
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
        return "redirect:/blogs/index";
    }



    @GetMapping("/editblog/{id}")
    public String showUpdateBlogForm(@PathVariable("id") int id, Model model){
        Blog blog = iBlogService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog id:" + id));
        List<Category> listCate = iCategoryService.getAll();
        List<Category> listCate1 = new ArrayList<>();
        for (Category category: listCate) {
            if(category.getId() != blog.getCategory().getId()){
                listCate1.add(category);
            }
        }
        model.addAttribute("listCategory", listCate1);
        model.addAttribute("blog",blog);
        return "update-blog";
    }

    @PostMapping("updateblog/{id}")
    public String updateBlog(@PathVariable("id") int id, @Validated Blog blog, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("listCategory", iCategoryService.getAll());
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

//    @GetMapping("/filter/{cateid}")
//    public String filterCate(@PathVariable("cateid") int id,@Validated Category category, Model model, int categoryid){
//        List<Blog> listBlog  = iBlogService.getBlogByCategoryId(id);
//
//        model.addAttribute("listCategory", iCategoryService.getAll());
//        model.addAttribute("listBlogs", iBlogService.getAll());
//
//        return "index";
//    }
}
