package model.Person;

import java.util.ArrayList;

import model.Books.Book;
import model.Transaction.Rent;

public class Client extends Person{
	
	private int id;
	private int points;
	
	public ArrayList<Rent> rents;
	
	public Client(String name, int phone, String email, int id) {
		super(name, phone, email);
		this.id = id;
		setRents(new ArrayList<Rent>());
	}
	
	public Client(String name, int phone, String email) {
		super(name, phone, email);
		setRents(new ArrayList<Rent>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Client [id=" + id +" points= "+points+"  "+ super.toString()+"]";
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public ArrayList<Rent> getRents() {
		return rents;
	}

	public void setRents(ArrayList<Rent> rents) {
		this.rents = rents;
	}
	
	public Rent getRentByBook(Book b) {
	
		Rent r = null;
		System.out.println(rents.size());
		
		for(int i=0;i<this.rents.size();i++) {
			if(rents.get(i).getBook_id().getId().equals(b.getId())) {
				r=rents.get(i);
				System.out.println(i);
				System.out.println(r);
				break;
			}
		}
		return r;
		
	}
	
	
}
