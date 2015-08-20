package finalproj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
 * @Author: Christopher DuBois
 * CSS 162
 * Professor Rob Nash
 * Final Project
 * this class is the class square which extends from shape
 *it has a sidelength and a color as well as an 
 *area defined as data definitions
 *it also overrides the shapes methods because
 * a shape is not defined as well as a 
 * square and you cannot get the general area of a shape
 * but rather need it defined
 *this class is similar to that in lab and much is copied over 
 *some additions have been made though inorder for the 
 *area to work properly on the pizza class
 *this includes adding the area instance variable which is set randomly
 *by the sidelength random generator
 */
public class Square extends Shape {//inheritance
	private double sideLength = ((Math.random() * 4)); //from 0 - 4 as a double
	private Color color24;//the color class
	private double area = sideLength * sideLength;//math
	
	//the no argument constuctor
	//it takes in three values
	//two to set the x and y position as defined in super
	//and the other to take the sidelength for the square
	public Square(int x, int y, double sL) {
			super(x, y);
			setSideLength(sL);
	}
	//the no arg constructor
	//has a sidelength of 0
	//and an x,y position has 0,0
	public Square() {
		super(0, 0);
	}
	//the copy constructor for square
	//it takes a square object
	//calls super on the decomposed x and y
	//and sets the sidelength from the decomposed 
	//squares side length
	//then also decomposes the colors and sets 
	//them for this specific shape
	public Square (Square that) {
		super(that.getX(), that.getY());
		setSideLength(that.getSideLength());
		int r = that.getColor().getRed();
		int g = that.getColor().getGreen();
		int b = that.getColor().getBlue();
		setPizzaColor(new Color(r,g,b));
	}
	
	//checks and sets the sidelength of the square
	//acording to the input of what has \
	//been passed in to the method
	public void setSideLength(double sL) {
		if (sL <= 0) throw new PizzaException("Cannot have a square with side length less than or equal to 0");
		this.sideLength = sL;
	}
	//override to imp[lement the cloneable interface
	//calls the copuy constructor and returns a new square object from that
	@Override
	public Square clone() {
		return new Square(this);
	}
	
	//return the double value
	//of the area instance variable defined above
	public double getArea() {
		return area;
	}
	//return the integer value
	//of the calling object's side length
	public double getSideLength() {
		return this.sideLength;
	}
	
	//overrides supers draw method 
	//and prints our sqaure brackets
	@Override
	public void draw(){
		System.out.println("[]");
	}
	
	//return the color value of the square
	//because color has no copy constructor or clone method 
	//just return the leash to the color
	public Color getColor() {
		return color24;
	}
	
	//set the value of the area to the value
	//to what is passed in
	//this is implemented when part of the pizza is eaten
	public void setArea(double in) {
		area = in;
	}
	
	//override the objects equals method
	//check all the components of the square
	//sidelength, x and y and the color component
	//for equivalency and return true if they are all the same
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Square)) throw new PizzaException("give me a square");//null check, square check
		Square that = (Square) o;//cast
		return this.getX() == that.getX() && this.getY() == that.getY() && this.getSideLength() == that.getSideLength();//check all the components
	}
	
	//set the pizza color by getting all the rgb values of
	//the color class and the color data definition
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
	@Override
	public String toString() {
		return "Square";// with sideLength: " + sideLength;///"Square with side Length " + this.getSideLength() + " and color " + color24 + " and position (" + this.getX() + "," + this.getY() + ")";
	}
	//the other overridden draw method
	//this was only implemented in the lab 
	public void draw( Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor( Color.BLUE );
		 g2d.draw3DRect(getX(), getY(), (int)sideLength * 2, (int) sideLength * 2, false);
		}

}
