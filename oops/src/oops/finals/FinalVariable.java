package oops.finals;

public class FinalVariable {
	 final static double pi = 3.17;
	 
	 public static double AreaOfCircle(int r) {
			return 2*pi*r;
	 }
	 
	 public static void main(String args[]) {
		 int r = 4;
		 //pi = 22/7; 
		 System.out.println(AreaOfCircle(r));
	 }
	 
}
