package com.example.buoi4_bt1.Repositories;

import com.example.buoi4_bt1.Models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
