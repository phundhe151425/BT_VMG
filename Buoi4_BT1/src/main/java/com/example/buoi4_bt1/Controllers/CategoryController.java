package com.example.buoi4_bt1.Controllers;


import com.example.buoi4_bt1.Models.Blog;
import com.example.buoi4_bt1.Models.Category;
import com.example.buoi4_bt1.Services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/index")
    public String listBlogs(Model model) {
        model.addAttribute("listCategory", iCategoryService.getAll());
        return "category";
    }

    @GetMapping("/addcateform")
    public String showAddBlogForm(Category category, Model model) {
        return "add-category";
    }

    @PostMapping("/addcategory")
    public String addCategory(@Validated Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-category";
        }

        iCategoryService.save(category);
        return "redirect:/category/index";
    }

    @GetMapping("/editcate/{id}")
    public String showUpdateCateForm(@PathVariable("id") int id, Model model){
        Category category = iCategoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category id:" + id));

        model.addAttribute("category",category);
        return "update-category";
    }

    @PostMapping("updatecategory/{id}")
    public String updateCate(@PathVariable("id") int id, @Validated Category category, BindingResult result, Model model){
        if(result.hasErrors()){
            return "update-category";
        }

        iCategoryService.save(category);
        return "redirect:/category/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") int id, Model model) {
        Category category = iCategoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog Id:" + id));
        iCategoryService.delete(id);
        return "redirect:/category/index";
    }
}
