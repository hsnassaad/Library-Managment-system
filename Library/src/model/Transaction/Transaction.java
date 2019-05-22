package model.Transaction;

import model.Bill;
import model.Books.Book;

public class Transaction {
	protected Book book_id;
	protected int id;
	protected Bill bill; 	
	
	public Transaction(Book book_id, int id,Bill bil) {
		super();
		this.book_id = book_id;
		this.id = id;
		this.bill = bil;
	}
	
	public Transaction(Book book_id, int id) {
		super();
		this.book_id = book_id;
		this.id = id;
	}
	
	public Transaction(Book book_id) {
		super();
		this.book_id = book_id;
	}
	
	public Transaction(int id) {
		super();
		this.id = id;
	}
	
	
	public Transaction() {}
	
	public Book getBook_id() {
		return book_id;
	}
	public void setBook_id(Book book_id) {
		this.book_id = book_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	@Override
	public String toString() {
		return "Transaction [book_id=" + book_id + ", id=" + id;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
}
