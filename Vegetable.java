package finalproj;
import java.awt.Color;

/*
 * @Author:
 * Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is for the vegetable of a pizza
 * ie, the type of sauce that will go on the pizza
 * to be created
 * it extends the ingredient because a vegetable is a type of an ingredient
 * and only calls the constructor of ingredient passing in the specific 
 *values passed into the constructor for the base 
 *which will be either olives or peppers
 *the only difference between this class and the classes of meat, cheese and base
 *is that vegetable also has a color component
 *so this is defined in the data defintion
 *and additional methods are provided for the color to be defined
 *luckily, both olives and peppers can be green :)
 */
public class Vegetable extends Ingredient {//inheritance
	private Color color24;//additional data definition
	//is a color component from the color class
	//same type of constructor as base meat and cheese
	public Vegetable(String d, Money c, int cal) {//call the super constructor
		super(d, c, cal);
		color24 = Color.GREEN; //because veggies are normally green?
	}
	//constructor that takes in everything
	//just as base, meat, cheese, but now also has a color
	public Vegetable(String d, Money c, int cal, Color c24) {//a more full constructor
		//which passes in everything to super just as before
		super(d, c, cal);
		color24 = c24;//but now has a color component as well
	}
	//color does not have a defined clone method
	//or copy constructor
	//so simply return the leash to the color object
	public Color getColor() {
		return color24;
	}
	//the overridden equals method for a vegetable
	//because there is an additional component in veggies
	//we can use the super method
	//we just also have to define the comparison of colors
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Vegetable) ) throw new PizzaException("That isn't a veggie");//null check, veggie check
		Vegetable that = (Vegetable) o;//cast
		if (this.color24.equals(that.getColor()) && super.equals(that) ) return true;//compare the components using super and the color equals
		return false;
	}
	//override the Ingredient to string
	//add in the supers to String and make sure to also print the color of the vegetable
	@Override
	public String toString() {
		return super.toString() + " Color: " + color24.toString();//supers to String plus a color component
	}
	
}
