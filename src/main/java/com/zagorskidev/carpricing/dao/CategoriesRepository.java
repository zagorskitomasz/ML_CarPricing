package com.zagorskidev.carpricing.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zagorskidev.carpricing.service.loaders.SimpleCategory;

public interface CategoriesRepository extends JpaRepository<SimpleCategory,Integer> 
{
	public Collection<SimpleCategory> findByParent(Integer parent);
}
