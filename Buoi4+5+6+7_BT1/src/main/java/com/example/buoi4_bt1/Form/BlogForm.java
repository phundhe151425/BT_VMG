package com.example.buoi4_bt1.Form;

import com.example.buoi4_bt1.Models.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@Data
public class BlogForm {

    private int id;
    @NotNull
    @Size(min = 3, max = 20, message = "Title have 3-20 characters")
    private String title;

    private Set<CoverForm> coverForms;
    @NotNull(message = "Content is not empty")
    @NotBlank(message = "Content is not blank")
    private String content;

    private Category category;

    private List<MultipartFile> files;

    public BlogForm() {
    }

    public BlogForm(BlogFormBuilder blogFormBuilder) {
        this.title = blogFormBuilder.title;
        this.content = blogFormBuilder.content;
        this.coverForms = blogFormBuilder.coverForms;
    }

    public static class BlogFormBuilder {
        private String title;

        private String content;

        private Set<CoverForm> coverForms;

        private Category category;

        public BlogFormBuilder(String title) {
            this.title = title;
        }

        public BlogFormBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BlogFormBuilder cover(Set<CoverForm> coverForms) {
            this.coverForms = coverForms;
            return this;
        }

        public BlogFormBuilder category(Category category){
            this.category = category;
            return this;
        }

        public BlogForm build() {
            return new BlogForm(this);
        }
    }
}
