package com.kiwiplan.management.tree.model;

/**
 * 
 * @author Dhaval Shah
 * @version 1.0
 * @since 07 October 2020
 * 
 * This is the model class represent Employee.
 *
 */
public class Employee implements Comparable<Employee> {
	private int id;
	private String name;
	private int managerId;

	public Employee(int id, String name, int managerId) {
		this.id = id;
		this.name = name;
		this.managerId = managerId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Employee emp) {
        return this.name.compareTo(emp.getName());
	}
}
