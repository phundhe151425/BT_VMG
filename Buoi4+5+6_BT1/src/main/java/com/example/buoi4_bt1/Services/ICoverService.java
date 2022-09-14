package com.example.buoi4_bt1.Services;

import com.example.buoi4_bt1.Models.Blog;
import com.example.buoi4_bt1.Models.Cover;

public interface ICoverService {
    public Cover save(Cover cover);

    public void deleteByBlogID(Blog blog);
}
