package finalproj;
/*
 * @Author: Christopher DuBois
 * CSS 162 Final Project
 * Professor Rob Nash
 * Fall Quarter
 * Class Definition defined below
 * it is below Rob's skeleton code class
 *definition
 */

import java.util.InputMismatchException;
import java.util.Scanner;


/*  PizzaManager Skeleton File
 * 
 * 	This class is a starting point for your final project and is incomplete.
 *  Start by reading the documentation(comments), and then the code.  Find the 
 *  empty stub methods you are to fill in using a Top-Down approach (see the
 *  assignment for more information on this and Stepwise Refinement)
 * 
 *  Author: Rob Nash
 *  
 *  BELOW is my notes
 */

/*This class manages the Pizzas for our Pizza vending machine
 * it does so by offering an interface of selections a user can enter
 * to either add a hundred random pizzas, add a random pizza,
 * sort pizzas in the list by price, calorie or size
 *or offering the option to search for a particular calorie counted pizza
 *it can also reverse the order of pizzas
 *which might be useful to swap the order after it's been sorted
 *methods that are duplicates (such as the ongoing quick sorts) do not have 
 *additional inline comments but rather just one set of inline comments 
 *and each have method headers
 * @Author: Christopher DuBois
 */
public class PizzaManager {
	/*
	 *  TODO: Data definitions here.  
	 */
	//defined data definition for the Pizza Manager class, this arraylist holds only Pizzas
	public ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();

	/* 
	 * The console interface is defined in the start method 
	 * You can exit or extend the code below to accomplish all of 
	 * the outcomes defined in the homework document
	 */
	public void start() {
		
		Scanner foo = new Scanner(System.in);
		while(true) {
			displayAllPizzas();
			displayInstructions();

			System.out.println("Please enter a selection:");
			
			//Get the next scanner input, and check the char
			//of that input.
			String input = foo.next();
			char selection = input.charAt(0);

			//foo.nextChar() doesn't exist, so now what?

			switch(selection) {
			case	'A':    
			case	'a':	System.out.println("Adding a random pizza to the ArrayList<Pizza>.");
							//Add a random pizza to the list.
							//Uses pizza randomizer inside pizza class
							addRandomPizza();

							break;
			case	'H':    
			case	'h':	System.out.println("Adding one hundred random pizzas to the ArrayList<Pizza>.");

							//Add 100 random pizzas to the list.
							addHundredPizzas();

							break;					
			case	'E':    
			case	'e':	System.out.println("Eating a fraction of a pizza. How much? (a/b)");
			
							//Get the next input
							String fracInput = foo.next();

							//Try retrieving a fraction and index
							try {		
								
								System.out.println("At what index?");

								int index = foo.nextInt();

								eatSomePizza(fracInput, index);

								//If neither is valid, throw an exception
								//but continue trying to get a valid input.
							} catch (PizzaException e) {
								System.out.println("Enter a valid number");
								continue;
							} catch (InputMismatchException e) {//if something was passed in that isn't valid//catch that acception and try again
								System.out.println("Enter a valid number");
								continue;
							}

								break;		


			case	'P':    
			case	'p': 	System.out.println("QuickSorting pizzas by (P)rice");
							//Quick sort the pizzas by price
							quickSortByPrice();
							break;	
			case	'S':    
			case	's': 	System.out.println("QuickSorting pizzas by (S)ize");
							//Quick sort the pizzas by size
							quickSortBySize();
							break;		  
			case	'C':    
			case	'c':  	System.out.println("QuickSorting pizzas by (C)alories");
							//Quick sort by calories
							quickSortByCalories();
							break;
			case	'B':
			case	'b':	System.out.println("(B)inary search over pizzas by calories(int).  QuickSorting first. What calorie count are you looking for?");
							//Search for specific calories
							System.out.println("Enter calories: ");
							try {
							int findCal = foo.nextInt();
							binarySearchByCalories(findCal);
							} catch (PizzaException e) {
								System.out.println("Not valid input.");
								continue;
							}
							break;


			case    'R':
			case 	'r':	System.out.println("(R)everse order of Pizzas with a Stack" );
							//Reverse the listing of the strings.

							pizzaList = reverseOrder(pizzaList);
							break;


			case    'Q':
			case 	'q':	System.out.println("(Q)uitting!" );
							//Exit out of the program.
							System.exit(0);

			default:	System.out.println("Unrecognized input - try again");
			}
		}

	}

	//this method eats a portion of a pizza by taking 
	//in a string as a fraction, 
	//and converting it into a fraction, and taking 
	//that fraction away from the fraction 
	//of pizza remaining at a specific index in the pizza arraylist
	private void eatSomePizza(String str, int index) {

		Pizza toEat = null;//initialize the Pizza variable
		if (index < pizzaList.size()) {//if the index entered is valid
		toEat = pizzaList.get(index);//reInitalize the toEat Pizza by getting it at the index
		} else {//if it's not a valid index then restart
			System.out.println("Please enter a valid index");
		}
		
		String[] fractionSplit = str.split("/");//turn it into a fraction array 
		//by splitting the argument parameters into a String array

		int num = 0;//set the numerator of the fraction to zero
		int den = 0;// and the denominator of the fraction to zero

		//Break down String input into integers
		for (int i = 0; i < fractionSplit.length; i++){//for the length of the split fraction string array so do this for 
			//both the num and denom
			String number = fractionSplit[i];//get the num and denominator as numbers
			Scanner getTheNumber = new Scanner(number);//initialize a scanner that takes in these numbers

			if (i == 0){//for the first index
				num = getTheNumber.nextInt();//reset the numerator from the scanner and the string array
			} else if (i == 1 && num != 0) {//the second index and its a nonzero number
				den = getTheNumber.nextInt();//get the denominator from the scanner and the string array
			} else if (i == 0 && num == 0){//nonzero number on top == 0 fraction
				num = getTheNumber.nextInt();//get it again
			}
		}
		
		if (num > 0 && den > 0 ) { //as long as it's a fraction that is real or not zero
			
			//then make a fraction
			Fraction2 eat = new Fraction2(num, den);

			//as long as we have a real pizza to work with
			//to knead the dough
			//and bake...
			//and add peppers and olives and meat
			//to make the supreme goat cheese pizza :)
			if (toEat != null) {
				//then we call the eatFraction method and eat a certain amount of pizza from that pizza
				toEat.removeFraction(toEat, eat);
				//and we update the cost
				toEat.decreaseCost(toEat, eat);
				//update the calories
				toEat.decreaseCalories(eat);
				//update the size
				toEat.removeAmount(eat);
				//If the remainder of the pizza is 0, remove that pizza from the list
				if(toEat.getRemainder().getNumerator() == 0){
					System.out.println("Pizza at index " + index + " has been removed");//tell the user what happend
					pizzaList.remove(index);//remove at that specific index
				}

				if (pizzaList.get(index) == null) {//if what were looking 
					//at is a real pizza and not something that is null
					System.out.println("We're eating a pizza that is nothing");
					throw new PizzaException ("can't eat a nonreal pizza");
				}

			} 
		} else {
			throw new PizzaException("Tried to eat a null or nonreal pizza");
		}

	}

	//call the randomPiozza generator from the Pizza class
	//and add it to the arrayList
	private void addRandomPizza() {
		//todo:

		pizzaList.insert(new Pizza());//make a new pizza, insert it to the list

	}

	//add one hundred pizzas using a for loop
	private void addHundredPizzas() {
		for(int i = 0; i < 100; i++){
			pizzaList.insert(new Pizza());//adds each to the end of the list
		}	
	}

	//call the toString on the array list of pizzas and
	//print it to the console
	private void displayAllPizzas() {
		System.out.println(pizzaList);
	}


	//sort all the pizzas in the arraylist using the recursive quick sort
	//it was stated to use quicksort initially
	//then switched to selectionSort
	//but watching the video on how quicksort works was pretty cool
	//so a recursive (for length of code) implementation
	//found, studied, and adapted from sources on the implementation 
	//were used
	private void quickSortByPrice() {  

		//just like a normal facade, we give it the array, the starting index, 0, and the ending index, length -1
		quickSorter(pizzaList, 0, pizzaList.size() - 1);
	}

	//actually sorts the pizzas
	private void quickSorter(ArrayList<Pizza> toSort, int start, int end) {
		
		//if the start, is over the end
		//we are done
		if( start >= end) {
			return;//then we're done
		}

		//the point pivot is used to analyze the partitions of the sorted array list
		//we use pivot and update it recursively to increment one side to show the partition increasing
		//and decrement it from the other side to show the partition incrementing from the other end
		//this is similar to the binary search
		int pivotPoint = privateQuickSort2(toSort, start, end);//calls the other helper method
		
		//Sort the low half of the list, then the high half of the list.
		quickSorter(toSort, start, pivotPoint - 1);//update the partition
		quickSorter(toSort, pivotPoint + 1, end);//update the partition
	}

	
	private int privateQuickSort2(ArrayList<Pizza> sort, int low, int high) {
		int i = low + 1;
		int j = high;

		//as long as the lower index is less than the top index
		while ( i <= j) {

			//find where the pizza should go using the comparable interface
			if (sort.get(i).compareTo(sort.get(low)) <= 0) {
				i++;//increment at that next index
				//find the piza spot the other side
			} else if (sort.get(j).compareTo(sort.get(low)) > 0) {//drop it drop it low
				j--;//decrement the pizza
			}

			//this will end the loop and stop the sort
			if (j < i) {
				break;
			} else {
				//calls the swap method defined in arraylist
				sort.swap(i, j);
			}
		}
		
		//swap the items appropriately
		//from the lower spot(shown by i)
		//and the higher spot(shown by j)
		sort.swap(low, j);
		return j;//return the index swapped at the top spot		
	}

	//the same as the quick sort by price essentially
	//except calls a different compareTo
	//so no comments are added because it's the same implementation as above
	private void quickSortBySize() {
		quickSortSizer(pizzaList, 0 , pizzaList.size() - 1);
	}

	private void quickSortSizer(ArrayList<Pizza> sort, int start, int ender) {
		if( start >= ender) {
			return;
		}

		int pivot = privateQuickSort3(sort, start, ender);
		quickSortSizer(sort, start, pivot - 1);
		quickSortSizer(sort, pivot + 1, ender);
	}

	private int privateQuickSort3(ArrayList<Pizza> sort, int low, int high) {

		int i = low + 1;
		int j = high;

		while ( i <= j) {

			if (sort.get(i).compareToBySize(sort.get(low)) > 0) {
				i++;
			} else if (sort.get(j).compareToBySize(sort.get(low)) <= 0) {
				j--;
			}

			if (j < i) {
				break;
			} else {
				sort.swap(i, j);
			}
		}
		sort.swap(low, j);
		return j;		
	}


	//this is the same as a above
	//its just like quicksort by size and by cost
	//except the compareTo methods are called accordingly 
	//for the right characteristic to 
	//sort by, thus no inline commented are ADDed because 
	//its the same implementation
	private void quickSortByCalories() {
		quickSortingCalories(pizzaList, 0, pizzaList.size() - 1);
	}

	private void quickSortingCalories(ArrayList<Pizza> sort, int low, int high) {
		if( low >= high) {
			return;
		}

		int pivot = privateQuickSortCalories3(sort, low, high);
		quickSortingCalories(sort, low, pivot - 1);
		quickSortingCalories(sort, pivot + 1, high);
	}

	private int privateQuickSortCalories3(ArrayList<Pizza> sort, int low, int high) {

		int i = low + 1;
		int j = high;

		while ( i <= j) {

			if (sort.get(i).compareToByCalories(sort.get(low)) > 0) {
				i++;
			} else if (sort.get(j).compareToByCalories(sort.get(low)) <= 0) {
				j--;
			}

			if (j < i) {
				break;
			} else {
				sort.swap(i, j);
			}
		}
		sort.swap(low, j);
		return j;		
	}
	
	//typical facade which takes in the calories to search for
	//and calls the binary search private method
	//implements recursive because Nash likes recursion
	//can be elegant solutions in complicated situations
	//such as the first instance of combining classes together
	private void binarySearchByCalories(int cals) {
		//todo:

		//sort it by calories before we search
		//the precondition of a binary search is that there is a constrained order
		//to search by
		//in order to get the proper bigO by halving the amount we have to look for
		//each time it searches until it finds (or doesn't) find the item we are looking for
		quickSortByCalories();

		int showSpot = binaryRecSearch(pizzaList, cals);

		//display to the console the pizza at the right index
		System.out.println("Pizza at index " + showSpot + " has " + cals + " calories.");
	}

	//the private method that actually searches
	private int binaryRecSearch(ArrayList<Pizza> list, int cals) {
		
		//this private method isn't technically necessary
		//but during my creation, was easier to keep track of
		//and although it is less efficient on memory
		//because it creates an unnecessary 32 bits in memory to store the showSpot index
		//it implements binary search correctly below
		return recBinSearch(list, cals, 0, list.size() - 1);
	}

	//actually searches
	//using the binary search
	//recursive implementation
	private int recBinSearch(ArrayList<Pizza> list, int cals, int below, int above) {

		//this will return -1 if not found
		int retVal = -1;

		int mid = (below + above) / 2;//the middle is between the below and above 

		//this means that the bars of search have overlapped
		if( below > above ) {
			throw new PizzaException("Cannot locate the pizza with those particular calories");//can't find it throw the Pizza exception
		} else {
			//get the calories at the spot
			//compare and further the binary search
			if(list.get(mid).getCalorie() > cals){
				above = mid - 1;
				//search from the new above
				retVal = recBinSearch(list, cals, below, above);

				//check at the other side
			} else if (list.get(mid).getCalorie() < cals) {
				//update the places to look from
				below = mid + 1;
				//search from the new below
				retVal = recBinSearch(list, cals, below, above);
				//otherwise, the last case, defined as else if to double check that they are actually equal
				//they are equal at the midpoint, so we can return 
			} else if (list.get(mid).getCalorie() == cals) {
				retVal = mid;
			}
		}
		if(retVal == -1) {//checks to see if we found the pizza with those calories.
			throw new PizzaException("No pizza like that.");//we didn't find it
		}
		return retVal;//return the value if it was found
	}

	//this will reverse the orders of the pizza in the pizzaList by
	//using the characyeristics of a stack type
	//fill it up and pop it out into a new arraylist
	//implemented actually using the ArrayList 
	//Stacks add and remove at the same end
	//and this is why they have that characteristic of printing out backwards (or popping) backwards
	//so this is easily implemented with using an arrayList
	//and actually shows how it works more clearly in code
	//moreover, stacks are "dinky" lists :)
	public static ArrayList<Pizza> reverseOrder(ArrayList<Pizza> pList){
		//the copy array declared
		ArrayList<Pizza> copyArr = new ArrayList<Pizza>();
		//for the length of the pizzaList passed in
		for(int i = pList.size(); i > 0; i--) {
			//the pizza at the very end
			//to be held and added back
			Pizza holderVal = pList.get(i - 1);
			copyArr.insert(holderVal);//copy it into the copy arraylist
		}
		//return the backwards list added from the opposite ends
		return copyArr;

	}


	/*
	 * No need to edit functions below this line, unless extending the menu or
	 * changing the instructions
	 */
	private static final String instructions = "-----------------------\nWelcome to PizzaManager\n-----------------------\n(A)dd a random pizza\nAdd a (H)undred random pizzas\n(E)at a fraction of a pizza\nQuickSort pizzas by (P)rice\nQuickSort pizzas by (S)ize\nQuickSort pizzas by (C)alories\n(B)inary Search pizzas by calories\n(R)everse order of pizzas with a stack\n(Q)uit\n";
	private void displayInstructions() {
		System.out.println(instructions);	
	}
	/*
	 * Notice the one-line main function.
	 * Halo.Go!
	 * Forza.Race!
	 * Chris.Succeed?
	 */
	public static void main(String[] args) {
		new PizzaManager().start();
	}
}


