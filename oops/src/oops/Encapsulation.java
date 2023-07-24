package oops;
//decoupling, restricted accessibility

class Employee{
	private String name;
	private int id;
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name  = name;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}

public class Encapsulation {
	public static void main(String args[]) {
	
	Employee e1 = new Employee();
	e1.setId(1);
	e1.setName("Rashmi");
	
	Employee e2 = new Employee();
	e2.setId(2);
	e2.setName("Shri");
	
	System.out.println("Employee with id: "+ Integer.toString(e1.getId())+" is "+e1.getName());
}
}
