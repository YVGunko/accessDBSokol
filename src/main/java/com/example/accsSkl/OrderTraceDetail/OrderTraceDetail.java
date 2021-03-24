package com.example.accsSkl.OrderTraceDetail;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.accsSkl.Model.Model;
import com.example.accsSkl.OrderTrace.OrderTrace;
import com.example.accsSkl.OrderTrace.OrderTraceRepository;
import com.example.accsSkl.Sizing.Sizing;

public class OrderTraceDetail {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderTrace getOrderTrace() {
		return orderTrace;
	}

	public void setOrderTrace(OrderTrace orderTrace) {
		this.orderTrace = orderTrace;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Sizing getSizing() {
		return sizing;
	}

	public void setSizing(Sizing sizing) {
		this.sizing = sizing;
	}

	public Long getNumberOfOrder() {
		return numberOfOrder;
	}

	public void setNumberOfOrder(Long numberOfOrder) {
		this.numberOfOrder = numberOfOrder;
	}

	@SuppressWarnings("unused")
	private OrderTraceDetail() {
		super();
	}
	@NotNull
	private Long id;
	
	@NotNull
	private OrderTrace orderTrace;
	
	@NotNull
	private int quantity;
	
	@NotNull
	@ManyToOne(optional = false)
	private Model model ;
	
	@NotNull
	@ManyToOne(optional = false)
	private Sizing sizing;
	
	private Long numberOfOrder;
	
	@Autowired
	OrderTraceRepository orderTraceRepository ;
	
	public OrderTraceDetail (Long id, Long parent_id, Long numberOfOrder, Long dateOfTrace, String nameOfTrace, 
			Long sender_id, 
			Long receiver_id,
			Long client_id,
			Long brand_id, 
			Long season_id,
			Long model_id, Long sizing_id, int quantity)	{
		this.id = id;
		this.orderTrace = new OrderTrace (parent_id, numberOfOrder, dateOfTrace, nameOfTrace, sender_id, receiver_id, client_id, brand_id, season_id);	
		this.numberOfOrder = numberOfOrder;
		this.model = new Model(model_id, "");
		this.sizing = new Sizing(sizing_id, "", quantity);
		this.quantity = quantity;
	}
	
	public OrderTraceDetail (Long id, Long parent_id, Long numberOfOrder,	Long model_id, Long sizing_id, int quantity)	{
		this.id = id;
		this.orderTrace = new OrderTrace (parent_id, numberOfOrder, new Date().getTime(), "", new Long(0), new Long(0), new Long(0), new Long(0), new Long(0));	
		this.numberOfOrder = numberOfOrder;
		this.model = new Model(model_id, "");
		this.sizing = new Sizing(sizing_id, "", quantity);
		this.quantity = quantity;
	}
	
	public OrderTraceDetail (Long id, Long parent_id, Long numberOfOrder,  
			Long model_id, String model_name, 
			Long sizing_id, String sizing_name, int quantity)	{
		this.id = id;
		this.orderTrace = new OrderTrace (parent_id, numberOfOrder, new Date().getTime(), "", new Long(0), new Long(0), new Long(0), new Long(0), new Long(0));
		this.numberOfOrder = numberOfOrder;
		this.model = new Model(model_id, model_name);
		this.sizing = new Sizing(sizing_id, sizing_name, quantity);
		this.quantity = quantity;
	}
	

}
