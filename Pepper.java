package finalproj;


/*
 * @Author: Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is the extension of the Vegetable class
 * and is the pepper option for the sauce of the pizza
 * it extends meat and sets the data definitions as final
 * because there is only one type of pepper
 */

public class Pepper extends Vegetable {
	private final static String pepper = "Pepper";//final static peppers are peppers
	private final static int calorie = 24; //google search
	private final static Money cost = new Money(1, 25);//my grocery store
	
	//cal the super construcotr
	//and pass in the values of the final static instance variables
	//to make sure that a pepper is always created the same way
	public Pepper(){
		super(pepper, cost, calorie);
	}
	//override the supers to string
	//because a pepper has its own specific values to print out
	@Override
	public String toString() {
		return "Veggie: " + pepper + " Cost: " + cost.toString() + " Calories: " + calorie;
	}

}
