

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
class Employee
{
	int id;
	String name;
	String department;
	int salary;
	boolean activeStatus;
	
	Employee(int id,String name,String department,int salary,boolean activeStatus)
	{
		this.id=id;
		this.name=name;
		this.department=department;
		this.salary=salary;
		this.activeStatus=activeStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
	@Override
	public String toString() {
	    return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary
	            + ", activeStatus=" + activeStatus + "]";
	}

	public static void main(String args[])
	{
		//FETCH Employee with salary > 60,000
		List<Employee> emps= Arrays.asList(
				new Employee(1,"Karthikeya","IT",150000,true),
				new Employee(2,"Sarma","IT",100000,true),
				new Employee(3,"Nagarjuna","Non IT",59000,true),
				new Employee(4,"Chiranjeevi","Semi IT",50000,true));
		List<Employee> res1= emps.stream()
				.filter(e-> e.getSalary() > 60000)
				.collect(Collectors.toList());
		res1.forEach(System.out::println);
		//convert emp names to uppercase
		
		List<String> res2= emps.stream()
				.map(e-> e.getName().toUpperCase())
				.collect(Collectors.toList());
		res2.forEach(System.out::println);
		
		//sort salaries ascending 
		List<Employee> res3=emps.stream()
				.sorted(Comparator.comparingInt(e -> e.getSalary()))
				.collect(Collectors.toList());
		res3.forEach(System.out::println);
		
		//sort salaries descending
		List<Employee> res4=emps.stream()
				.sorted(Comparator.comparingInt((Employee e) -> e.getSalary()).reversed())
				.collect(Collectors.toList());
		res4.forEach(System.out::println);
		
		//count active employees
		List<Employee> res5=emps.stream()
				.filter(e->e.activeStatus)
				.collect(Collectors.toList());
		res5.forEach(System.out::println);
		
		//group employees by department
		Map<String,Long> res6=emps.stream()
				.collect(Collectors.groupingBy(e->e.getDepartment(),Collectors.counting()));
		System.out.print(res6);
		
		//Highest salary Employee
		Optional<Employee> res7=emps.stream()
				.max(Comparator.comparingInt((Employee e) -> e.getSalary()));
				
				if (res7.isPresent()) {
				    System.out.println("Highest Salary Employee: " + res7.get());
				} else {
				    System.out.println("No employees found.");
				}
		//Second highest salary Employee
				Optional<Employee> res8=emps.stream()
						.sorted(Comparator.comparingInt((Employee e) -> e.getSalary()).reversed())
						.skip(1)
						.findFirst();
						
						if (res8.isPresent()) {
						    System.out.println("Highest Salary Employee: " + res8.get());
						} else {
						    System.out.println("No employees found.");
						}
		
	}
}
