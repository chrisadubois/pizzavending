package finalproj;

/*
 * @Author: Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is the extension of the base class
 * and is the marinara option for the sauce of the pizza
 * it extends base and sets the data definitions as final
 * because there is only one type of marinara
 */

public class Marinara extends Base {//inheritance
	private final static String m = "Marinara";//it is marinara
	private final static int cal = 96;//marinara is healthy :)
	private final static Money cost = new Money(3, 0);//healthy stuff is expensive
	
	//calls the super constructors on the static final data definitions
	//so it is always a marinara with these exact values
	public Marinara(){
		super(m, cost, cal);
	}
	//override the supers toString
	//because marinara has specific values to print out
	@Override
	public String toString() {
		return "Name: " + m + " Cost: " + cost.toString() + " Calories: " + cal;
	}

}
