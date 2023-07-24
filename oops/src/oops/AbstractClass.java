package oops;

//class Animal in same package

abstract class Bike {
	//cannot be instantiated, can have abstract and non-abstract methods
	void start() {
		System.out.println("Starts engine");
	}
	abstract void brake(); 
}

class Honda extends Bike {
	  // implementation of abstract method
	  public void brake() {
	    System.out.println("Honda Bike Brake");
	  }
}

class Pulsar extends Bike {
	public void brake() {
	    System.out.println("Pulsar Bike Brake");
	 }
}
public class AbstractClass {
	public static void main(String args[]) {
		Honda h = new Honda();
		h.brake();
		h.start();
		Pulsar p = new Pulsar();
		p.brake();
	}
}
