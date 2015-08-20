package finalproj;
/*
 * @Author:
 * Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is for the base of a pizza
 * ie, the type of sauce that will go on the pizza
 * to be created
 * it extends the ingredient because a base is a type of an ingredient
 * and only calls the constructor of ingredient passing in the specific 
 *values passed into the constructor for the base 
 *which will be either alfredo or marinara
 */
public class Base extends Ingredient {//inheritance
	//the constructor for a base
	//takes in all the components of an ingredient
	//and calls the supers method
	public Base(String d, Money c, int cal) {//call the super constructor with what is passed in
		super(d, c, cal);//pass in the values
	}
}
