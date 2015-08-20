package finalproj;
import java.awt.Graphics;

/* Class Shape
 *
 * By Rob Nash
 * 
 * This is the superclass in a hierarchy of shapes that you have to construct
 */

//the superclass in our inheritance hierarchy
//all "common" features, functions and data should go here
//for example, all shapes in Java2D have a x,y that declares their position
//and many of the shapes exposed have a width and a height (but not all, so we didn't put width and height here)
//note that this class is mostly empty, as there is no algorithm generic enough to guess an arbitrary shape's area (future subclasses must override getArea() to provide something reasonable)
//also, the draw method is empty too, as we don't know what shape to draw here! (again, our subclasses will need to replace this method with one that actually draws things)
public class Shape extends Object implements Cloneable {
	private int x = 0;
	private int y = 0;
	private double area;
	
	@Override
	public Shape clone() {
		return new Shape(this);
	}
	
	public Shape(Shape o){
		x = o.x;
		y = o.y;
	}
	
	public Shape( int a, int b ) {
		x=a;
		y=b;
	}
	
	public void setArea(double input){
		area = input;
	}
	
	public double getArea() {
		return area;
	}
	
	public void draw() {
		System.out.println("?");
	}
	
	//public void draw( Graphics g ){}
	
	public int getX() { return x; }
	public int getY() { return y; }
}