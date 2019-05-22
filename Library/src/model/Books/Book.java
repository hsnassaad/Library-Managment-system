package model.Books;

import model.Types.Type;

public abstract class Book {

	private String id;
	private String title;
	private String author;
	private String description;
	private int qt;
	private Type type;
	
	
	public Book(String id, String title, String author, String description, int qt, Type type) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.qt = qt;
		this.type = type;
	}
	
	public Book(String id, String title, String author, String description, int qt) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.qt = qt;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQt() {
		return qt;
	}
	public void setQt(int qt) {
		this.qt = qt;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description + ", qt="
				+ qt + ", type=" + type + "]";
	}
	
}
