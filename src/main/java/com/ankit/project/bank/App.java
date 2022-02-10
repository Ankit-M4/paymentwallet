package com.ankit.project.bank;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {

//     Load the context
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bank.xml");

		bankOperations dao = context.getBean("bankOperations", bankOperations.class);

		// System.out.println("number of customer in this list
		// :"+dao.getCountOfcustomer());

		Scanner sc = new Scanner(System.in);
		System.out.println("Press Key  For Operations");
		
		
		System.out.println("---------------------------");
		
		
		
		System.out.println("Press 1  For WithDrawmoney");
		System.out.println("Press 2  For DepositDrawmoney");
		System.out.println("Press 3  For Create A New Account");
		System.out.println("Press 4  For Show balance");

		int choice = sc.nextInt();
		

		switch (choice) {
		case 1:

			System.out.println("Enter the  customer_id ");

			Integer customer_id = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter amount to withdraw ");

			Integer amount = sc.nextInt();

			System.out.println("Your updated balance is : " + dao.withDrawMoney(customer_id, amount));

			break;
		case 2:

			System.out.println("Enter the  customer_id ");

			Integer id = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter amount to deposit ");

			Integer amt = sc.nextInt();

			System.out.println("Your updated balance is : " + dao.depositMoney(id, amt));
			
			break;
			
		case 3:
			
			System.out.println("Enter the  customer_id ");

			Integer id1 = sc.nextInt();
			
			System.out.println( "Enter the customer_accountNumber ");
			Integer customer_accountNumber = sc.nextInt();
			
			
			System.out.println( "Enter the  customer_name ");
			String customer_name = sc.next();
			
			System.out.println( "Enter the customer_balance");
			Integer customer_balance = sc.nextInt();
			
			System.out.println( "Enter the customer_bankname  ");
			
			String  customer_bankname = sc.next();

			dao.addNewCustomer(new Customer(id1,customer_accountNumber,customer_name,customer_balance,customer_bankname));

	    	System.out.println("New Customer Registired");
	    	
	    	
	    	break;
	    	
		case 5 :
			
			System.out.println("Enter id of customer :");
			
			Integer id3 = sc.nextInt();
			
		
		    Customer theCustomer = dao.getParticipantDetails(id3);
			System.out.println(dao.getParticipantDetails(id3));
			
			break;
			
		case 4:
			
		System.out.println("Enter id  and name of customer :");
		
			
			
			Integer id4 = sc.nextInt();
			sc.nextLine();
			
			String name = sc.nextLine();
			
		String balance  = dao.getcustomerDetails(name, id4);
			
		System.out.println("balance of the customer  "+balance);
	
		break;

		}
		
		
		
		
//	System.out.println("Enter id  and name of customer :");
//	
//	
//	
//	Integer id = sc.nextInt();
//	sc.nextLine();
//	
//	String name = sc.nextLine();
//	
//	String balance  = dao.getcustomerDetails(name, id);
//	
//	System.out.println("balance of the customer  "+balance);
//    


		// Integer id = sc.nextInt();
		// sc.nextLine();

		// Customer theCustomer = dao.getParticipantDetails(id);
		// System.out.println(dao.getParticipantDetails(id));

		// System.out.println("Your updated balance is :
		// "+dao.withDrawMoney(customer_id, amount));

	}
}