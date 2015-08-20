package finalproj;


/*
 * @Author: Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is the extension of the CHeese class
 * and is the goat option for the sauce of the pizza
 * it extends cheese and sets the data definitions as final
 * because there is only one type of goat cheese
 */
public class Goat extends Cheese {//inheritance
	private final static String g = "Goat";//G-cheese
	private final static Money cost = new Money(1, 50);//goat cheese is more expensive than Mozz
	private final static int cal = 45;//but I'm pretty sure it's healthier because less is needed on a pizza//flavorful
	
	
	//cal the super constructor for the cheese class
	//by passing in the final static instance variables
	//to ensure that goat cheese is always made the same way
	public Goat(){
		super(g, cost, cal);
	}
	//override the supers to String
	//because goat cheese has its own specific values to print out
	@Override
	public String toString() {
		return "Name: " + g + " Cost: " + cost.toString() + " Calorie: " + cal;
	}
}
