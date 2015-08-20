package finalproj;
/*
 * @Author:
 * Christopher DuBois
 * CSS 162
 * Final Project
 * Professor Rob Nash
 * this class is for the meat of a pizza
 * ie, the type of sauce that will go on the pizza
 * to be created
 * it extends the ingredient because a meat is a type of an ingredient
 * and only calls the constructor of ingredient passing in the specific 
 *values passed into the constructor for the meat
 *which will be either sausage or pep
 */
public class Meat extends Ingredient {//inheritance
	
	public Meat(String d, Money c, int cal) {//call the super constructor
		//for the specific description, money and caloric content 
		//being passed into the ingredient
		super(d, c, cal);//pass the values to ingredients super constructor
	}

}
