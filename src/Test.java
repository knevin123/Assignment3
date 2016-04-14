
import java.util.ArrayList;

import processing.core.*;

public class Test extends PApplet {
	
	Startmenu start;
	Endmenu end;
	ArrayList<Stars> star =new ArrayList<Stars>();
	ArrayList<Walls> topwalls = new ArrayList<Walls>();
	ArrayList<Bottomwall> botwalls = new ArrayList<Bottomwall>();
	ArrayList<SpeedPowerup> speeds = new ArrayList<SpeedPowerup>();
	ArrayList<Light> lights = new ArrayList<Light>();
	ArrayList<Fusion> fuses =new ArrayList<Fusion>();
	ArrayList<Fuelpowerup> fuels = new ArrayList<Fuelpowerup>();
	ArrayList<Astro> ast = new ArrayList<Astro>();
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
	    		Astro astr = null;
			    astr = new Astro(width/2, height/2, 100, this);
			    ast.add(astr);
	    		state=2;
	    	}
	    }
	    if(state==2)
	    {
	    	game();
	    	for(int i= ast.size()-1; i>=0;i--)
		    {
		    	Astro go = ast.get(i);  
		    	if(go.fuel<0)
		    	{
		    		fuels.clear();
		    		lights.clear();
		    		speeds.clear();
		    		botwalls.clear();
		    		topwalls.clear();
		    		ast.clear();
		    		state=3;	
		    	}
		    }
	    }
	    if(state==3)
	    {
	    	endmenu();
	    	restart();
	    }
	    
	    
	}
	public void restart()
	{
		if(state==3)
		{
			if(mousePressed)
			{
				if(mouseX>end.pos.x && mouseX<end.pos.x+end.w)
				{
					if(mouseY>end.pos.y && mouseY<end.pos.y+end.h)
					{
						state=1;
						
					}
				
				}
			}
		}
	}
	public void topwalldetect()
	{
		for(int i= topwalls.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
				Walls go = topwalls.get(i); 
				if(go.wallpos.x+go.w<0)
				{
					topwalls.remove(go);
				}
				if(other.pos.y<go.h)
				{
					if((other.pos.x+other.w/2-10)>go.wallpos.x  && (other.pos.x+other.w/2-10)<(go.wallpos.x+go.w) || (other.pos.x-other.w/2+5)>go.wallpos.x  && (other.pos.x-other.w/2+5)<(go.wallpos.x+go.w))
					{
						other.fuel=0;
					}
				}
			}
	    }
	}
	public void bottomwalldetect()
	{
		for(int i= botwalls.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
				Bottomwall go = botwalls.get(i); 
		        if(other.pos.y+other.w>height-go.h)
		        {
		        	if(go.wallpos.x<0)
			    	{
			    		botwalls.remove(go);
			    	}
		        	if(other.pos.x-other.w/2>go.wallpos.x && other.pos.x-other.w/2<(go.wallpos.x)+go.w || other.pos.x+other.w/2-15>go.wallpos.x && other.pos.x+other.w/2-15<(go.wallpos.x)+go.w)
		        	{
		        		other.fuel=0;        	
		        	}
		        }
			}
	    }
	}
	public void fueldetect()
	{
		for(int i= fuels.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
		    	Fuelpowerup go = fuels.get(i);  
		    	if(go.pos.x<0)
		    	{
		    		fuels.remove(go);
		    	}
		    	if (go.pos.dist(other.pos) < other.halfW + go.h/2)
		        {
		            // Do some casting
		    		other.fuel+=50;
		    		fuels.remove(go);
		        }
			}
	    }
		
	}
	public void fusiondetect()
	{
		for(int i= fuses.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
		    	Fusion go = fuses.get(i);
		    	if(go.pos.x<0)
		    	{
		    		fuses.remove(go);
		    	}
		    	if (go.pos.dist(other.pos) < other.halfW + go.w/2)
		        {
		            // Do some casting
		    		fuses.remove(go);
		    		//change1=true;
		    		change=true;
		    		change1=true;
		    		wallspeed=3;
	        		wallspawn=50;
	        		for(int x= topwalls.size()-1; x>=0;x--)
	        	    {
	        	    	Walls go1 = topwalls.get(x);  
	        	        go1.speed=3;
	        	    }
	        		for(int x= botwalls.size()-1; x>=0;x--)
	        	    {
	        			Bottomwall go2 = botwalls.get(x); 
	        	        go2.speed=3;
	        	    }
	        		for(int x= fuels.size()-1; x>=0;x--)
	        		{
	        			Fuelpowerup go3 = fuels.get(x);
	        			go3.speed=3;
	        		}
		        }
			}
	    }
	}
	public void lightdetect()
	{
		for(int i= lights.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
		    	Light go = lights.get(i);
		    	if(go.pos.x<0)
		    	{
		    		lights.remove(go);
		    	}
		    	if (go.pos.dist(other.pos) < other.halfW + go.w/2)
		        {
		            // Do some casting
		    		lights.remove(go);
		    		change1=true;
		        }
			}
	    }
		if (change1==true)
		{
			count1++;
			if(count1<600)
			{
				background(255);
				fill(0);
				for(int j=ast.size()-1;j>=0;j--)
				{
					Astro other = ast.get(j);
					ellipse(other.pos.x,other.pos.y+other.halfW,250,250);
				}
			}
			if(count1>300)
			{
				change1=false;
				count1=0;
			}
		}
	}
	
	public void speeddetect()
	{
		for(int i= speeds.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
		    	SpeedPowerup go = speeds.get(i);
		    	if(go.pos.x<0)
		    	{
		    		speeds.remove(go);
		    	}
		        if(other.pos.y<go.pos.y+(go.h/2) && other.pos.y>go.pos.y-(go.h/2))
		        {
		        	if((other.pos.x+other.w/2-10)>go.pos.x  && (other.pos.x+other.w/2-10)<(go.pos.x+go.w) || (other.pos.x-other.w/2+5)>go.pos.x  && (other.pos.x-other.w/2+5)<(go.pos.x+go.w))
		        	{
		        		//change wall speeds
		        		change=true;
		        		speeds.remove(go);
		        		wallspeed=3;
		        		wallspawn=50;
		        		for(int x= topwalls.size()-1; x>=0;x--)
		        	    {
		        	    	Walls go1 = topwalls.get(x);  
		        	        go1.speed=3;
		        	    }
		        		for(int x= botwalls.size()-1; x>=0;x--)
		        	    {
		        			Bottomwall go2 = botwalls.get(x); 
		        	        go2.speed=3;
		        	    }
		        		for(int x= fuels.size()-1; x>=0;x--)
		        		{
		        			Fuelpowerup go3 = fuels.get(x);
		        			go3.speed=3;
		        		}
		        		
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
        		for(int j= fuels.size()-1; j>=0;j--)
        		{
        			Fuelpowerup go3 = fuels.get(j);
        			go3.speed=(float) 1.5;
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
		topwalldetect();
	    bottomwalldetect();
		fusiondetect();
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
	    	Walls go = topwalls.get(topwalls.size()-1);  
		    Bottomwall go1 = botwalls.get(botwalls.size()-1);
		    k=random(go.h+30,(height-go1.h)-30);
	    	Fuelpowerup fuel = null;
	    	fuel = new Fuelpowerup(this,k,wallspeed);
        	fuels.add(fuel);
	    }
	    if (frameCount % 600 == 0)
	    {
	    	//initialise speedPowerup
	    	int x=(int)random(-1,3);
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
	    	if(x==2)
	    	{
	    		Fusion fuse=null;
	    		fuse =new Fusion(this);
	    		fuses.add(fuse);
	    	}
		    
		    
	    }
		if(frameCount % 25 == 0)
		{
			for(int i= ast.size()-1; i>=0;i--)
		    {
		    	Astro go = ast.get(i);  
		        go.fuel-=3;
		    }
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
	    for(int i= fuses.size()-1; i>=0;i--)
	    {
	    	Fusion go = fuses.get(i);  
	        go.update();
	        go.render();
	    }
	    for(int i= fuels.size()-1; i>=0;i--)
	    {
	    	Fuelpowerup go = fuels.get(i);  
	        go.update();
	        go.render();
	    }
	    for(int i= ast.size()-1; i>=0;i--)
	    {
	    	Astro go = ast.get(i);  
	        go.update();
	        go.render();
	        go.fuel();
	    }
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