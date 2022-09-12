package com.example.buoi4_bt1.Form;

import com.example.buoi4_bt1.Models.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class BlogForm {

    private int id;
    private String title;
    private MultipartFile cover;
    private String content;

    private Category category;

    public BlogForm() {
    }

    public BlogForm(BlogFormBuilder blogFormBuilder) {

        this.title = blogFormBuilder.title;
        this.cover = blogFormBuilder.cover;
        this.content = blogFormBuilder.content;
    }

    public static class BlogFormBuilder {
        private String title;
        private MultipartFile cover;
        private String content;

        public BlogFormBuilder(String title) {
            this.title = title;
        }

        public BlogFormBuilder description(MultipartFile cover) {
            this.cover = cover;
            return this;
        }

        public BlogFormBuilder image(String content) {
            this.content = content;
            return this;
        }

        public BlogForm build() {
            return new BlogForm(this);
        }
    }
}
