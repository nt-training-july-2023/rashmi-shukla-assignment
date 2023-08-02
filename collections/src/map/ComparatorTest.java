package map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Person{
	String name;
	int age;
	public Person(String name, int age) {
		this.name= name;
		this.age = age;
	}
}

class AgeComparator implements Comparator<Person>{
	@Override
	public int compare(Person o1, Person o2) {
		// TODO Auto-generated method stub
		return o1.age - o2.age;
	}
}
class NameComparator implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		// TODO Auto-generated method stub
		return o1.name.compareTo(o2.name);
	}
	
}
public class ComparatorTest {
	public static void main(String[] args) {
		ArrayList<Person> al=new ArrayList<Person>();  
		al.add(new Person("Rashmi",21));  
		al.add(new Person("Aj",22));  
		al.add(new Person("Rekha",19));
		
		System.out.println("sort by age: ");
		Collections.sort(al, new AgeComparator());
		
		for(Person p:al) {
			System.out.println(p.name+" "+p.age);
		}
		
		System.out.println("sort by name");
		Collections.sort(al, new NameComparator());
				
		for(Person p:al) {
			System.out.println(p.name+" "+p.age);
		}
	}
}


