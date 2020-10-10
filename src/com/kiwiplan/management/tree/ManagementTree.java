package com.kiwiplan.management.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.kiwiplan.management.tree.model.Employee;

/**
 * 
 * @author Dhaval Shah
 * @version 1.0
 * @since 07 October 2020
 * 
 * This is the class to build management tree.
 *
 */
public class ManagementTree {
	
	public static final String INDICATION_CHAR = "->";
	
	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee(10, "Tom", 0));
		employeeList.add(new Employee(2, "Mickey", 10));
		employeeList.add(new Employee(3, "Jerry", 10));
		employeeList.add(new Employee(7, "John", 2));
		employeeList.add(new Employee(5, "Sarah", 10));
		
		
		List<Employee> employeeList2 = new ArrayList<Employee>();
		employeeList2.add(new Employee(1, "Jerry", 0));
		employeeList2.add(new Employee(2, "Philip", 1));
		employeeList2.add(new Employee(3, "Stacie", 1));
		employeeList2.add(new Employee(4, "David", 2));
		employeeList2.add(new Employee(5, "Ken", 2));
		employeeList2.add(new Employee(6, "Tom", 2));
		employeeList2.add(new Employee(7, "Alice", 4));
		employeeList2.add(new Employee(8, "Mike", 4));
		employeeList2.add(new Employee(9, "Peter", 5));
		employeeList2.add(new Employee(10, "John", 7));
		
		System.out.println("Management Tree:1");
		System.out.println("-----------------");
		Collections.sort(employeeList);

		displayManagementTree(employeeList);
		
		System.out.println("Management Tree:2");
		System.out.println("-----------------");

		Collections.sort(employeeList2);

		displayManagementTree(employeeList2);
	}

	/**
	 * This method display the management tree. It takes the list of employees, traverse through that list
	 * and print the management tree accordingly.
	 * 
	 * @param employeeList
	 */
	private static void displayManagementTree(List<Employee> employeeList) {
		Map<Integer, List<Employee>> empHierarchyMap = new HashMap<Integer, List<Employee>>();
		Optional<Employee> manager = employeeList.stream()
									   			 .filter(emp -> emp.getManagerId() == 0)
									   			 .findFirst();
		Employee managerEmp = manager.get();
		int level = 0;
		if(level == 0)
		{
			System.out.print(INDICATION_CHAR);
		}
		System.out.println(managerEmp.getName());
		for(Employee e :  employeeList)
		{
			prepareHierarchyMap(employeeList, e, empHierarchyMap);
		}
		
		if(isSubOrdinateExists(empHierarchyMap, manager.get().getId()))
		{
			printSubOrdinate(empHierarchyMap, Integer.valueOf(manager.get().getId()), ++level);
		}
	}
	
	/**
	 * This method is to check if there is any subordinate exists for the employee or not
	 * 
	 * @param employeeSubordinateMap
	 * @param id
	 * @return boolean
	 */
	private static boolean isSubOrdinateExists(Map<Integer, List<Employee>> employeeSubordinateMap, Integer id) {
		return employeeSubordinateMap.containsKey(id) && !employeeSubordinateMap.get(id).isEmpty();
	}
	
	/**
	 * This method prints the hierarchy of subordinate based on their levels
	 * 
	 * @param employeeSubordinateMap
	 * @param id
	 * @param level
	 */
	private static void printSubOrdinate(Map<Integer, List<Employee>> employeeSubordinateMap, Integer id, int level) {
		for(Employee subEmp : employeeSubordinateMap.get(id))
		{
			for(int i = 0; i <= level; i++)
			{
				System.out.print(INDICATION_CHAR);
			}
			System.out.println(subEmp.getName());
			if(isSubOrdinateExists(employeeSubordinateMap, Integer.valueOf(subEmp.getId())))
			{
				printSubOrdinate(employeeSubordinateMap, Integer.valueOf(subEmp.getId()), ++level);
				level--;
			}
			employeeSubordinateMap.remove(Integer.valueOf(id));
			employeeSubordinateMap.remove(Integer.valueOf(subEmp.getId()));
			
		}
	}
	
	/**
	 * Prepare a map of employee hierarchy  
	 * 
	 * @param employeeList
	 * @param superier
	 * @param empHierarchyMap
	 */
	private static void prepareHierarchyMap(List<Employee> employeeList, Employee superier, Map<Integer, List<Employee>> empHierarchyMap) {
		empHierarchyMap.put(superier.getId(), employeeList.stream()
	   			 .filter(emp -> emp.getManagerId() == superier.getId())
	   			 .collect(Collectors.toList()));
	}
}
