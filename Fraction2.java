package finalproj;

/*@Author: Christopher DuBois
 * this class was previously defined
 * an altered for the pizza class
 * to implement the comparable interface
 * moreover, the fraction is created as a final fraction
 * and can be made as a copy constructor, a decomposed num and denom
 *and utilizes the greatest common divisor algorithm
 *from euclid to do so
 *implements the iterative method
 *but gcd could be
 *gcd(n, d){
 *if (d == 0) return n;
 *return gcd(d, n%d);
 *}
 */

public class Fraction2 implements Comparable {
	public final int numerator;// num component
	public final int denominator;// denom component

	public Fraction2() {// everything a Fraction has below
		numerator = 0;// num can be 0
		denominator = 1;// denom cannot be 0
	}
	//create the fraction
	//using the primitive values
	//passed in
	public Fraction2(int num, int denom) {// sets the fraction components
		int gcd = gcdHelper(num, denom);
		if (gcd == 0) {//if there isn;t a greatest common divisor
			numerator = num;//just set them
			denominator = denom;
		} else {//otherwise
			//divide by the greatest common divisor between the two
		numerator = num / gcd;
		valid(denom);
		denominator = denom / gcd;
		}
	}
	//copy constructor calls the bigger decomposed primitive constructor
	public Fraction2(Fraction2 aFraction) {// if passed a fraction, it will
											// determine the components and
											// construct a fraction accordingly
		this(aFraction.getNumerator(), aFraction.getDenominator());
	}
	//deep copy
	//getter
	public Fraction2 getFraction() {
		return new Fraction2(this.getNumerator(), this.getDenominator());
	}
	//this method checks the equivalency
	//of two fractions by comparing their components
	public boolean equals(Object anotherObject) {// check to see if two
													// fractions are equal
		if (anotherObject instanceof Fraction2) {// make sure it's a fraction
												// object and not a gorilla or a
												// startrooper
			Fraction2 that = (Fraction2) anotherObject;// cast
			if (this.numerator == that.getNumerator()
					&& this.denominator == that.getDenominator()) {
				return true; // comparison comes back true, return true
			}
		}
		return false;// else return false
	}
	//simple primitive getter
	public int getNumerator() {// getter
		return this.numerator;
	}
	//simple getter primitive
	public int getDenominator() {// getter
		return this.denominator;
	}
	//greatest common divisor algorithm
	//returns the greatest common divisor of a fraction as an integer value
	private int gcdHelper(int num, int denom) {// greatest common divisor
		// algorithm
		int remainder; // holder value for remainder
		while (denom != 0) { // if the denom is not 0
			do {
				remainder = num % denom; // simple modulo, returns remainder
				num = denom;// numerator = the old denominator
				denom = remainder; // denominator = the old remainder
			} while (remainder > 0);// this continues until remainder is 0 and
			// no more divisions are possible and the
			// greatest common divisor has been found
		}
		return num;
	}
	//a method that checks on the denominator
	public boolean valid(int d) {
		if (d < 0) throw new PizzaException("cannot have a fraction with 0 on the bottom");
		return true;
	}
	//this method adds fractions onto another fraction
	public Fraction2 add(Fraction2 that) {
		int top = numerator;//holder value
		int bot = denominator;//holder value
		top = (that.getDenominator() * top) + (that.getNumerator() * bot);//denominator * the top + the numerator * bottom
		if (valid(that.getDenominator())) {//as long as the denominator is a valid denominator (is not <0)
			bot = bot * that.getDenominator();//then we can define a denominator
		}
		return new Fraction2(top, bot);//and create a new fraction
	}
	//this method was initially defined in my Fraction class
	//but kind of screwed things up
	/*
	 * public void reduceFractions() { int num = this.getNumerator();// use
	 * getter to find the calling objects // numerator int denom =
	 * this.getDenominator();// same as above but denom int newNum;// holder val
	 * int newDenom;// holder val for reduction int gcd = gcdHelper(num,
	 * denom);// uses helper method to find the // greatest common divisor
	 * newNum = num / gcd;// divide num by greatest common divisor that is //
	 * common newDenom = denom / gcd;// same as above for denom
	 * setNumerator(newNum);// reset values setDenominator(newDenom);// reset
	 * values }
	 */

	//this method compares this fraction to that fraction
	//according to the comparable interface
	public int compareTo(Object o) {
		if (o == null || !(o instanceof Fraction2))//null check fraction check
			throw new PizzaException(
					"Cannot compare this to a null object or something that is not a fraction");//throw the exception
		else {
			Fraction2 that = (Fraction2) o;//cast
			double denom = this.getNumerator() * that.getDenominator();//make the denominator
			double num = this.getDenominator() * that.getNumerator();//make the numerator
			// System.out.println(this.getDenominator() * that.getNumerator() +
			// "/" + this.getNumerator() * that.getDenominator()); to check the
			// cross multiplication trace
			if (num < denom)//cross multiplication
				return 1;
			if (num > denom)//cross multiplication 
				return -1;
			if (num == denom)//cross multiplication showed the top to be equal to the bottom so we can
				//say that the fractions being compared are equal
				return 0;
		}
		throw new PizzaException(
				"major programming error, the calling fraction was neither less than greater than or equal to the passed in fraction");
	}

	public String toString() {//pretty prints
		return this.getNumerator() + "/" + this.getDenominator();
	}

}
