package com.example.buoi8_th_ajax.Controllers;

import com.example.buoi8_th_ajax.Models.Company;
import com.example.buoi8_th_ajax.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companyList")
    public ResponseEntity<List<Company>> getCompanyList(){
        return new ResponseEntity<List<Company>>(companyService.getCompanyList(), HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<Company>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @PostMapping("/company/save")
    public ResponseEntity<Void> saveOrUpdateCompany(@RequestBody Company company){
        companyService.saveOrUpdateCompany(company);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<Void> deleteCompanyById(@PathVariable(name = "id")Integer id){
        companyService.deleteCompany(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
