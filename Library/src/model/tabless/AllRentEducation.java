package model.tabless;

public class AllRentEducation extends AllRent{

	private String subject;
	private String academiclvl;
	
	
	public AllRentEducation(String id, String title, String author, int maxDays, int price_week,
			String subject, String academiclvl,int qt) {
		super(id, title, author, maxDays, price_week,qt);
		this.subject = subject;
		this.academiclvl = academiclvl;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getAcademiclvl() {
		return academiclvl;
	}


	public void setAcademiclvl(String academiclvl) {
		this.academiclvl = academiclvl;
	}


	@Override
	public String toString() {
		return "AllBuyEducation [subject=" + subject + ", academiclvl=" + academiclvl + "AllRent: "+super.toString()+"]";
	}
	
	
	
	
}
