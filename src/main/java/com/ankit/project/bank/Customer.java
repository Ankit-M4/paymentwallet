package com.ankit.project.bank;

record Customer(Integer customer_id,
		Integer customer_accountNumber,
		String customer_name,
		Integer customer_balance,
		String customer_bankname){}