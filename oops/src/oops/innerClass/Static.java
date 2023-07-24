package oops.innerClass;


class Out {
	static String name;
	
	void show() {
		System.out.println("in method show");
	}
	
	static class In{
		
		void config() {
			System.out.println(name+" in method config");
		}
		
	}
}
public class Static {
	public static void main(String args[]) {
		
		Out obj1 = new Out();
		Out.name = "program";
		obj1.show();
		
		Out.In obj2 = new Out.In(); //can intialize without object of A
		obj2.config();

	}

}
