package com.example.accsSkl.Client;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
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
	private Client() {
		super();
	}

	@NotNull
	private Long id;

	@NotNull
	private String name;

	public Client (Long id, String name)	{
		this.id = id;
		this.name = name;
	}
}