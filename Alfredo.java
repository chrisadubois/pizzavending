package finalproj;

/*
 * @Author: Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is the extension of the base class
 * and is the alfredo option for the sauce of the pizza
 * it extends base and sets the data definitions as final
 * because there is only one type of alfredo
 */

public class Alfredo extends Base {//inheritance
	private final static String alfy = "Alfredo";//final alfredo value because alfredo is alfredo
	private final static int cal = 560;//alfredo isn't healthy :(
	private final static Money cost = new Money(3, 0);//but costs the same as marinara
	//because it's delicious
	
	//call the super constructor for the base class
	//and set the values
	//from the final static instance variables
	public Alfredo(){
		super(alfy, cost, cal);
	}
	//override the supers to String
	//because alfredo has specific values to print out
	@Override
	public String toString() {
		return "Name: " + alfy + " Cost: " + cost.toString() + " Calories: " + cal;
	}

}
