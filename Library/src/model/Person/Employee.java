package model.Person;

import model.work;

public class Employee extends Person{
	public static int count=0;
	private String username;
	private String password;
	private work work_id;
	private int Salary;
	private int id;
	private String workk;
	
	
	public Employee(String name, int phone, String email, String username, String password, work work, int salary,
			int id) {
		super(name, phone, email);
		this.setUsername(username);
		this.password = password;
		this.work_id = work;
		Salary = salary;
		this.id = id;
	}
	
	public Employee(String name, int phone, String email, String username, String password, work work, int salary) {
		super(name, phone, email);
		this.setUsername(username);
		this.password = password;
		this.work_id = work;
		Salary = salary;
	}
	
	public static int getCount() {
		return count;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public work getWork_id() {
		return work_id;
	}
	public void setWork_id(work work_id) {
		this.work_id = work_id;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", work_id=" + work_id + ", Salary="
				+ Salary + ", id=" + id +" "+super.toString()+ "]";
	}

	public String getWorkk() {
		return workk;
	}

	public void setWorkk(String workk) {
		this.workk = workk;
	}


	
	
	
	
}
