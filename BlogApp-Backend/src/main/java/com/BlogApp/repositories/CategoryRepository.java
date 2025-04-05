package com.BlogApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

	Optional<Category> findCategoryByName(String categoryname);
}
