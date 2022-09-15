package com.example.buoi8_th_ajax.Services;

import com.example.buoi8_th_ajax.Models.Company;
import com.example.buoi8_th_ajax.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanyList() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).get();
    }

    public void saveOrUpdateCompany(Company company) {
        companyRepository.save(company);
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}
