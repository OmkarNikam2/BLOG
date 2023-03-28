package com.example.CRM_OMKAR.repositories;

import com.example.CRM_OMKAR.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
