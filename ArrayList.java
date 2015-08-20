package finalproj;

/*@Author: Christopher DuBois
 * This is a user-created ArrayList class that should operate exactly as the built-in 
 * java ArrayList class.  The arraylist is a data structure that can take in any class of Objects, 
 * but in this case because it accepts generics, once initialized, the ArrayList will only take in
 * objects of the type noted in the first initilization parameters.  The ArrayList will not run out of
 * room like a normal array, but still uses contiguous space in memory to store Objects.  This class
 * will also implement the Iterable interface, which allows for the user to use the for each loop
 * it only takes in objects which are comparable, and it is a cloneable arraylist, as well as an
 * arraylist that can be compared to another arraylist for inequality 
 */

import java.util.Iterator;
//inserts should change to Type remove should return type, same with get
public class ArrayList<Type extends Comparable> implements Iterable<Type>, Cloneable, Comparable {//The class header, only takes in comparable Objects,
	//the for each loop may be called on it
	//it is cloneable thus .clone is valid
	//and .compareTo(ArrayList other) can be called as well due to the fact that it is a comparable arraylist
	
	//instance variable declaration, this arraylist will have a 'list' of objects stored in an array taking up contiguous space in memmory
	private Object[] list;
	
	//instance variable declaration, this arraylist will have a number of elements to show the true size of the array
	private int numElem;
	
	//Constructor which takes in an array of a Type that is comparable and creates an arraylist out of this array
	//this is useful in the case where you need a resizable array (if the amount of data to be stored is unknown)
	public ArrayList(Type[] arr) {//array constructor
		int size = 0;//set size to 0
		for (int i = 0; i < arr.length; i++) {//for the length of the argument array
			if (arr[i] != null) {//checks at every spot to determine numElem
				//as long as it is not null at that spot, a size will be incrmeneted
				size++;
			}
		}
		this.numElem = size;//since a new arraylist was created, set the instance variable
		this.list = new Object[size];//make the object array of the particular numElem
		for (int i = 0; i < size; i++) {//loop through to copy over everything
			this.list[i] = arr[i];//copies the arr into list
		}
	}
	
	
	//Constuctor which creates an ArrayList of a size determined by the user specificed by the parameter int length
	//will not make an arraylist longer than 50,000 spots in contiguous memory
	public ArrayList(int length) {//constructor of arb length no elements less than 50k
		if (length < 50000) {//as long as the parameter is less than 50000
			this.numElem = 0;//there are no elements in it so it just saves the spots for the arraylist but no elements so size is 0
			this.list = new Object[length];//initializes the object list to have that many spots avaialble
		} else {//otherwise break out
			System.out.println("OverFlow");//print a message to the screen
			System.exit(-1);//end the program
		}
	}

	//The no arg consstuctor 
	//this will create the ArrayList and make it automatically have storage spots for 100 objects but will have a size of zero
	//the array at this point could then be filled with the Object of the parameter type defined above
	public ArrayList() {//no arg constructor
		int a = 100;//set a variable to make the Object list of a particular size (as an array declaration)
		this.numElem = 0;//there are no spots that are filled up, that is each element of the Object list contain a null value
		this.list = new Object[a];//make the array of size 100
	}
	
	
	//This is the copy constructor for the arraylist
	//it takes in an arraylist and creates an entirely new one, free of privacy leaks 
	//this definition should not be used but rather it is utilized for the clone() method 
	public ArrayList(Type o) {//copy constructor, and pulls the elements and copies them into this.list
		if (o == null || !(o instanceof ArrayList)) throw new IllegalArgumentException("cannot copy a null arraylist or something that isn't an arraylist");
		//if the type (object) being sent in is a null object, then throw an exception, if it's not an arraylist, it means we can't copy it so throw an exception
		ArrayList that = (ArrayList) o;//make a cast
			this.list = new Object[that.size()];//initialize the object list's size to the size of the other arraylist by calling the other arraylist's size method
			for (int i = 0; i < that.size(); i++) {//for the length of the other array starting at index 0 we want to copy over everything
				this.list[i] = (Type) that.get(i);//we copy over all of the elements, even the spots that are empty  
			}
			this.numElem = that.size();//now we set the instance variable of number of elements from the other arraylist
	}
	
	//this method is the compareTo method for the Arraylist in order to implement the Comparable interface
	//it compares the arraylists by using the objects inside compareTo method
	//this works because arraylist takes in only objects that are of type Comparable
	//moreover because arraylist only takes in objects of the same type
	//(ie we have an array of ONLY gorillas, or an array of ONLY CSS 162 Students) we can simply call that objects compareTo method by getting
	//the object at that particular index and comparing them for return values
	//this method will sum up all of the compareTo's in each arraylist to compare and return the value if "this" arraylist is larger (1) or if
	//"this" arraylist is smaller (-1)
	//this assumes the two arrays are of the same size because it only goes for the length of the "this" arraylist
	@Override
	public int compareTo(Object o) {
		int retVal = 0;//set the return value to zero in order to begin the summation
		if (o == null || !(o instanceof ArrayList)) throw new IllegalArgumentException("Cannot pass in a null arraylist or something that is not an arraylist");
		//if the thing passed in is null or not an arraylist we want to throw an exception and break
		ArrayList that = (ArrayList) o;//cast to an arraylist
		for (int i = 0; i < numElem; i++) {//for the entire length of the array of (this)
			if (this.get(i).compareTo(that.get(i)) > 0) retVal++;//increments the retVal if "this" compareTo at i is larger (>0)
			else if (this.get(i).compareTo(that.get(i)) < 0) retVal--;//decrements if "this" compareTo at i is smaller (<0)
			else retVal = retVal;//otherwise, if they are equal, retVal remains the same
		}
		return retVal;//return the return value as positive, negative or zero;
	}
	//this method is in order to implement the clonable interface
	//it calls the copy constructor and returns a new arraylist using the copy constructor when this method is called
	public ArrayList clone() {
		return new ArrayList( (Type) this);
	}
  //simple return of numElem (number of elements is always incremented or decremented accordingly in each method)
	public int size() {
		return this.numElem;//how many elements are in the array
	}
//simple return if there are no elements in the array because numElem is allways ++ or -- accordingly in each method
	public boolean isEmpty() {//if there are no elements in the array
		return numElem == 0;
	}
//simple return if the number of elements is the same as the lists array length
	private boolean isFull() {//checks to see if all the elements have something in it
		return numElem == this.list.length;
	}

	//this method resizes the array in order to account for "too many" elements being added to the array
	//this method will make it so that there is no way the array could get full unless you fill up all the space in your computers memory
	//it does so by creating a temporary array and copying over everything from the first array into the copy array
	//it then reinitializes the object lists length and copies the stuff in the temporary array over
	private void resize(int resizeValue) {//resizes it at a specific amount, and then copies into an array, and copies back with that new size allocation
		Object[] copyArr = new Object[resizeValue];//create a new object array with a resize value (which is always length *2 hardcoded)
		for (int i = 0; i < this.numElem; i++) {//for the length of the array to be copied over
			copyArr[i] = this.list[i];//copy over everything accessing the element at i
		}
		this.list = new Object[resizeValue];//reinitialize the object list size
		for (int i = 0; i < this.numElem; i++) {//copy everything back over
			this.list[i] = copyArr[i];//by accessing the element at i
		}
	}

	/*private void shiftRight(int shiftAtIndex)broken doesnt work {//shiftright of a particular index to move elements over for an index insertion
		for (int i = shiftAtIndex; i < this.numElem; i++) {broken doesnt work
			//this.list[i+1] = this.list[i];broken doesnt work
		}starts at the wrong side of the array
		you must start at the end of the array and work your way backwards otherwise things get erased in the shift right
	}*/ 
	
	//this is the real shiftRight method
	//this is used for inserts into the array
	//it shifts over the index if the something needs to be added at an index that already contains a value
	private void shiftRight(int shiftAtIndex) {
		for (int i = numElem-1; i >= shiftAtIndex; i--) {//start at the second to last REAL element, end at the element to be shifted at
			this.list[i+1] = this.list[i];//make the list at the further index to the right equal to the stuff at the index previous to it
		}
	}

	//this is used for removals from the array
	//it shifts over the index if something is removed
	private void shiftLeft(int shiftAtIndex) {//same as above shiftleft index insertion
		for (int i = shiftAtIndex; i < this.list.length - 1; i++) {//starting at the shift left index we want to go all the way to the end of the array and shift everything else over
			//Type o = this.list[i+1];
			this.list[i] = this.list[i+1];//the stuff at the first index is equal to the stuff at the index to the right of it, and by doing this
			//over the entire length of the array, we will move everything over to the left
			//this.resize(this.list.length);
		}
	}
	
	
	//this is the insert method for the arraylist
	//it takes in the generic type and inserts at a particular index specified by the user
	//if it adds to the end, nothing is shifted
	//if it is full, it resizes so as to never seem to be full by the user
	//if it wants an index past the length or the size of the array we resize so as to seem unbreakable to the user
	//if it adds to the middle or the beginning, everything is shifted over
	public void insert(Type o, int index) {//insert an Type at a particular index
		if (o != null) {//as long as the thing being passed in is not null
			if (this.isFull()) {//if the array is full
				this.resize(numElem * 2);//resize the array
				if (index < this.size()) {//if index in middle of the array
					this.shiftRight(index);//we need to shift everything over to the right
					this.list[index] = o;//set the stuff at the particular index after the shifting is done and overwrite that space
				}
				if (index >= this.size()) {//if index is after the array
					this.resize(index + 1);//then we need to resize by the index value + 1 for the extra spot
					this.list[index] = o;//and set the variable at that index specified equal to o overwriting null
				}
			} else {
				if (index < this.size()) {//if index in middle of array
					this.shiftRight(index);//do the same thing as above if it's not full except don't resize because that increases the bigO
					this.list[index] = o;//overwite as above
				}
				if (index >= this.size()) {//if index is after the array
					this.resize(index + 1);//even if it's not full, we want to resize it just incase
					this.list[index] = o;//overwrite the values
				}
			}
			numElem++;//everytime this method is called, there is one element being added so we must increment the number of elements in the arraylist
		}
	}
	
	//this method uses the same algorithms as the insert above, except no index is needed to be specified by the user
	//in this method, a user can simply add the generic Type and it will be added on to the end of the array (the end at size, not the end at the lists length)
	public void insert(Type o) {//calls the master insert, adds onto the end
		this.insert(o, this.numElem);//use the master insert with the type, and hardCode at numElem
	}
	
	//this method removes objects out of the arraylist at specificed indices
	//it will shift over the array to the left when called 
	//and does not work if you try to call it on a spot that doesn't exist in the array
	public Type remove(int index) {
		//remove something at a particular spot, return it and shift things over left to cover up the spot
		if (index >= this.numElem) { throw new NullPointerException("Cannot remove from a space that holds no data"); 
			//return "Error, cannot remove from nonexistence";
		} else {//otherwise
			Type retVal = (Type) this.list[index];//the return value for the remove 
			this.shiftLeft(index);//we want to call the shift left method from above to move everything over
			this.list[this.list.length-1] = null;//at the very end of the array, make it a null value so we don't have to resize
			numElem--;//everytime this method runs, it should decrease the true size of the array
			return retVal;//return the object because that's what remove methods do
		}
	}
	
	//this method will check to see if two arraylists are equal
	//it does so by using the objects equals method and checking at every index
	//if something is not equal it automatically throws a false
	//if one arraylist is a different size than that arraylist it throws a false
	public boolean equals(Type o) {//checks at lengths, then checks at each index
		if (o == null || !(o instanceof ArrayList)) throw new IllegalArgumentException ("cannot do equals on something that is not an arraylist or something that is null");
		//if the arraylist passed in is null or if its not an arraylist, break
			ArrayList that = (ArrayList) o;//cast
			if (this.list.length == that.list.length) {//check the sizes of the arrays
				for (int i = 0; i < this.list.length; i++) {//for the entire length of the array (not just the size)
					if (!this.list[i].equals(that.list[i])) {//we want to check the values at i
						return false;//if something doesn't equal, automatically return false
					}
				}
			}
			return true;//if it passes here, everything is equal, so we can return true
		}
	
	//this method will "get" the object at a specificed index and return it
	//it only does so as long as the index is a valid index
	public Type get(int index) {
		if (index < this.list.length) {//makes sure the index is inbounds
			return (Type) this.list[index];//returns that value
		}
		return (Type) "Cannot return a value that is nonexistent";//otherwise, we simply state we can't return the value and do nothing else
	}
	
	//this method will search for a specific object asked for by the user
	//if it doesn't find the object in the arraylist to return the index value it will return a -1 to indicate this
	//it checks every single index of the arraylist and uses the equals method to match the target object parameter to the arraylist at index i
	public int indexOf(Type o) {//searches for a particular Type using the equals and iterating through the list
		if (o == null) throw new IllegalArgumentException("Cannot search for the indexOf a null Type");//if we're looking for null, break
		if (o != null) {//if it's not null
			for (int i = 0; i < this.list.length; i++) {//for the entire length of the array
				if (this.list[i].equals(o)) {//check the indices for the target
					return i;//if we find it, return that index
				}
			}
		}
		return -1;//otherwise return -1
	}
	
	
	//this class within the arraylist class was made to implement the iterable interface
	//this iterator class makes it able to use the for each loop on the arraylist to say, 
	//print out all of it's elements if you didn't want to 
	//think about how many elements it probably has
	//more over it makes it so you can create an iterator object and iterate through the array to print
	//by checking the hasNext(0) method and using the .next method to pull out that item
	public Iterator<Type> iterator() {//set the class to only for the type passing into the array
		Iterator<Type> it = new Iterator<Type>() {//make a new Iterator of that type
			private int index;//set it's own instance variable

			@Override
			public boolean hasNext() {//a boolean method defined in the iterator class that must be overriding implementing the iterable interface
				return index < numElem && list[index] != null;//as long as the index is less than numElem in the arraylist and the list at the specific index to go to is not null
			}
			@Override
			public Type next() {
				return (Type) list[index++];//then, the next method, pulls out the next
			}
			@Override
			public void remove() {//resources indicate that we do not need to add this method unless specified.
			}
		};
		return it;//return the iterator
	}
	
	//this method will clear out the entire arraylist emptying it of any elements, but leaving the length the same
	//it uses the remove method to loop through and remove at every single index in the array
	public void clear() {
		for (int i = numElem-1; i >= 0; i--) {//for the size of the array
			this.remove(i);//remove at i
		}
	}
	
	//this method was added for the Pizza class
	//it swaps items in the array at particular indexes
	//using the implementation of the recursive quick sort method
	public void swap(int i, int min) {//takes in two integers to swap at particular indexes in the variables
		Type temp = (Type) list[min];//holder value
		list[min] = list[i];//make the swap
		list[i] = temp;//and reassign
	}
	
	//pretty prints!
	//this method prints out the stuff at the index
	//override the super object method
	//and adds some formatting features
	@Override
	public String toString() {//standard to string, plus also tells the index
		String retVal = "[";//start the array end
		for (int i = 0; i < this.list.length; i++) {//for the length of the array
			if (this.list[i] != null) {//as long as it's not a null spot
				retVal += this.list[i] + "(index = " + i + ")" + "," + "]\n";//add to the String value the stuff, and the index
			}
		}
		return retVal + "";//end the array
	}
}
