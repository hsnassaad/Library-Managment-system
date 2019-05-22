package model.tabless;

public class recent {

	private String id;
	private String bookName;
	private String category;
	private int number;
	private String type;

	
	public recent(String id, String bookName, String category, int number, String type) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.category = category;
		this.number = number;
		this.type = type;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return "recent [id=" + id + ", bookName=" + bookName + ", category=" + category + ", number=" + number
				+ ", type=" + type + "]";
	}
	
	
	
	
	
}
