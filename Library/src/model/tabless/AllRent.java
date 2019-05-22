package model.tabless;

public class AllRent {

	private String id;
	private String title;
	private String author;
	private String description;
	private int maxDays;
	private int price_week;
	private int qt;

	public AllRent(String id, String title, String author, String description, int maxDays, int price_week,int qt) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.maxDays = maxDays;
		this.qt=qt;
		this.price_week = price_week;
	}
	
	public AllRent(String id, String title, String author, int maxDays, int price_week,int qt) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.maxDays = maxDays;
		this.qt=qt;
		this.price_week = price_week;
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

	public int getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}

	public int getPrice_week() {
		return price_week;
	}

	public void setPrice_week(int price_week) {
		this.price_week = price_week;
	}
	
	
	

	public int getQt() {
		return qt;
	}

	public void setQt(int qt) {
		this.qt = qt;
	}

	@Override
	public String toString() {
		return "AllRent [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description
				+ ", maxDays=" + maxDays + ", price_week=" + price_week + ", qt=" + qt + "]";
	}

	
	

	
	
	
	
	
}
