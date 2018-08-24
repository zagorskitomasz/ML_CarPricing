package com.zagorskidev.carpricing.service.loaders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAR_TYPES")
public class SimpleCategory 
{
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PARENT")
	private Integer parent;

	public SimpleCategory()
	{
		
	}
	
	public SimpleCategory(Integer id, String name, Integer parent) 
	{
		this.id = id;
		this.name = name;
		this.parent = parent;
	}

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Integer getParent() 
	{
		return parent;
	}

	public void setParent(Integer parent) 
	{
		this.parent = parent;
	}
}
