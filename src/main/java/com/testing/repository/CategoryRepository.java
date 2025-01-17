package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testing.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
