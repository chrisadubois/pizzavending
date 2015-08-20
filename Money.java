package finalproj;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;

/*@Author: Christopher DuBois
 * This is the money class
 *which holds two integers values to indicate the dollars and cents of a money
 *it implemnents the serializable interface which means this object can be written to files
 *it is also comparable so money objects can be compared properly
 *it is also clonebale, that is, you can make a money object from another one and have them be equivalent
 *you can never have negative dollars or cents
 */

public class Money implements java.io.Serializable, Comparable, Cloneable { //class definition, implementing serializable, comparable and cloneable
	//obvious instance variables
	private int cents;
	private int dollars;
	
	//this is this normal constructor which takes in dollars and cents and sets them calling the invariants
	public Money(int d, int c) {//constructor using sets
		setDollars(d);//invariant
		setCents(c);//invariant
	}
	
	//if you want to create a money object but only do so with a dollar value
	public Money(int d) {
		setDollars(d);//invariant
	} //this was called in the driver but not noted in the specs
	
	//this is the copy constructor
	//it takes an object
	//checks for nullity and instanceof-ness
	//and then copies over the instance variables into a new money object
	public Money(Object o) {//Money object constructor
		if (o == null || !(o instanceof Money)) throw new IllegalArgumentException("cannot pass in a null or a non money object to clone");//null check, Money check
		Money that = (Money) o;//cast
		setDollars(that.getDollars());//no priv leaks here because of primitives
		setCents(that.getCents());//same as right above	
	}
	
	//this method is to implement the cloneable interface
	//we use the copy constructor and return a new money using the Money calling object as a parameter
	public Money clone() {//so we can call Money b = a.clone()
		return new Money(this);//the calling object Money parameter
	}
	
	//the no arg constructor
	//make money cents equal to 0
	//make dollar equal to 0
	public Money() {//no arg constructor
		setCents(0);//invariant
		setDollars(0);//invariant
	}
	
	//this method will set the cents following the invariant for cents cannot be less than 0
	//moreover, if you pass in 207 cents
	//it will add to dollars 2 dollars and make cents equal to 7 cents
	public void setCents(int c) {
		if (c < 0) {//invariant
			System.out.println("cents can't be less than 0");//class invariant
			throw new IllegalArgumentException("cents can't be less than 0");//throw the break
		}
		while (c > 99) {//allow user to type in cents as 101, 102...Infin
			this.dollars++;//add to the dollars while it's doing this
			c = c -100;//then make cents 100 less then it was before.
		}
		this.cents = c;//set the cents after the loop is totally done
	}
	
	//this method will set the dollars 
	//following the invariant that dollars 
	//cannot be less than 0
	//nothing else to be done
	public void setDollars(int d) {
		if (d < 0) {
			throw new IllegalArgumentException("dollars can't be less than 0");//throw the invariant break
		}
		this.dollars = d;//otherwise set the value
	}
	
	//the super set
	//it follows the invariants by setting money using the seperate set cents and set dollars methods which hold the invariant checks
	public void setMoney(int d, int c) {//call setters with checks
		setCents(c);//invariant
		setDollars(d);//invariant
	}
	
	//return the integer value of cents from 0-99
	public int getCents() {//simple get
		return this.cents;//no priv leak
	}
	
	//return the integer value of dollars
	public int getDollars() {//simple get
		return this.dollars;//no priv leak
	}
	
	//to get all of the money as one 
	//number as a double this method 
	//takes the money in, adds cents and dollars
	//together as full integer values
	//divides by 100 to get the cents back behind 
	//the decimal
	public double getMoney() {
		return this.getDollars() + ((double) this.getCents())/100;//return as double value by casting cents to double, then dividing and adding to dollars
	}
	
	//if we are just adding dollars
	//then simply add the dollars ontop of eachother
	public void add(int d) {
		int a = this.getDollars() + d;//holder value
		this.setDollars(a);//reset
	}
	
	//to subtract you can either call 
	//add(-dol, -cents) or to catch everything
	//it minuses a certain money amount from another money amount
	 public void subtract(int dollars, int cents) throws IllegalArgumentException {
	        if(dollars >= 0 && cents >= 0 && cents <= 99 && this.dollars >= dollars) {//to make sure the money won't go below 0
	            this.cents -= cents;//minus stuff
	            this.dollars -= dollars;//minus stuff
	            if (this.cents < 0 && this.dollars > 0) {//if cents went below 0 but dollars is more than 1
	                this.dollars--;//minus the dollars
	                this.cents += 100;//add 100 to cents to make up for the dollar minused but would go from -7 to 93
	            } else {
	                throw new IllegalArgumentException ("Attempted subtraction would result in a negative value for Money");//throw the break if it would violate invarianbt
	            }
	        } else {
	            throw new IllegalArgumentException ("Attempt to subtract invalid number of dollars and/or cents");//invalid argument
	        }
	    }
	
	//to add seperate values of integer moneys into the money object
	 //uses the same method as the setCents for if the cents go above 100
	 //afterward it resets the dollar and cents values
	public void add(int d, int c) {
		int cents = this.getCents() + c;//holder
		while (cents > 99) {//allow user to type in cents as 101, 102...Infin
			this.dollars++;
			cents = cents -100;
		}
		int centsAfterAdd = cents;
		int dol = this.getDollars() + d;//holder
		this.setMoney(dol, centsAfterAdd);//reset
	}
	
	//in order to add a money Object to
	// the money object being operated on
	//it does so by using the gets as holder values and then
	//caling the master set which calls the
	//seperate sets
	//holding the class invariants
	public void add(Money o) {//money object add
		if (o instanceof Money && o != null) {//null check
			Money that = (Money) o;
		int cents = this.getCents() + that.getCents();//hold after gett
		int dol = this.getDollars() + that.getDollars();//hold
		this.setMoney(dol, cents);//reset
		}
	}
	
	//the equals method, to see if two money objects
	//are the same
	//it does so by checking the dollars and the cents
	//if both are equal, then return true
	//otherwise return false
	public boolean equals(Object o) {
		if (o instanceof Money && o != null) {//object money check and null check
			Money that = (Money) o;//cast 
			if (this.getDollars() == that.getDollars() && this.getCents() 
					== that.getCents()) {//checks of all instance vars
				return true;
			}
		}
		return false;
	}
	
	//this is to implement the comparable method
	//first it checks the dollars (because that
	//is the greatest value stored in a money
	//then if those are equal
	//it checks the cents
	//returns 1 if this money is > that money
	//returns -1 if this money is < that money
	//returns 0 otherwise
	@Override
	public int compareTo(Object o) {
		if (o == null || ! (o instanceof Money)) throw new IllegalArgumentException("Cannot compare null objects or something that isnt money");//null check money check
		else {
			Money that = (Money) o;//cast
			if (this.getDollars() > that.getDollars()) return 1;//this > that dollars
			if (this.getDollars() < that.getDollars()) return -1; //this < thaty dollars
			if (this.getDollars() == that.getDollars()) {//this equal that dollars
				if (this.getCents() == that.getCents()) return 0;//then we can check the cents
				else if (this.getCents() > that.getCents()) return 1;//this cents > that cents
				else return -1;//if this.getCents() < that.getCents() is essentially what tis says
			} else throw new RuntimeException("comparable didnt work for Money");//this should never happen
			}
		}
	//in order to implement the serializable interface
	//we must be able to write the money object to a file
	//and deserialize it
	public void writeObject() {
		Money e = this.clone();//create a new money without priv leaks
		try
		{
			FileOutputStream fileOut = new FileOutputStream("MoneySez.ser");//make a new file 
			ObjectOutputStream out = new ObjectOutputStream(fileOut);//object out the stuff from that file
			out.writeObject(e);//write the money object
			out.close();//close the file
			fileOut.close();//close the file
			System.out.println("Serialized data is saved in MoneySez.ser ");
		}catch(IOException outputExcept)
		{
			outputExcept.printStackTrace();//print exactly what happened and what went wrong
		}
	}
	public void readObject() {
		Money e = null;//make a money holder object
		try
		{
			FileInputStream fileIn = new FileInputStream("MoneySez.ser");//make a file input stream
			ObjectInputStream in = new ObjectInputStream(fileIn);//read them in as objects
			e = (Money) in.readObject();//cast and set the holder value equal to the deserialized Money object
			in.close();//close the file
			fileIn.close();//close the file
		}catch(IOException inputExcept)
		{
			inputExcept.printStackTrace();//print out what went wrong
			return;
		}catch(ClassNotFoundException cnfe)
		{
			System.out.println("Money obj unable to locate");
			cnfe.printStackTrace();//learned this in reading about serialization which shows where the exception broke exactly
			return;
		}
		System.out.println("Money object in MoneySez.ser is " + e);
	}
	//pretty prints method
	//it will print out the money to look like $dol.cent
	//if cents are 70 and dollars are 5 it prints out $5.70
	//but if cents are 7 and dollars are 5 it prints out
	//$5.07 rather than 5.7
	public String toString() {//pretty prints
		String retVal = "";
		if (this.getCents() >= 10) {//if the cents are more than 10, we print them out normally
			retVal = "$" + this.getDollars() + "." + this.getCents();
		} else {//but if cents are less than 10 we need to put that 0 infront of it to make it look proper
			retVal = "$" + this.getDollars() + ".0" + this.getCents();
		}
		return retVal;
}
	
	
}
