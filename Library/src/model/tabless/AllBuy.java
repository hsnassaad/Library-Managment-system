package model.tabless;

public class AllBuy {

	private String id;
	private String title;
	private String author;
	private String description;
	private int qt;
	
	private int price;

	public AllBuy(String id, String title, String author, String description, int qt, int price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.qt = qt;
		this.price = price;
	}
	
	public AllBuy(String id, String title, String author, int qt, int price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.qt = qt;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "AllBuy [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description + ", qt="
				+ qt + ", price=" + price + "]";
	}
	
	
	
	
}
