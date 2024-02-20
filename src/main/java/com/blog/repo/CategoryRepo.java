package com.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
}
