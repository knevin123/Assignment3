
import java.util.ArrayList;

import processing.core.*;

public class Test extends PApplet {
	
	Astro astro;
	speedPowerup speed;
	ArrayList<Stars> star =new ArrayList<Stars>();
	ArrayList<Walls> topwalls = new ArrayList<Walls>();
	ArrayList<Bottomwall> botwalls = new ArrayList<Bottomwall>();
	ArrayList<speedPowerup> speeds = new ArrayList<speedPowerup>();
	public void setup() 
	{
		size(800,800);
		background(0);
		for(int i=0; i<200;i++)
		{
			Stars stars = null;
		    stars = new Stars(this);
		    star.add(stars);
		}
		astro = new Astro(width/2, height/2, 100, this);
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
	    if (frameCount % 600 == 0)
	    {
	    	//initialise speedPowerup
	    	speedPowerup speed = null;
		    speed = new speedPowerup(this);
		    speeds.add(speed);
		    
	    }
		if(frameCount % 25 == 0)
		{
			astro.fuel-=2;
		}
	    
	    for(int i= star.size()-1; i>=0;i--)
	    {
	    	Stars go = star.get(i);  
	        go.update();
	        go.render();
	    }
	    for(int i= speeds.size()-1; i>=0;i--)
	    {
	    	speedPowerup go = speeds.get(i);  
	        go.update();
	        go.render();
	    }
	    astro.render();
	    astro.update();
	    astro.fuel();
	    
	    for(int i= topwalls.size()-1; i>=0;i--)
	    {
	    	Walls go = topwalls.get(i);  
	        go.update();
	        go.render();
	    }
	    
	    for(int i= botwalls.size()-1; i>=0;i--)
	    {
	    	Bottomwall go = botwalls.get(i);  
	        go.update();
	        go.render();
	    }
	    topwalldetect();
	    bottomwalldetect();
	}
	public void topwalldetect()
	{
		for(int i= topwalls.size()-1; i>=0;i--)
	    {
	    	Walls go = topwalls.get(i);  
	        if(astro.pos.y<go.h)
	        {
	        	if((astro.pos.x+astro.w/2-10)>go.wallpos.x  && (astro.pos.x+astro.w/2-10)<(go.wallpos.x+go.w) || (astro.pos.x-astro.w/2+5)>go.wallpos.x  && (astro.pos.x-astro.w/2+5)<(go.wallpos.x+go.w))
	        	{
	        		ellipse(width/2,height/2,50,50);
	        	}
	        }
	    }
	}
	public void bottomwalldetect()
	{
		for(int i= botwalls.size()-1; i>=0;i--)
	    {
			Bottomwall go = botwalls.get(i); 
	        if(astro.pos.y+astro.w>height-go.h)
	        {
	        	if(astro.pos.x-astro.w/2>go.wallpos.x && astro.pos.x-astro.w/2<(go.wallpos.x)+go.w || astro.pos.x+astro.w/2-15>go.wallpos.x && astro.pos.x+astro.w/2-15<(go.wallpos.x)+go.w)
	        	{
	        		rect(width/2,height/2,50,50);
	        	}
	        }
	    }
	}
}