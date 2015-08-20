package finalproj;
/*
 * @Author:
 * Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is for the cheese of a pizza
 * ie, the type of cheese that will go on the pizza
 * to be created
 * it extends the ingredient because a cheese is a type of an ingredient
 * and only calls the constructor of ingredient passing in the specific 
 *values passed into the constructor for the cheese 
 *which will be either Mozz or Goat
 */
public class Cheese extends Ingredient {//inheritance
	
	public Cheese(String d, Money c, int cal) {//call the super constructor of ingredient
		//with the specific values for this type of cheese
		super(d, c, cal);//the descrip, money, and calories of cheese
	}

}
