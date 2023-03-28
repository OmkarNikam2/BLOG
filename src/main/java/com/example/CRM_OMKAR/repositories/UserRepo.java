package com.example.CRM_OMKAR.repositories;

import com.example.CRM_OMKAR.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
