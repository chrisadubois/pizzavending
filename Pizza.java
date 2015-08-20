package finalproj;
import java.text.DecimalFormat;//pretty prints

/*@Author: Christopher DuBois
 * CSS 162
 * Professor Rob Nash
 * Fall Quarter
 * Final project
 * this class is the foundation of what a pizza is
 *it uses a random number generator
 *created by java.util classes as a static double generator
 *that makes numbers from 0-1 as a double
 *if we multiply that number by an integer value 
 *we are able to produce any range of numbers we would like
 *we can then cast that number down to an integer value
 *and use switch statements to create random opportunities
 *for a pizzas ingredients when a pizza is created
 *that is, this class creates pizzas, and holds the getters, setters
 *and to string for what a pizza is and has for the
 *pizza vending machine.  it also has compareTo methods which
 *are called by the sorting mechanism defined in PizzaManager
 *in order to sort according to the directions provided
 *Thanks for a good quarter!
 */
public class Pizza implements PizzaComparable {//the contract to abide by defined in PizzaComparable class given to us
	//data definition as an arrayList
	//which holds ingredients in the arraylist
	private ArrayList<Ingredient> ingredientArray = new ArrayList<Ingredient>();
	//data definition
	//the cost of a pizza starts at a value of zero
	//pretty prints
	private DecimalFormat dF = new DecimalFormat("#.00");//pretty prints
	private Money cost = new Money();
	//the Pizza will be a null shape off the bat
	private Shape pizzaShape;
	//since the Pizza is technically kind of null right now
	//there will be no calories in it
	private int calories;
	//there is a Null fraction off the bat
	//because the pizza is null
	//there is no fraction to be had DNE
	private Fraction2 pizzaRemaining;
	//calls the random pizza generator
	//gives it ingredients
	//updates calories, size, ingredients, costs
	//etc
	public Pizza() {//this is the no arg constructor 
		//it will create a random Pizza by calling a method that generates everything
		//using switches and the math.random (casted to an int) to produce the right case numbers
		randomPizzaGenerator();
	}

	//eatSomePizza 
	//remove an amount of pizza from the pizza calling object
	//it will update the amount of pizza by multiplying the current area of the pizza
	//by the fraction being passed in
	public void removeAmount(Fraction2 f) {
		double currentArea = pizzaShape.getArea();//hold the current area of the pizza
		//get the numerator and denominator from the fraction
		//being passed in
		int num = f.getNumerator();
		int den = f.getDenominator();
		//the computation for the new area 
		double reducedArea = (currentArea * num) / den;
		//reset the area of the pizza by the above value
		pizzaShape.setArea(reducedArea);
	}

	//eat some pizza
	//remove a particular fraction of pizza from the pizza calling object
	//the computation is cross multiplication on fractions
	//using the methods defined in the fraction class to reduce
	//the fraction of pizza remaining
	public void removeFraction(Pizza p, Fraction2 f) {
		Fraction2 pizzaFrac = p.getRemainder();//hold the fraction value of the pizza being passed in 
		//holder value for cross multiplication
		int numerator = ( f.getDenominator() * pizzaFrac.getNumerator() ) - ( pizzaFrac.getDenominator() * f.getNumerator() );
		//then multiply the denominators
		int denominator = ( f.getDenominator() * pizzaFrac.getDenominator() );
		//if the amount remaining is going to be 0
		//this will be shown by the numerator value if it is 0, the fraction is nothing
		//and therefore a whole pizza has been eaten at the specified index in the pizza array
		if(numerator == 0) {
			//all of this makes the pizza dissapear		
			p.setRemainder(new Fraction2(0, 0));
			p.resetCost();
			calories = 0;
			pizzaShape.setArea(0.0);
			//this means there is no pizza left
			//so throw an exception
		} else if( numerator < 0 || denominator < 0){
			throw new PizzaException("No more pizza");
		} else {
			//Create a fraction that is left over.
			Fraction2 afterEating = new Fraction2(numerator, denominator);
			//Set the remaining fraction to this pizza.
			p.setRemainder(afterEating);//reset the amount on the pizza after a pizza has been eaten from
		}
	}

	//reduce the amount of calories left in the pizza
	//defined by the fraction that was removed from the pizza
	public void decreaseCalories(Fraction2 f){
		//hold the values of the the fraction being passed in 
		//as two seperate variable values
		int numHolder = f.getNumerator();
		int denomHolder = f.getDenominator();
		//as long as the denominator is greater than 0
		if (denomHolder > 0) {
		//then we can update the calories by multiplying across the top
		calories = (calories * numHolder) / denomHolder;
		} else {
			throw new PizzaException("Tried to divide by zero.");//otherwise the denom is 0 and that is impossible
			//DNE
		}
	}

	//reduce the cost of the pizza
	//this will reduce the cost of the 
	//pizza by the amount defined
	//by the fraction eaten
	public void decreaseCost(Pizza p, Fraction2 f) {
		double moneyHolder = p.getCost().getMoney();//holder value for the amount of money
		//hold the values from the fraction being passed in
		int numHolder = f.getNumerator();//hold the value of the numerator
		int denomHolder = f.getDenominator();//hold the value of the denominator
		double newMoney = (numHolder * moneyHolder) / denomHolder;
		//take the newMoney and 
		//step by step decompose it into integer values for dollars
		//and cents
		double intBreaker = newMoney;//holder to bring it down into a dol,cent
		int dollars = (int) intBreaker;//the dollar is the (casted) integer value
		//of the newMoney double
		double c = intBreaker - (double) dollars;//then we use the other holder value to 
		//set the value of cents
		int cents = (int) (c * 100);//turn it into something that isn't a decimal
		//reset the value of the pizza by calling the set in the Money class
		cost.setMoney(dollars , cents);
	}
	
	//getters and setters below for organization
	
	//simple getter
	//deep copy for a fraction object calling the copy constructor
	//(could call .clone() ) but the clone
	//redirects to the copy constructor anyway
	public Fraction2 getRemainder(){
		return new Fraction2(pizzaRemaining);
	}
	//return the leash to the cost of the pizza now
	//this will return the Money object for the pizza
	public Money getCost(){
		return cost;
	}
	//this method sets the cost of the pizza 
	//down to 0 dollars and 0 cents
	public void resetCost(){
		cost = new Money(0, 0);
	}
	//Set the fraction of this pizza to 0.
	//make a new fraction because fractions are final 
	//once they have been created
	//so you must set a new one
	public void setRemainder(Fraction2 f){
		pizzaRemaining = new Fraction2(f);
	}
	//simply return the integer
	//value of the pizza calling object
	public int getCalorie(){
		return calories;
	}
	//simply return the area method defined in
	//the shape classes
	public double getRemainingArea() {
		return pizzaShape.getArea();
	}
	//set the shape of the pizza calling the clone
	//defined in the shapes classes
	public void setShape(Shape s){
		pizzaShape = (Shape) s.clone();
	}
	//deep copy of the shape
	//calling the clone
	//which redirects to the 
	//copy constructor
	public Shape getShape(){
		return (Shape) pizzaShape.clone();
	}
	//return the size of the pizza
	//by calling on the shapes getArea method
	public double getPizzaSize(){
		return pizzaShape.getArea();
	}
	//sets the size of the pizza
	//to the input passed in
	public void setSize(double area51){//:)
		pizzaShape.setArea(area51);
	}
	
	//this method will add an ingredient according 
	//to what is passed in devised by the random
	//pizza generator which adds varieties of ingredients
	public void addIngredient(Ingredient ingr){
		//we need to insert into our ingredientArrayList 
		//this arraylist will hold everything put onto the pizza
		ingredientArray.insert(ingr);
		//add the calories according to what was added 
		//going to that classes defined calories
		calories = calories + ingr.getCalorie();
		//add the cost to the Money object according to what ingredient 
		//that was added
		cost.add(ingr.getCost());
	}
	//this is the bulk
	//of what creates the pizza
	//it utilizes switch statements to check cases for the 
	//different combinations that can go onto a pizza
	//the casted integer value
	//of a math.random call everytime the pizza generator
	//is invoked will add different ingredients sequentially
	public void randomPizzaGenerator() {
		//define the sauce first for the pizza
		//initialize it
		Ingredient sauce = null;
		switch ( (int) (Math.random() * 2)) {//two options for sauce
		case 0: sauce = new Marinara();
		break;
		case 1: sauce = new Alfredo();
		break;
		}
		//add whichever was chosen from the random number
		//generator
		addIngredient(sauce);
		//next to go on a pizza is the cheese
		//initialize it
		Ingredient cheese = null;
		switch ( (int) (Math.random() * 2) ) {//two options for cheese
		//you can't have both from a vending machine
		case 0: cheese = new Mozzarella();
		break;
		case 1: cheese = new Goat();
		break;
		}
		//add the type of the cheese onto the pizza being created
		addIngredient(cheese);
		//you can definitely have both types of meats
		//on our pizza
		//so set a meat variable
		//and a both variable
		Ingredient meat = null;
		Ingredient bothM = null;
		int addMeats = (int) (Math.random() * 3);//three options
		switch ( addMeats ) {

		case 0: meat = new Sausage();//sausage
		break;
		case 1: meat = new Pepperoni();//pepproni
		break;
		case 2: meat = new Sausage();//or both meats
		bothM = new Pepperoni();
		}

		if ( addMeats == 0 ) {//according to the cases above
			//because we have two variables
			//we can't just add "meat"
			//we must have options for each
			addIngredient(meat);
		} else if (addMeats == 1) {//add the pep
			addIngredient(meat);
		} else if (addMeats == 2) {//add the meats
			addIngredient(meat);
			addIngredient(bothM);
		
		}
		//this will do the same for the veggies 
		//as it does for meats
		Ingredient veggie = null;
		Ingredient bothV = null;
		int addVeggies = (int) (Math.random() * 3);
		switch (addVeggies) {

		case 0: veggie = new Olive();
		break;
		case 1: veggie = new Pepper();
		break;
		case 2: veggie = new Olive();
		bothV = new Pepper();
		break;
		
		}
		//follows the same reasoning as the above meats
		//need a case for each variety 
		//either 1 veg
		//or both
		if ( addVeggies == 0 ) {
			addIngredient(veggie);
		} else if (addVeggies == 1) {
			addIngredient(veggie);
		} else if (addVeggies == 2) {
			addIngredient(veggie);
			addIngredient(bothV);
		}
		//use the math.random function
		//to define the shape of the pizza

		switch ( (int) (Math.random() * 2) ) {

		case 0: pizzaShape = new Circle();
		break;
		case 1: pizzaShape = new Square();
		break;
		}
		
		//when the pizza has been created
		//we automatically have the full amount
		//so set the fraction to be 1/1
		pizzaRemaining = new Fraction2(1, 1);// a full pizza
	}
	//compareTo defined by money
	//it will compare the money values of the pizza
	//so it will call
	//money's defined compareTo method
	public int compareTo(Object o) {
		if( o == null || !(o instanceof Pizza)){//null check, pizza check
			throw new PizzaException("give me a pizza to compare");
		}
		//cast to compare
		Pizza that = (Pizza) o;
		//compare using moneys
		//if this cost is greater than that cost
		if (this.cost.compareTo(that.getCost()) > 0) {
			return -1;
			//if this cost is less than that cost
		} else if (this.cost.compareTo(that.getCost()) < 0) {
			return 1;
		}
		//otherwise
		//the money objects are equal so return 0
		return 0;
	}
	//this method will compare pizzas by size
	//it uses the shapes
	//and primitives to simply compare values
	//and accordingly implement the comparable directions
	public int compareToBySize(Object o) {
		if( o == null || !(o instanceof Pizza)) {//null check, pizza check
			throw new PizzaException("give me a pizza to compare");
		}
		//that to o
		Pizza that = (Pizza) o;
		//if this pizza is less than that pizza
		if(this.getPizzaSize() < that.getPizzaSize()) {
			return 1;
		//if this pizza is grater than that pizza
		} else if (this.getPizzaSize() > that.getPizzaSize() ){
			return -1;
		}
		//otherwise, the sizes are equal and we can return 0 to 
		//represent equivcalency
		return 0;
	}

	//compare the pizzas by their caloric
	//values
	//this is similar to the compareBySize
	//in that it compares calories
	public int compareToByCalories(Object o) {
		if ( o == null || !(o instanceof Pizza)) {//null check, pizza check
			throw new PizzaException("That's not a pizza");
		}
		Pizza that = (Pizza) o;//cast
		//if this is less than that
		if( (calories < that.getCalorie())) {
			return 1;
		//if this is greater than that
		} else if (calories > that.getCalorie()){
			return -1;
		}
		//otherwise
		//return 0 to represent equivalency in the
		//caloric values
		return 0;
	}

	//the toString for a pizza
	//it shows the cost of the pizza, the calories in the pizza
	//as determined by the ingredient array
	//and the fraction/area remaining in the pizza
	public String toString(){
		return "Pizza has a price of " + cost.toString() + " and " + calories + " calories, Fraction remaining " + pizzaRemaining.toString() + " With area: " + dF.format(pizzaShape.getArea()) + " with a shape: " + pizzaShape.toString();
	}

}

