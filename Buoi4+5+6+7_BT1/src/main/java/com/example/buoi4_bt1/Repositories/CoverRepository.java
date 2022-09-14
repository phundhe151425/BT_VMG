package com.example.buoi4_bt1.Repositories;

import com.example.buoi4_bt1.Models.Cover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverRepository extends JpaRepository<Cover, Integer> {
}
