package com.example.blog_api.Form;

import com.example.blog_api.model.Blog;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CoverForm {
    private int id;

    private MultipartFile name;

    private Blog blog;

    public CoverForm() {
    }

    public CoverForm(CoverFormBuilder coverFormBuilder) {
        this.name = coverFormBuilder.name;
        this.blog = coverFormBuilder.blog;
    }

    public CoverForm(MultipartFile file, Blog blog) {
        this.name = file;
        this.blog = blog;
    }
    public static class CoverFormBuilder{
        private final MultipartFile name;

        private Blog blog;

        public CoverFormBuilder(MultipartFile multipartFile){
            this.name = multipartFile;
        }

        public CoverFormBuilder blog(Blog blog) {
            this.blog = blog;
            return this;
        }

        public CoverForm build() {
            return new CoverForm(this);
        }
    }
}
