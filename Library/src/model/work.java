package model;

import java.sql.Time;

public class work {

	private int id;
	private String Day;
	private Time start;
	private Time end;
	
	
	public work(int id, String day, Time start, Time end) {
		super();
		this.id = id;
		Day = day;
		this.start = start;
		this.end = end;
	}
	
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}
	public Time getStart() {
		return start;
	}
	public void setStart(Time start) {
		this.start = start;
	}
	public Time getEnd() {
		return end;
	}
	public void setEnd(Time end) {
		this.end = end;
	}
	public String toString() {
		return this.Day+" "+this.start+" "+this.end;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
