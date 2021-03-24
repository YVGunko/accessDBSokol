package com.example.accsSkl.Contragent;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contragent {
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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@SuppressWarnings("unused")
	private Contragent() {
		super();
	}

	@NotNull
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private int sequence;

	public Contragent (Long id, String name, int sequence)	{
		this.id = id;
		this.name = name;
		this.sequence = sequence;
	}
}
