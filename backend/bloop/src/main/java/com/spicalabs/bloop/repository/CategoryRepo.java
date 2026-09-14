package com.spicalabs.bloop.repository;

import com.spicalabs.bloop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, UUID> {

}