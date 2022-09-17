package com.example.blog_api.controller;

import com.example.blog_api.Form.BlogForm;
import com.example.blog_api.model.Blog;

import com.example.blog_api.model.Cover;
import com.example.blog_api.service.BlogService;
import com.example.blog_api.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CoverService coverService;

    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> listBlogs(@RequestParam(name = "cateid") Optional<Integer> cateid) {

        try {
            List<Blog> blogs = new ArrayList<Blog>();

            System.out.println(blogs);
            if (cateid.isPresent()) {
                blogs = blogService.listAllFilter(cateid.get());
            } else {
                blogs = blogService.getAll();
            }

            if (blogs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable(name = "id") int id) {
        Blog blog = blogService.findById(id).get();
        if (blog == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }


    //    @PostMapping("/blogs")
//    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {
//        Blog blog1 = blogService.insert(blog);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("blog", "/api/blogs/" + blog1.getId());
//        return new ResponseEntity<>(blog1, httpHeaders, HttpStatus.CREATED);
//    }
    @PostMapping("/blogs")
    public ResponseEntity<Blog> addBLog(@ModelAttribute("blog") @RequestBody BlogForm blogForm, BindingResult bindingResult) throws IOException {
        if (!bindingResult.hasErrors()) {
            Blog blog = new Blog.BlogBuilder(blogForm.getTitle())
                    .content(blogForm.getContent()).build();
            blog.setCategory(blogForm.getCategory());
            blog.setAuthor(blogForm.getAuthor());
            blogService.insert(blog);
//            System.out.println(blogForm.getCategory().getName());
            for (MultipartFile file : blogForm.getFiles()) {
                String fileName = file.getOriginalFilename();
                var is = file.getInputStream();
                try {
                    Files.copy(is, Paths.get(this.fileUpload + fileName), StandardCopyOption.REPLACE_EXISTING);
//                FileCopyUtils.copy(blogForm.getFiles().get().getBytes(), new File(this.fileUpload + fileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Cover cover = new Cover(fileName, blog);
                coverService.save(cover);
            }
            return new ResponseEntity<Blog>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<Blog> updateTodo(@PathVariable("id") int id, @RequestBody Blog blog) {
        blogService.update(id, blog);
        return new ResponseEntity<>(blogService.findById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable(name = "id") int id) {
        coverService.deleteByBlogId(id);
        blogService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/blogs/search")
    public ResponseEntity<List<Blog>> searchBlog(@RequestParam(name = "key") String key) {
        try {
            List<Blog> blogs = new ArrayList<Blog>();
//            blogs = blogService.findByAuthorName(key);
            blogs = blogService.findBlogsBySearch(key);
            System.out.println(blogs);

            if (blogs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


//
//}