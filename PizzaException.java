package finalproj;


/*@Author:Christopher DuBois
 * CSS 162
 * Final project
 * Professor Rob Nash
 * this is the boiler plate exception 
 * class to be thrown for the pizza 
 * class created, it will say
 * that there is an error with your pizza
 *if the no arg constructor is called 
 */
public class PizzaException extends RuntimeException {//inheritance
	public PizzaException() {//no arg constructor
		super("There was an error in your pizza");//when the no arg constructor is called
		//a simple error message prints out that stops the running of the program
	}
	//constructor that takes in the string message
	//and prints out the message to the string when called in other classes 
	public PizzaException(String msg) {//
		super(msg);//pass it to super
	}

}
