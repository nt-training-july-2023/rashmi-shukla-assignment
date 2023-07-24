package oops.inheritance;

class Parent{
	String name  = "Parent";
	public void m1() {
		System.out.println("Parent class method");
	}
}

class Child extends Parent{
	String name = "Child";
	public void m2() {
		System.out.println("Child class method");
	}
}

public class Single {
	public static void main(String args[]) {
		Child c = new Child();
		c.m1();
		c.m2();
		System.out.println(c.name);
	}
}
