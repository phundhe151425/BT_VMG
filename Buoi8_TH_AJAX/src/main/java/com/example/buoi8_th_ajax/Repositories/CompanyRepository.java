package com.example.buoi8_th_ajax.Repositories;

import com.example.buoi8_th_ajax.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
