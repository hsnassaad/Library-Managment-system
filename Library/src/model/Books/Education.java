package model.Books;

import model.Types.Type;

public class Education extends Book{


	private String subject;
	private String academicLvl;
	
	public Education(String id, String title, String author, String description, int qt, Type type,String subject,String academic) {
		super(id, title, author, description, qt, type);
		// TODO Auto-generated constructor stub
		setSubject(subject);
		setAcademicLvl(academic);
	}

	
	public Education(String id, String title, String author, String description, int qt,String subject,String academic) {
		super(id, title, author, description, qt);
		// TODO Auto-generated constructor stub
		setSubject(subject);
		setAcademicLvl(academic);
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAcademicLvl() {
		return academicLvl;
	}
	public void setAcademicLvl(String academicLvl) {
		this.academicLvl = academicLvl;
	}
	@Override
	public String toString() {
		return "Education ["+super.toString()+"subject=" + subject + ", academicLvl=" + academicLvl + "]";
	}
	
}
