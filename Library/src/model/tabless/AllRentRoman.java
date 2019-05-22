package model.tabless;

public class AllRentRoman extends AllRent{

	private String summary;
	private int age;
	
	public AllRentRoman(String id, String title, String author, String summary, int age, int maxDays, int price_week,int qt) {
		super(id,title,author,maxDays,price_week,qt);
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
		return "AllBuyRoman [summary=" + summary + ", age=" + age + "AllRent: "+super.toString()+"]";
	}


	
	
}
