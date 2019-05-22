package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Books.Book;
import model.Person.Client;
import model.Person.Employee;
import model.Transaction.Transaction;

public class Bill {

	
	public static int id=0;
	private  double total=0;
	private  double total_sale=0;
	public String Date;
	private Employee emp;
	private Client client;
	Sale sale;
	public ArrayList<Transaction> transactions;
	
	
	
	public Bill() {
	}
	
	public Bill(Employee emp,Client client,double total, double total_sale, String date2, Sale sale) {
	super();
	this.total = total;
	this.total_sale = total_sale;
	Date = date2;
	this.emp = emp;
	this.client = client;
	this.sale = sale;
	transactions = new ArrayList<Transaction>();
}
	
	public Bill(Employee emp,Client client,double total, double total_sale, String date2) {
		super();
		this.total = total;
		this.total_sale = total_sale;
		Date = date2;
		this.emp = emp;
		this.client = client;
		transactions = new ArrayList<Transaction>();
	}
	
	
	public Bill(Employee emp,Client client,double total, double total_sale, Sale sale) {
	super();
	this.total = total;
	this.total_sale = total_sale;
	this.sale = sale;
	transactions = new ArrayList<Transaction>();
}
	
	public Bill(Employee emp,Client client) {
	super();
	this.emp=emp;
	this.setClient(client);
	transactions = new ArrayList<Transaction>();
}
	
	public void setTransaction(Transaction t) {
		transactions.add(t);
		t.setBill(this);
		
	}



	public ArrayList<Transaction> getTransactions(){
		return transactions;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal_sale() {
		return total_sale;
	}

	public void setTotal_sale(double total_sale) {
		this.total_sale = total_sale;
	}

	@Override
	public String toString() {
		return "Bill [id= "+id+ "total=" + total + ", total_sale=" + total_sale + ", Date=" + Date + ", sale=" + sale
				+ " Transaction: {"+printTransaction()+"}";
	}
	
	public String printTransaction() {
		String msg="";
		for(int i=0;i<transactions.size();i++) {
			msg+=i+" - "+transactions.get(i).toString();
			msg+="\n";
		}
		return msg;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public double getTotalBalance() {
		double d;
		
		d=total-total_sale;
		return d;
	}
	
	
	
	
//public static void main(String[] args) {
//	new Bill().setTransaction(new Buy());
//}
	
}




