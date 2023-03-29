package com.example.CRM_OMKAR.repositories;

import com.example.CRM_OMKAR.entities.Category;
import com.example.CRM_OMKAR.entities.Post;
import com.example.CRM_OMKAR.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
