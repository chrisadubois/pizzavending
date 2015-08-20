package finalproj;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
 * @Author: Christopher DuBois
 * CSS 162
 * Professor Rob Nash
 * Final Project
 * this class is the class circle which extends from shape
 *it has a radius and a color as well as an 
 *area defined as data definitions
 *it also overrides the shapes methods because
 * a shape is not defined as well as a 
 * circle and you cannot get the general area of a shape
 * but rather need it defined
 *this class is similar to that in lab and much is copied over 
 *some additions have been made though inorder for the 
 *area to work properly on the pizza class
 *this includes adding the area instance variable which is set randomly
 *by the radius random generator
 */

public class Circle extends Shape {//inheritance
	private double radius = ((Math.random() * 2) + 4);//from 0 - 2 as the radius
	private Color color24;//color data definition
	private double area = Math.PI * (radius * radius);//math
	
	//call the super constructor for the
	//x and y components of the circle
	//and set the radius of the circle
	//seperately
	public Circle(int a, int b, double r) {
		super(a, b);
		setRadius(r);
	}
	// no radius value
	//and the x and y component is 0
	//for the no arg constructor
	public Circle() {
		super(0, 0);
	}
	
	//set the radius of the circle
	//as long as the radius is valid
	//according to the value passed in as a double
	public void setRadius (double r) {
		if (r < 0) throw new PizzaException("cannot have a radius for circle less than 0");
		this.radius = r;//otherwise set the value for radius
	}
	
	//copy constructor for the circle clas
	//it takes in a circle
	//and decomposes the pieces of the circle 
	//caling super for the x and y and 
	//calling the setters for the radius
	//and finally, setting the pizza color with the decomposed
	//rgb color values
	public Circle (Circle that) {
		super(that.getX(), that.getY());
		setRadius(that.getRadius());
		int r = that.getColor().getRed();
		int g = that.getColor().getGreen();
		int b = that.getColor().getBlue();
		setPizzaColor(new Color(r,g,b));
	}
	//return the color value
	//from the color class
	public Color getColor() {
		return color24;
	}
	
	//ovverridden draw method
	//this method will print out what kind of looks
	//like a circle
	@Override
	public void draw() {
		System.out.println("()");
	}
	//implement the cloneable interface
	//utilize the copy constructor to 
	//return a new circle from the circle calling object
	//deep copy
	@Override
	public Circle clone() {
		return new Circle(this);
	}
	
	//setting the pizza color by pulling out the
	//rgb components 
	//of the color being passed in
	public void setPizzaColor(Color c) {
		if (c != null) {
			//int a = c.getAlpha();
			int r = c.getRed();
			int g = c.getGreen();
			int b = c.getBlue();
			color24 = new Color(r, g, b);
		} else {
			throw new PizzaException("cannot pass in a null or invalid color");
		}
	}
	
	//set the area according to the double 
	//value being passed in
	//this is utilized in the pizza class 
	//when the area of the pizza
	//is updated after pizza has been eaten
	public void setArea(double in) {
		area = in;
	}
	
	//return the double value
	//of the area from the data definition
	@Override
	public double getArea() {
		return area;
	}
	
	//this method is from lab and actually draws the circle
	public void draw (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (color24 == null) throw new PizzaException(" must color in the pizza first ");
		g2d.setColor(color24);
		int radDraw = (int) radius;
		g2d.drawOval(getX(), getY(), radDraw, radDraw);
	}
	
	//return the double value of the radius
	//defined in the data definitions of the circle 
	//class
	public double getRadius() {
		return this.radius;
	}
	
	//overrides the super 
	//objects equals method
	//it checks all the components of the circle to the calling circle
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Circle)) throw new PizzaException("give me a circle to equals to");//null check circle check
		Circle that = (Circle) o;//cast
		return this.getX() == that.getX() &&  this.getY() == that.getY() && this.getRadius() == that.getRadius();//check the components
	}
	//ovverride the supers to String
	//this has been edited for the pizza class print out
	//so it just prints out circle as the type of shape that it is
	@Override
	public String toString() {
		String retVal = "";
		retVal = "Circle"; //"Circle of area: " + area;//"Circle of radius " + this.getRadius() + " with color " + color24 + " and position (" + this.getX() + "," + this.getY() + ")" ;
		return retVal;
	}

}
