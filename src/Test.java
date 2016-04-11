
import java.util.ArrayList;

import processing.core.*;

public class Test extends PApplet {
	
	Astro astro;
	Startmenu start;
	Endmenu end;
	ArrayList<Stars> star =new ArrayList<Stars>();
	ArrayList<Walls> topwalls = new ArrayList<Walls>();
	ArrayList<Bottomwall> botwalls = new ArrayList<Bottomwall>();
	ArrayList<SpeedPowerup> speeds = new ArrayList<SpeedPowerup>();
	ArrayList<Light> lights = new ArrayList<Light>();
	ArrayList<Fuelpowerup> fuels = new ArrayList<Fuelpowerup>();
	float k;
	//int for game state 1=startmenu 2=game 3=restart menu
	int state;
	int count;
	int count1;
	int wallspawn;
	float wallspeed;
	boolean change;
	boolean change1;
	public void setup() 
	{
		size(800,800);
		background(0);
		state=1;
		change=false;
		change1=false;
		count=0;
		count1=0;
		wallspawn=100;
		wallspeed=(float) 1.5;
		for(int i=0; i<200;i++)
		{
			Stars stars = null;
		    stars = new Stars(this);
		    star.add(stars);
		}
		astro = new Astro(width/2, height/2, 100, this);
		start = new Startmenu(this);
		end = new Endmenu(this);
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
	    for(int i= star.size()-1; i>=0;i--)
	    {
	    	Stars go = star.get(i);  
	        go.update();
	        go.render();
	    }
	    if(state==1)
	    {
	    	startmenu();
	    	if(keyPressed)
	    	{
	    		state=2;
	    	}
	    }
	    if(state==2)
	    {
	    	game();
	    	if(astro.fuel<0)
	    	{
	    		state=3;
	    	}
	    }
	    if(state==3)
	    {
	    	endmenu();
	    }
	    
	    
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
	        		astro.fuel=0;
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
	        		astro.fuel=0;        	
	        	}
	        }
	    }
	}
	public void fueldetect()
	{
		for(int i= fuels.size()-1; i>=0;i--)
	    {
	    	Fuelpowerup go = fuels.get(i);  
	    	if (go.pos.dist(astro.pos) < astro.halfW + go.w/2)
	        {
	            // Do some casting
	    		astro.fuel+=50;
	    		fuels.remove(go);
	        }
	    }
		if (change1==true)
		{
			count1++;
			if(count1<600)
			{
				background(255);
				fill(0);
				ellipse(astro.pos.x,astro.pos.y+astro.halfW,250,250);
			}
			if(count1>300)
			{
				change1=false;
				count1=0;
			}
		}
	}
	public void lightdetect()
	{
		for(int i= lights.size()-1; i>=0;i--)
	    {
	    	Light go = lights.get(i);  
	    	if (go.pos.dist(astro.pos) < astro.halfW + go.w/2)
	        {
	            // Do some casting
	    		lights.remove(go);
	    		change1=true;
	        }
	    }
	}
	
	public void speeddetect()
	{
		for(int i= speeds.size()-1; i>=0;i--)
	    {
	    	SpeedPowerup go = speeds.get(i);  
	        if(astro.pos.y<go.pos.y+(go.h/2) && astro.pos.y>go.pos.y-(go.h/2))
	        {
	        	if((astro.pos.x+astro.w/2-10)>go.pos.x  && (astro.pos.x+astro.w/2-10)<(go.pos.x+go.w) || (astro.pos.x-astro.w/2+5)>go.pos.x  && (astro.pos.x-astro.w/2+5)<(go.pos.x+go.w))
	        	{
	        		//change wall speeds
	        		change=true;
	        		speeds.remove(go);
	        		wallspeed=3;
	        		wallspawn=50;
	        		for(int j= topwalls.size()-1; j>=0;j--)
	        	    {
	        	    	Walls go1 = topwalls.get(j);  
	        	        go1.speed=3;
	        	    }
	        		for(int j= botwalls.size()-1; j>=0;j--)
	        	    {
	        			Bottomwall go2 = botwalls.get(j); 
	        	        go2.speed=3;
	        	    }
	        		
	        	}
	        }
	    }
		if (change==true)
		{
			count++;
			if(count>300)
			{
				for(int j= topwalls.size()-1; j>=0;j--)
        	    {
        	    	Walls go1 = topwalls.get(j);  
        	        go1.speed=(float) 1.5;
        	    }
        		for(int j= botwalls.size()-1; j>=0;j--)
        	    {
        			Bottomwall go2 = botwalls.get(j); 
        	        go2.speed=(float) 1.5;
        	    }
        		wallspeed=(float) 1.5;
        		wallspawn=100;
        		change=false;
        		count=0;
			}
		}
	}
	public void startmenu()
	{
		start.render();
	    start.update();
	}
	public void endmenu()
	{
		end.render();
		end.update();
	}
	public void game()
	{
		//topwalldetect();
	    //bottomwalldetect();
	    speeddetect();
	    fueldetect();
	    lightdetect();
	    if (frameCount % wallspawn == 0)
	    {
	    	//initialise top wall
		    Walls topwall = null;
		    topwall = new Walls(this,wallspeed);
		    topwalls.add(topwall);
		    //initialise bottom wall
		    Bottomwall bottomwall = null;
		    bottomwall = new Bottomwall(this,wallspeed);
		    botwalls.add(bottomwall);
	    }
	    if (frameCount % (wallspawn*4) == 0)
	    {
	    	for(int i= topwalls.size()-1; i>=0;i--)
		    {
		    	Walls go = topwalls.get(i);  
		        for(int j= botwalls.size()-1; j>=0;j--)
		        {
		        	Bottomwall go1 = botwalls.get(j);
		        	k=random(go.h,(height-go1.h));
		        }
		    }
	    	Fuelpowerup fuel = null;
	    	fuel = new Fuelpowerup(this,k,wallspeed);
        	fuels.add(fuel);
	    }
	    if (frameCount % 600 == 0)
	    {
	    	//initialise speedPowerup
	    	int x=(int)random(0,1);
	    	if(x==1)
	    	{
	    		SpeedPowerup speed = null;
	    		speed = new SpeedPowerup(this);
	    		speeds.add(speed);
	    	}
	    	if(x==0)
	    	{
	    		Light lite = null;
	    		lite = new Light(this);
	    		lights.add(lite);
	    	}
		    
		    
	    }
		if(frameCount % 25 == 0)
		{
			astro.fuel-=3;
		}
	    for(int i= speeds.size()-1; i>=0;i--)
	    {
	    	SpeedPowerup go = speeds.get(i);  
	        go.update();
	        go.render();
	    }
	    for(int i= lights.size()-1; i>=0;i--)
	    {
	    	Light go = lights.get(i);  
	        go.update();
	        go.render();
	    }
	    for(int i= fuels.size()-1; i>=0;i--)
	    {
	    	Fuelpowerup go = fuels.get(i);  
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
	    
	}
	
	
}