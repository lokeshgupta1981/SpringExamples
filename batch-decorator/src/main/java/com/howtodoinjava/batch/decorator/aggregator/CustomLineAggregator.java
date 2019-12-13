package com.howtodoinjava.batch.decorator.aggregator;

import org.springframework.batch.item.file.transform.LineAggregator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howtodoinjava.batch.decorator.model.Customer;

public class CustomLineAggregator implements LineAggregator<Customer> {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String aggregate(Customer item) {
		try {
			return objectMapper.writeValueAsString(item);
		} catch (Exception e) {
			throw new RuntimeException("Unable to serialize Customer", e);
		}
	}
}
