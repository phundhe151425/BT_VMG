package com.example.buoi4_bt1.Services.Impl;

import com.example.buoi4_bt1.Models.Blog;
import com.example.buoi4_bt1.Models.Cover;
import com.example.buoi4_bt1.Repositories.CoverRepository;
import com.example.buoi4_bt1.Services.ICoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverServiceImpl implements ICoverService {
    @Autowired
    private CoverRepository coverRepository;
    @Override
    public Cover save(Cover cover) {
        return coverRepository.save(cover);
    }

    @Override
    public void deleteByBlogID(Blog blog) {
        List<Cover> listCovers = coverRepository.findAll();
        for (Cover c: listCovers) {
            if(c.getBlog().getId() == blog.getId()){
                coverRepository.deleteById(c.getId());
            }
        }

    }
}
