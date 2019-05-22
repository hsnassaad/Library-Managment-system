package model.tabless;

public class AllBuyEducation extends AllBuy{

	private String subject;
	private String academiclvl;
	
	
	public AllBuyEducation(String id, String title, String author, int qt, int price,
			String subject, String academiclvl) {
		super(id, title, author, qt, price);
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
		return "AllBuyEducation [subject=" + subject + ", academiclvl=" + academiclvl + "]";
	}
	
	
	
	
}
