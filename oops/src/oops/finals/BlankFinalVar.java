package oops.finals;
//how to assign them

public class BlankFinalVar {
	   public final String name;
	   public final static int age;
	   
//static final can only be assigned in static block or the compiler thinks we are reassigning it 
	   static {  
		   age = 21;
	   }
	   
	   public BlankFinalVar(){
		      this.name = "Rashmi";
	   }
	   
	   public void display(){
	      System.out.println("Name of the Student: "+this.name);
	      System.out.println("Age of the Student: "+BlankFinalVar.age);
	   }
	   public static void main(String args[]) {
	      new BlankFinalVar().display();
	   }
		   
}
