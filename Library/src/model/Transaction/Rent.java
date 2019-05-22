package model.Transaction;

import java.sql.Time;
import java.time.LocalDate;

import model.Bill;
import model.Books.Book;
import model.Person.Client;
import model.Person.Employee;

public class Rent extends Transaction{


	public static int counta=1;
	private int Days;
	public LocalDate StartDate;
	private int bit=0;

	public Rent(Book book_id, int id, int days,Bill bill) {
		super(book_id, id,bill);
		Days = days;
	}
	
	public Rent(Book book_id, int days) {
		super(book_id);
		Days = days;
	}
	

	public int getDays() {
		return Days;
	}

	public void setDays(int days) {
		Days = days;
	}
	
	@Override
	public String toString() {
		return "Rent [Days=" + Days + ", bit=" + bit+"super(): "+super.toString();
	}

	public int getBit() {
		return bit;
	}

	public void setBit(int bit) {
		this.bit = bit;
	}

	public LocalDate getStartDate() {
		return StartDate;
	}

	public void setStartDate(LocalDate startDate) {
		StartDate = startDate;
	}

	
	
}
