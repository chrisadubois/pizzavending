package finalproj;

/*
 * @Author: Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is the extension of the Cheese class
 * and is the cheese option for the sauce of the pizza
 * it extends cheese and sets the data definitions as final
 * because there is only one type of cheese
 */

public class Mozzarella extends Cheese {//inheritance
	private final static String m = "Mozzarella";//because mozz is always mozzerella
	private final static Money cost = new Money(1, 0);//it doesn't take long to make so it's cheaper
	private final static int cal = 110;//and not too unhealthy because can be made from skim milk

	
	//call the super constructors for mozzerella
	//by passing in the final static instance variables
	//of the mozzerella class to ensure that a mozzerella
	//is always created the same way with the same stuff
	public Mozzarella() {
		super(m, cost, cal);
	}

	//override the supers to String 
	//because a mozzerella has it's own specific values to print out
	@Override
	public String toString() {
		return "Name: " + " Cost: " + cost.toString() + " Calories: " + cal;
	}
}
