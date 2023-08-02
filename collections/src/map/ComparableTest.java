package map;

import java.util.ArrayList;
import java.util.Collections;

class Emp implements Comparable<Emp>{
	String name;
	int age;
	int yearOfJoin;
	public Emp(String name, int age, int yearOfJoin) {
		this.name = name;
		this.age = age;
		this.yearOfJoin = yearOfJoin;
	}

	@Override
	public int compareTo(Emp o) {
		// TODO Auto-generated method stub
		if(yearOfJoin == o.yearOfJoin) return 0;
		else if(this.yearOfJoin > o.yearOfJoin) return 1;
		else return -1;
	}
}

public class ComparableTest {
	public static void main(String[] args) {
		ArrayList<Emp> arrayList = new ArrayList<Emp>();
		arrayList.add(new Emp("Rashmi",21,2023));
		arrayList.add(new Emp("Rekha",25,2021));
		arrayList.add(new Emp("Rashi",23,2020));
		
		Collections.sort(arrayList);
		
		//sorting acc to yearOfJoin
		for(Emp e : arrayList) {
			System.out.println(e.name+" "+e.age+" "+e.yearOfJoin);
		}
		
		
	}
}
