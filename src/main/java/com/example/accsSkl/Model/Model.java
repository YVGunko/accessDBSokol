package com.example.accsSkl.Model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Model {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unused")
	private Model() {
		super();
	}
	@NotNull
	private Long id;
	@NotNull
	private String name;

	public Model (Long id, String name)	{
		this.id = id;
		this.name = name;
	}
}
