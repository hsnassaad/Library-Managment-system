package model.Transaction;

import java.sql.Time;

import model.Bill;
import model.Books.Book;

public class Buy extends Transaction{

	private int qt;
	public static int counta=0;

	public Buy(Book book_id, int id, int qt,Bill bil) {
		super(book_id, id,bil);
		this.qt = qt;
	}

	public Buy(Book book_id, int qt) {
		super(book_id);
		this.qt = qt;
	}
	
	public Buy(String Book_id, int qt) {
		this.qt = qt;
	}
	
	@Override
	public String toString() {
		return "Buy "+super.toString() +"[qt=" + qt  + "]";
	}

	
	
	public int getQt() {
		return qt;
	}

	public void setQt(int qt) {
		this.qt = qt;
	}

}
