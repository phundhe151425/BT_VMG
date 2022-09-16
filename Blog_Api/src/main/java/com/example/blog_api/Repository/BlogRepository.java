package com.example.blog_api.Repository;

import com.example.blog_api.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> getBlogByCategory_Id(int id);

    @Query(value = "select * from blog b join author a on b.authorid=a.id  where a.name LIKE %?1%", nativeQuery = true)
    List<Blog> findBlogsByAuthor_Name(String authorName);

    @Query(value = "select * from blog b \n" +
            "join author a on b.authorid=a.id\n" +
            "join category c on b.categoryid=c.id\n" +
            "where a.name LIKE %?1%\n" +
            "or c.name LIKE %?1%\n" +
            "or  b.title LIKE %?1%",nativeQuery = true)
    List<Blog> findBlogsBySearch(String key);



}
