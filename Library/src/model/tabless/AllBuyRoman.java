package model.tabless;

public class AllBuyRoman extends AllBuy{

	private String summary;
	private int age;
	
	public AllBuyRoman(String id, String title, String author, String summary, int age, int qt, int price) {
		super(id,title,author,qt,price);
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
		return "AllBuyRoman [summary=" + summary + ", age=" + age + "]";
	}


	
	
}
