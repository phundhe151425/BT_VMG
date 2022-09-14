package com.example.buoi4_bt1.Models;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "blog")
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
    @NotBlank(message = "Title is mandatory")
    private String title;
    //    @Column(name = "cover")
//    @NotBlank(message = "Cover is mandatory")
//    private String cover;
    @OneToMany(mappedBy = "blog")
    private Set<Cover> covers;
    @Column(name = "content")
    @NotBlank(message = "Content is mandatory")
    private String content;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    @Transient
    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public Set<Cover> getCovers() {
        return covers;
    }

    public void setCovers(Set<Cover> covers) {
        this.covers = covers;
    }


    public Blog() {
    }

    public Blog(BlogBuilder blogBuilder) {
        this.title = blogBuilder.title;
        this.content = blogBuilder.content;
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
