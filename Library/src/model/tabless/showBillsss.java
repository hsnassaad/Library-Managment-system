package model.tabless;

import java.sql.Date;
import java.sql.Time;

public class showBillsss {

	
	private int id;
	private String empName;
	private String clientName;
	private double total;
	private Date date;
	
	public showBillsss(int id, String empName, String clientName, double total, Date date) {
		super();
		this.id = id;
		this.empName = empName;
		this.clientName = clientName;
		this.total = total;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "showBillsss [id=" + id + ", empName=" + empName + ", clientName=" + clientName + ", total=" + total
				+ ", date=" + date + "]";
	}
	
	
	
	
}
