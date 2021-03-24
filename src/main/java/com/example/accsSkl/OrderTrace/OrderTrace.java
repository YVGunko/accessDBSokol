package com.example.accsSkl.OrderTrace;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.example.accsSkl.Brand.Brand;
import com.example.accsSkl.Client.Client;
import com.example.accsSkl.Contragent.Contragent;
import com.example.accsSkl.Season.Season;

public class OrderTrace {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameOfTrace() {
		return nameOfTrace;
	}

	public void setNameOfTrace(String nameOfTrace) {
		this.nameOfTrace = nameOfTrace;
	}

	public Long getDateOfTrace() {
		return dateOfTrace;
	}

	public void setDateOfTrace(Long dateOfTrace) {
		this.dateOfTrace = dateOfTrace;
	}

	public Contragent getSender() {
		return sender;
	}

	public void setSender(Contragent sender) {
		this.sender = sender;
	}

	public Contragent getReceiver() {
		return receiver;
	}

	public void setReceiver(Contragent receiver) {
		this.receiver = receiver;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Long getNumberOfOrder() {
		return numberOfOrder;
	}

	public void setNumberOfOrder(Long numberOfOrder) {
		this.numberOfOrder = numberOfOrder;
	}

	@SuppressWarnings("unused")
	private OrderTrace() {
		super();
	}
	@NotNull
	private Long id;
	@NotNull
	private String nameOfTrace;
	@NotNull
	private Long dateOfTrace;
	@NotNull
	@ManyToOne(optional = false)
	private Contragent sender ;
	@NotNull
	@ManyToOne(optional = false)
	private Contragent receiver ;
	@NotNull
	@ManyToOne(optional = false)
	private Client client ;
	@NotNull
	@ManyToOne(optional = false)
	private Brand brand ;
	@NotNull
	@ManyToOne(optional = false)
	private Season season;
	private Long numberOfOrder;
	
	public OrderTrace (Long id, Long numberOfOrder, Long dateOfTrace, String nameOfTrace, 
			Long sender_id, String sender_name, int sender_sequence,
			Long receiver_id, String receiver_name, int receiver_sequence,
			Long client_id, String client_name,
			Long brand_id, String brand_name, 
			Long season_id, String season_name)	{
		this.id = id;
		this.numberOfOrder = numberOfOrder;
		this.dateOfTrace = dateOfTrace;
		this.nameOfTrace = nameOfTrace;
		this.sender = new Contragent(sender_id, sender_name, sender_sequence);
		this.receiver = new Contragent(receiver_id, receiver_name, receiver_sequence);
		this.client = new Client(client_id, client_name);
		this.brand = new Brand(brand_id, brand_name);
		this.season = new Season(season_id, season_name);
	}
	
	public OrderTrace (Long id, Long numberOfOrder, Long dateOfTrace, String nameOfTrace, 
			Long sender_id,
			Long receiver_id,
			Long client_id,
			Long brand_id,
			Long season_id)	{
		this.id = id;
		this.numberOfOrder = numberOfOrder;
		this.dateOfTrace = dateOfTrace;
		this.nameOfTrace = nameOfTrace;
		this.sender = new Contragent(sender_id, "", 0);
		this.receiver = new Contragent(receiver_id, "", 0);
		this.client = new Client(client_id, "");
		this.brand = new Brand(brand_id, "");
		this.season = new Season(season_id, "");
	}
	
	public OrderTrace (Long id)	{
		this.id = id;
	}
}
