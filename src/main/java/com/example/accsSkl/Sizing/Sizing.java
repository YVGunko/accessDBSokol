package com.example.accsSkl.Sizing;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sizing {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@SuppressWarnings("unused")
	private Sizing() {
		super();
	}
	@NotNull
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private int quantity;

	public Sizing (Long id, String name, int quantity)	{
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}
}
