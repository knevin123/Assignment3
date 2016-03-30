
import processing.core.*;

public class Test extends PApplet {
	Astro astro;
	Walls test;
	public void setup() 
	{
		size(800,800);
		test=new Walls(this);
		astro = new Astro(width/2, height/2, 50, this);
	}
	boolean[] keys = new boolean[512];

	public void keyPressed()
	{
	  keys[keyCode] = true;
	}

	public void keyReleased()
	{
	  keys[keyCode] = false;
	}
	public void draw() 
	{
		background(0);
	    astro.render();
	    astro.update();
	    test.render();
	    test.update();
	}
}