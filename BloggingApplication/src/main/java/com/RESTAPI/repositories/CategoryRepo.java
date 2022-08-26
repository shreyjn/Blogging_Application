package com.RESTAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RESTAPI.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
