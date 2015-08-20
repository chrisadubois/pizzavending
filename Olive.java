package finalproj;

/*
 * @Author: Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is the extension of the Vegetable class
 * and is the olive option for the sauce of the pizza
 * it extends meat and sets the data definitions as final
 * because there is only one type of olive
 */

public class Olive extends Vegetable {
	private final static String olive = "Olive";//because an olive is an Olive
	private final static int calorie = 4; //google search
	private final static Money cost = new Money(1, 30);//i hate olives :( lets make them expensive
	
	//call the super constructor for the olive
	//by passing in the final static instance variables of the class
	//data definitions, to make sure that an olive
	//is always created the same way
	public Olive(){
		super(olive, cost, calorie);
	}
	//override the supers to String
	//because an olive has its own specific values to print out
	@Override
	public String toString() {
		return "Veggie: " + olive + " Cost: " + cost.toString() + " Calories: " + calorie;
	}

}
