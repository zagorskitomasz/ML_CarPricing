package com.zagorskidev.carpricing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zagorskidev.carpricing.domain.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter,Integer> 
{
	public List<Parameter> findParametersByName(String name);
}
