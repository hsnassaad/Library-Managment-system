package model.tabless;

public class AllRentCook extends AllRent{

	private String region;
	private String typee;
	
	
	public AllRentCook(String id, String title, String author, int maxDays, int price_week, String region,
			String typee,int qt) {
		super(id, title, author, maxDays, price_week,qt);
		this.region = region;
		this.typee = typee;
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
	public void setTypee(String typee) {
		this.typee = typee;
	}
	
	
	@Override
	public String toString() {
		return "AllBuyCook [region=" + region + ", typee=" + typee + "AllRent: "+super.toString()+"]";
	}
	
	
	
}
