package com.howtodoinjava.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.howtodoinjava.batch.model.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Customer.builder()
					.id(rs.getLong("id"))
					.firstName(rs.getString("firstName"))
					.lastName(rs.getString("lastName"))
					.birthdate(rs.getString("birthdate"))
					.build();
	}
}
