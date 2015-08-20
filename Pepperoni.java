package finalproj;

/*
 * @Author: Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is the extension of the meat class
 * and is the pepperoni option for the sauce of the pizza
 * it extends meat and sets the data definitions as final
 * because there is only one type of pepperoni
 */

public class Pepperoni extends Meat {
 private final static String p = "Pepperoni";//pepperoni is pepperoni
 private final static Money cost = new Money(0, 75);//pepperoni is nice and cheap
 private final static int cal = 132;//but more unhealthy than sausage
 
 //call the super constructor for meat class
 //by pasing in the final static instance variables
 //so that pepperoni is always created as the same pepperoni
 public Pepperoni(){
	 super(p, cost, cal);
 }
 
 //override the supers to string
 //because pepperoni has it's own specific values to print out
 @Override
 public String toString() {
	 return "Name: " + p + " Cost: " + cost.toString() + " Calorie: " + cal;
 }
}
