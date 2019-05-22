package model.Books;

import model.Types.Type;

public class Roman extends Book{

	private String summary;
	private int age;
	
	
	public Roman(String id, String title, String author, String description, int qt, Type type, String summary,int age) {
		super(id, title, author, description, qt, type);
		this.summary = summary;
		this.age = age;
	}
	
	public Roman(String id, String title, String author, String description, int qt, String summary,int age) {
		super(id, title, author, description, qt);
		this.summary = summary;
		this.age = age;
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Roman ["+super.toString()+"summary=" + summary + ", age=" + age + "]";
	}

	
	
}
