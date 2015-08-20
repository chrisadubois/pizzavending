package finalproj;

/*
 * @Author: Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is the extension of the meat class
 * and is the sausage option for the sauce of the pizza
 * it extends meat and sets the data definitions as final
 * because there is only one type of sausage
 */

public class Sausage extends Meat {//inheritance

	private final static String s = "Sausage";//sausage is the best
	private final static Money cost = new Money(1, 25);//so it's nice and cheap :)
	private final static int cal = 75;//and it happens to be turkey sausage
	
	//call the super constructor
	//on the final static instance variables
	//so that sausage is always defined the same way
	public Sausage(){
		super(s, cost, cal);
	}
	
	//override the supers to string
	//because sausage has it's own specific values to print out
	@Override
	public String toString(){
		return "Name: " + s + " Cost: " + cost.toString() + " Calorie: " + cal;
	}
}
