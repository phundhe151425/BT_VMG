package com.example.buoi5_th.Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String image;

    public Product(ProductBuilder productBuilder) {
        this.name = productBuilder.name;
        this.description = productBuilder.description;
        this.image = productBuilder.image;
    }

    public Product() {
    }

    public static class ProductBuilder {
        private final String name;
        private String description;
        private String image;

        public ProductBuilder(String name) {
            this.name = name;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder image(String image) {
            this.image = image;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
