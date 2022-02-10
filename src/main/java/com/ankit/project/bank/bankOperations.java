package com.ankit.project.bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class bankOperations {
	JdbcTemplate jdbcTemplate;

	String qry;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

//	Fetch count of rows from the table
	Integer getCountOfcustomer() {

		return jdbcTemplate.queryForObject("select count(*) from customer", Integer.class);
	}

	// Check a balance

	String getcustomerDetails(String customer_name, Integer customer_id) {
		qry = "select customer_balance from customer where customer_id = ?   and customer_name= ?";
		return jdbcTemplate.queryForObject(qry, String.class, new Object[] { customer_id, customer_name });

	}
	
	
	
//	Add a new Participant
	void addNewCustomer(Customer customer) {
		qry = "insert into customer values(?,?,?,?,?)";
		
		jdbcTemplate.update(qry,
							new Object[] {
							customer.customer_id(),
							customer.customer_accountNumber(),
							customer.customer_name(),
							customer.customer_balance(),
							customer.customer_bankname(),
							});
	}
	
//withdraw money
	int withDrawMoney(int id,int bal) {
		qry = "select customer_balance from customer where customer_id=? ";
		int currBal =  jdbcTemplate.queryForObject(qry,new Object[] {id}, Integer.class);
		
		System.out.println("Current account balance is : "+currBal);
		qry = "update customer set customer_balance = ? where customer_id = ?";
		
		int updatedBal = currBal-bal;
	
		
		jdbcTemplate.update(qry, new Object[] {updatedBal,id});
		
		return updatedBal;
	}
	
	
	
	
	//deposit  money
		int depositMoney(int id,int bal) {
			qry = "select customer_balance from customer where customer_id=? ";
			int currBal =  jdbcTemplate.queryForObject(qry,new Object[] {id}, Integer.class);
			
			System.out.println("Current account balance is : "+currBal);
			qry = "update customer set customer_balance = ? where customer_id = ?";
			
			int updatedBal = currBal+bal;
		
			
			jdbcTemplate.update(qry, new Object[] {updatedBal,id});
			
			return updatedBal;
		}
		
	
	
	
	
	
	// get all deatils of a customer by id
	Customer getParticipantDetails(Integer customer_id) {
		qry = "select * from  customer where customer_id =?";
		return jdbcTemplate.queryForObject(qry, new CustomerMapper(), new Object[] {customer_id});
	}

//	Create an inner class that implements RowMapper
	class CustomerMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

//			Map the fields of class:Participant with columns of table:participants 
			Customer theParticipant = new Customer(rs.getInt("customer_id"),
					rs.getInt("customer_accountNumber"),
					rs.getString("customer_name"),
					rs.getInt("customer_balance"),
					rs.getString("customer_bankname"));

			return theParticipant;

		}
	}
}
