package com.example.blog_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "blogID")
    @JsonIgnore
    private Blog blog;

    public Cover(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cover(String name, Blog blog) {
        this.name = name;
        this.blog = blog;
    }
    public Cover(CoverBuiler coverBuiler){
        this.name = coverBuiler.name;
        this.blog = coverBuiler.blog;
    }
    public static class CoverBuiler{
        private final String name;
        private Blog blog;

        public CoverBuiler(String name){
            this.name = name;
        }

        public CoverBuiler blog(Blog blog){
            this.blog = blog;
            return this;
        }

        public Cover build(){
            return new Cover(this);
        }
    }
}
