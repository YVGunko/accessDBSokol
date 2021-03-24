package com.example.accsSkl.Brand;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Brand {
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
	private Brand() {
		super();
	}
	@NotNull
	private Long id;
	@NotNull
	private String name;

	public Brand (Long id, String name)	{
		this.id = id;
		this.name = name;
	}
}
