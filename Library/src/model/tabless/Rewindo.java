package model.tabless;

import java.sql.Time;

public class Rewindo {

	private String id;
	private String days;
	private String MaxDays;
	
	public Rewindo(String id, String days, String maxDays) {
		super();
		this.id = id;
		this.days = days;
		MaxDays = maxDays;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getMaxDays() {
		return MaxDays;
	}

	public void setMaxDays(String maxDays) {
		MaxDays = maxDays;
	}

	@Override
	public String toString() {
		return "Rewindo [id=" + id + ", days=" + days + ", MaxDays=" + MaxDays + "]";
	}
	


	
	
	
	

	
	
	
}
