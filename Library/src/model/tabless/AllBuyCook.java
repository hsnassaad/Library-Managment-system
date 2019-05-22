package model.tabless;

public class AllBuyCook extends AllBuy{

	private String region;
	private String typee;
	
	
	public AllBuyCook(String id, String title, String author, int qt, int price, String region,
			String typee) {
		super(id, title, author, qt, price);
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
		return "AllBuyCook [region=" + region + ", typee=" + typee + "]";
	}
	
	
	
}
