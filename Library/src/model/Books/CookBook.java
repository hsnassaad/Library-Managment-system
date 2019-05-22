package model.Books;

import model.Types.Type;

public class CookBook extends Book{

	private String typee;
	private String region;
	
	
	
	
	public CookBook(String id, String title, String author, String description, int qt, Type type, String typee,
			String region) {
		super(id, title, author, description, qt, type);
		this.typee = typee;
		this.region = region;
	}
	
	public CookBook(String id, String title, String author, String description, int qt, String typee,
			String region) {
		super(id, title, author, description, qt);
		this.typee = typee;
		this.region = region;
	}
	
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getTypee() {
		return typee;
	}
	public void setTypee(String type) {
		this.typee = type;
	}
	@Override
	public String toString() {
		return "CookBook ["+super.toString()+"typee=" + typee + ", region=" + region + "]";
	}
	
	
	
}
