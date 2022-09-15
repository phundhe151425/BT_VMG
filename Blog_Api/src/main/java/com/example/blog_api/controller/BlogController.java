package com.example.blog_api.controller;

import com.example.blog_api.model.Blog;

import com.example.blog_api.service.BlogService;
import com.example.blog_api.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Blog>> listBlogs(@RequestParam(name = "cateid") Optional<Integer> cateid){

        try{
            List<Blog> blogs = new ArrayList<Blog>();

            System.out.println(blogs);
            if(cateid.isPresent()) {
//                blogs = iCategoryService.findById(cateid.get()).get().getBlog();
                blogs = blogService.listAllFilter(cateid.get());
            }
            else {
                blogs = blogService.getAll();
            }

            if (blogs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(blogs, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable(name = "id") int id){
        Blog blog = blogService.findById(id).get();
        if (blog == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }


    @PostMapping("/blogs")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {
        Blog blog1 = blogService.insert(blog);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("blog", "/api/blogs/" + blog1.getId());
        return new ResponseEntity<>(blog1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<Blog> updateTodo(@PathVariable("id") int id, @RequestBody Blog blog) {
        blogService.update(id, blog);
        return new ResponseEntity<>(blogService.findById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable(name = "id") int id){
        coverService.deleteByBlogId(id);
        blogService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/blogs/search")
    public ResponseEntity<List<Blog>> searchBlog(@RequestParam(name = "key") Optional<String> key){
        try{
            List<Blog> blogs = new ArrayList<Blog>();

            System.out.println(blogs);
            if(key.isPresent()) {
                blogs = blogService.findByAuthorName(key.get());
//                blogs = blogService.findBlogsBySearch(key.get(),key.get(),key.get());
            }
            else {
                blogs = blogService.getAll();
            }

            if (blogs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(blogs, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}



//
//}