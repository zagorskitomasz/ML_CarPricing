package com.zagorskidev.carpricing.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PARAMS")
public class Parameter 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int ID;
	
	@Column(name="NAME")
	@NotNull(message="is required")
	private String name;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="CHANGED")
	private Timestamp changed;
	
	public Parameter() {}
	
	public Parameter(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	public int getID() 
	{
		return ID;
	}

	public void setID(int iD) 
	{
		ID = iD;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getValue() 
	{
		return value;
	}

	public void setValue(String value) 
	{
		this.value = value;
	}

	public Timestamp getChanged() 
	{
		return changed;
	}

	public void setChanged(Timestamp changed) 
	{
		this.changed = changed;
	}
}
