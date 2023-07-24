package oops.finals;

class X{
	public  final void m1() {
		System.out.println("This ia a final method");
	}
}

//the class can be extended 
class Y extends X{ 
//	public final void m1() {
//		System.out.println("Final method cannot be ovverriden");
//	}
}
public class FinalMethod {
	
	public static void main(String args[]) {
		 X objX = new X();
		 objX.m1();
	}
}
