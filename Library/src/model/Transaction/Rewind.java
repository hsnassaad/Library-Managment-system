package model.Transaction;

import java.sql.Time;

import model.Bill;
import model.Books.Book;


public class Rewind extends Transaction{

	private Rent rent;
	private int penaltiTaken=0;
	
	public Rewind(Book book_id, int id,Bill bil,Rent rent) {
		super(book_id,id,bil);
		this.rent = rent;
	}
	
	public Rewind(Book book_id,Rent rent,int penaltiTaken) {
		super(book_id);
		this.rent = rent;
		this.penaltiTaken=penaltiTaken;
	}

	@Override
	public String toString() {
		return "Rewind [rent=" + rent + "]";
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	public int getPenaltiTaken() {
		return penaltiTaken;
	}

	public void setPenaltiTaken(int penaltiTaken) {
		this.penaltiTaken = penaltiTaken;
	}
	
	
}
