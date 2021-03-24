package com.example.accsSkl.OrderTraceWithDetails;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.accsSkl.OrderTrace.OrderTrace;
import com.example.accsSkl.OrderTraceDetail.OrderTraceDetail;


public class OrderTraceWithDetails {
	@NotNull
	public List<OrderTrace> orderTrace = new ArrayList<>();
	@NotNull
	public List<OrderTraceDetail> orderTraceDetail = new ArrayList<>();
}
