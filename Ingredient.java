package finalproj;


/*
 * @Author: Christopher DuBois
 * CSS 162
 * Professor Rob Nash
 * Final Project
 * this class is the superclass for any ingredient added to the pizza
 * below it will be vegetables, bases, cheeses, and meats
 * it has a cost, description, and caloric content for each ingredient
 */
public class Ingredient implements Comparable {//the comparable contract to abide by
	//so that ingredients are comparable
	//data definitions
	private Money cost;//an ingredient has a cost
	private String description;//it also has a description of what it is
	private int calorie;//and a caloric content
	//no arg constructor for ingredient
	//initially, the money is nothing
	//the description is nothing
	//and the calories are nothing
	public Ingredient() {
		cost = new Money(0, 0);
		description = "null";
		calorie = 0;
	}
	//the full constructor
	//disallows privacy leaks
	//using the clone method 
	//which is implemented in the money class
	public Ingredient(String d, Money c, int cal) {
		description = d;//set it
		cost = c.clone();//deep copy
		calorie = cal;//set it
	}
	//simple getter
	//returns the deep copy
	//of the money calling object
	//by calling the clone method
	public Money getCost() {
		return cost.clone();
	}
	//sets the value of the money with 
	//the full money constructor 
	//as two integer values
	public void setCost(int d, int c) {
		cost.setMoney(d, c);
	}
	//simple getter
	//return the string representation
	//of the ingredient
	public String getDesc() {
		return description;
	}
	//simple setter
	//set it with the String class
	//with the equals
	public void setDesc(String d) {
		description = d;
	}
	//primitive getter
	//simply returns the value
	public int getCalorie(){
		return calorie;
	}
	//set the value
	//of the calorie of the ingredient
	//using the primitive input
	public void setCalorie(int c) {
		calorie = c;
	}
	//overrides the superclass object equals method
	//compares ingredients by their cost, description, and calories
	//if they are not all equal
	//then they are not the same ingredient
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Ingredient) ) throw new PizzaException("Not an ingredient");//null check, ingredient check
		Ingredient that = (Ingredient) o;//cast
		if (this.cost.equals(that.getCost()) && this.description.equals(that.getDesc()) && this.calorie == that.getCalorie()) return true;//compare each component
		return false;
	}
	//implements the comparable interface
	//does so by comparing the costs of the ingredients
	//using the money classes compare To method for whatever cost
	//each ingredient has
	@Override
	public int compareTo(Object o) {
		if (o == null || !(o instanceof Money) ) throw new PizzaException("Not an ingredient");//null check Money check
		Money that = (Money) o;//cast
		int retVal = this.cost.compareTo(that);//compare the cost of one ingredient to the cost of that inmgredient
		return retVal;//return the value passed back from Money's compareTo
	}
	//returns the string representation of an ingrediebnt
	//which is the descriprtion, cost of it, and the caloric content
	@Override
	public String toString(){
		return "Description: " + description + " Cost: " + this.cost.toString() + " Calories: " + this.calorie;
	}
}
