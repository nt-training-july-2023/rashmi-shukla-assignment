package oops.inheritance;
//Java doesnot support multiple inheritance although it can be achieved using INTERFACES

//Class Animal already in same package

interface Pet{
	public abstract void plays();
}

class Canine extends Animal{
}
class Dog extends Canine implements Pet{
	public void plays() {
		System.out.println("Fetch a ball");
	}
}
public class multiple {
	public static void main(String args[]) {
		Dog d = new Dog();
		d.plays();
		d.eat();
		d.walk();
	}
}
