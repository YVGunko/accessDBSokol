package com.example.accsSkl.Season;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Season {
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
	private Season() {
		super();
	}
	@NotNull
	private Long id;
	@NotNull
	private String name;

	public Season (Long id, String name)	{
		this.id = id;
		this.name = name;
	}
}
