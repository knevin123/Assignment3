
import java.util.ArrayList;

import processing.core.*;

public class Test extends PApplet {
	
	Astro astro;
	ArrayList<Stars> star =new ArrayList<Stars>();
	ArrayList<Walls> topwalls = new ArrayList<Walls>();
	ArrayList<Bottomwall> botwalls = new ArrayList<Bottomwall>();
	
	public void setup() 
	{
		size(800,800);
		background(0);
		for(int i=0; i<50;i++)
		{
			Stars stars = null;
		    stars = new Stars(this);
		    star.add(stars);
		}
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
	    if (frameCount % 100 == 0)
	    {
	    	//initialise top wall
		    Walls topwall = null;
		    topwall = new Walls(this);
		    topwalls.add(topwall);
		    //initialise bottom wall
		    Bottomwall bottomwall = null;
		    bottomwall = new Bottomwall(this);
		    botwalls.add(bottomwall);
	    }
	    
	    for(int i= topwalls.size()-1; i>=0;i--)
	    {
	    	Walls go = topwalls.get(i);  
	        go.update();
	        go.render();
	    }
	    for(int i= star.size()-1; i>=0;i--)
	    {
	    	Stars go = star.get(i);  
	        go.update();
	        go.render();
	    }
	    for(int i= botwalls.size()-1; i>=0;i--)
	    {
	    	Bottomwall go = botwalls.get(i);  
	        go.update();
	        go.render();
	    }
	    
		
	}
}