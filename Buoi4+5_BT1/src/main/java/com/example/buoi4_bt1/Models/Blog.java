package com.example.buoi4_bt1.Models;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


@Entity
@Table(name="blog")
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "cover")
    private String cover;
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name="categoryID")
    private Category category;


    public Blog(BlogBuilder blogBuilder) {
        this.title = blogBuilder.title;
        this.content = blogBuilder.content;
        this.cover = blogBuilder.cover;
    }

    public Blog() {
    }
    public static class BlogBuilder {
        private final String title;
        private String cover;
        private String content;

        public BlogBuilder(String title) {
            this.title = title;
        }

        public BlogBuilder cover(String cover) {
            this.cover = cover;
            return this;
        }

        public BlogBuilder content(String content) {
            this.content = content;
            return this;
        }

        public Blog build() {
            return new Blog(this);
        }
    }
}
