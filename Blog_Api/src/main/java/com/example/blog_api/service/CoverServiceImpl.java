package com.example.blog_api.service;

import com.example.blog_api.Repository.CoverRepository;
import com.example.blog_api.model.Blog;
import com.example.blog_api.model.Cover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverServiceImpl implements CoverService{

    @Autowired
    private CoverRepository coverRepository;

    @Override
    public void deleteByBlogId(int blogId) {
        List<Cover> listCovers = coverRepository.findAll();
        for (Cover c: listCovers) {
            if(c.getBlog().getId() == blogId){
                coverRepository.deleteById(c.getId());
            }
        }
    }
}
