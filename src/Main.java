
import java.util.ArrayList;
import processing.core.*;
import ddf.minim.*;

public class Main extends PApplet {
	//needed to run a jar file 
	static public void main(String args[])
	{
		PApplet.main(new String[] { "Main" });
	}
	//making a startmenu and endmenu object
	Startmenu start;
	Endmenu end;
	//audio background sound
	Minim minim;
	//array lists
	ArrayList<Stars> star =new ArrayList<Stars>();
	ArrayList<Walls> topwalls = new ArrayList<Walls>();
	ArrayList<Bottomwall> botwalls = new ArrayList<Bottomwall>();
	ArrayList<SpeedPowerup> speeds = new ArrayList<SpeedPowerup>();
	ArrayList<Light> lights = new ArrayList<Light>();
	ArrayList<Fusion> fuses =new ArrayList<Fusion>();
	ArrayList<Fuelpowerup> fuels = new ArrayList<Fuelpowerup>();
	ArrayList<Astro> ast = new ArrayList<Astro>();
	//fuel y-position
	float k;
	//scores
	int current;
	int high;
	//int for game state 1=startmenu 2=game 3=restart menu
	int state;
	//counter for powerdowns
	int count;
	int count1;
	//wall spawn timer
	int wallspawn;
	//wall speed
	float wallspeed;
	//indicator for start of powerdown
	boolean change;
	boolean change1;
	//setup
	public void setup() 
	{
		minim = new Minim(this);
		AudioPlayer audio;
		audio = minim.loadFile("music.mp3");
		audio.play();
		size(800,800);
		background(0);
		//set to start menu state
		state=1;
		//set powerdowns to off
		change=false;
		change1=false;
		count=0;
		count1=0;
		//set scores
		high=0;
		current=0;
		//set wallspawn and speed
		wallspawn=100;
		wallspeed=(float) 1.5;
		//initilise stars for background
		for(int i=0; i<200;i++)
		{
			Stars stars = null;
		    stars = new Stars(this);
		    star.add(stars);
		}
		//intialise start and end menus ready to be called
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
		//always render stars
	    for(int i= star.size()-1; i>=0;i--)
	    {
	    	Stars go = star.get(i);  
	        go.update();
	        go.render();
	    }
	    //if startmenu state
	    if(state==1)
	    {
	    	//render start menu 
	    	startmenu();
	    	//when pressed change states create character
	    	if(keyPressed)
	    	{
	    		Astro astr = null;
			    astr = new Astro(width/2, height/2, 100, this);
			    ast.add(astr);
	    		state=2;
	    		//reset powerups
	    		change=false;
	    		change1=false;
	    	}
	    }
	    //if game state
	    if(state==2)
	    {
	    	//render game situation
	    	game();
	    	//check astronaut
	    	for(int i= ast.size()-1; i>=0;i--)
		    {
		    	Astro go = ast.get(i);  
		    	//if fuel is less than 0 clear all array list and change state
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
	    //if endmenu state 
	    if(state==3)
	    {
	    	//restart game and score
	    	endmenu();
	    	current=0;
	    	restart();
	    }
	    
	    
	}
	//game restart
	public void restart()
	{
		//detect if you press the sprite to return to main menu
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
	//top wall detect
	public void topwalldetect()
	{
		for(int i= topwalls.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
				Walls go = topwalls.get(i); 
				//if walls off screen its removed
				if(go.wallpos.x+go.w<0)
				{
					topwalls.remove(go);
				}
				//if it hits the astronaut his fuel is 0
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
	//bottom wall detect
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
		        	//if walls off screen its removed
		        	if(go.wallpos.x<0)
			    	{
			    		botwalls.remove(go);
			    	}
		        	//if it hits the astronaut his fuel is 0
		        	if(other.pos.x-other.w/2>go.wallpos.x && other.pos.x-other.w/2<(go.wallpos.x)+go.w || other.pos.x+other.w/2-15>go.wallpos.x && other.pos.x+other.w/2-15<(go.wallpos.x)+go.w)
		        	{
		        		other.fuel=0;        	
		        	}
		        }
			}
	    }
	}
	//fuel detect
	public void fueldetect()
	{
		for(int i= fuels.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
		    	Fuelpowerup go = fuels.get(i); 
		    	//if fuels off screen its removed
		    	if(go.pos.x<0)
		    	{
		    		fuels.remove(go);
		    	}
		    	//circular detect for fuel and astro
		    	if (go.pos.dist(other.pos) < other.halfW + go.h/2)
		        {
		    		//add fuel and remove
		    		other.fuel+=50;
		    		fuels.remove(go);
		        }
			}
	    }
		
	}
	//fusion detect
	public void fusiondetect()
	{
		for(int i= fuses.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
		    	Fusion go = fuses.get(i);
		    	//remove fuse powerdown if off screen
		    	if(go.pos.x<0)
		    	{
		    		fuses.remove(go);
		    	}
		    	//if touches astro remove and start powerdown
		    	if (go.pos.dist(other.pos) < other.halfW + go.w/2)
		        {
		            
		    		fuses.remove(go);
		    		//change1=true;
		    		change=true;
		    		change1=true;
		    		wallspeed=3;
	        		wallspawn=50;
	        		//changes speed sof walls
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
	//light detect
	public void lightdetect()
	{
		for(int i= lights.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
		    	Light go = lights.get(i);
		    	//remove when off screen
		    	if(go.pos.x<0)
		    	{
		    		lights.remove(go);
		    	}
		    	//if touch start powerdown
		    	if (go.pos.dist(other.pos) < other.halfW + go.w/2)
		        {
		            // Do some casting
		    		lights.remove(go);
		    		change1=true;
		        }
			}
	    }
		//timer for powerdown
		if (change1==true)
		{
			count1++;
			if(count1<600)
			{
				//make background white and add circle
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
	//speed detect
	public void speeddetect()
	{	
		for(int i= speeds.size()-1; i>=0;i--)
	    {
			for(int j=ast.size()-1;j>=0;j--)
			{
				Astro other = ast.get(j);
		    	SpeedPowerup go = speeds.get(i);
		    	//remove if past screen
		    	if(go.pos.x<0)
		    	{
		    		speeds.remove(go);
		    	}
		    	//square detection
		        if(other.pos.y<go.pos.y+(go.h/2) && other.pos.y>go.pos.y-(go.h/2))
		        {
		        	if((other.pos.x+other.w/2-10)>go.pos.x  && (other.pos.x+other.w/2-10)<(go.pos.x+go.w) || (other.pos.x-other.w/2+5)>go.pos.x  && (other.pos.x-other.w/2+5)<(go.pos.x+go.w))
		        	{
		        		//change wall speeds
		        		change=true;
		        		speeds.remove(go);
		        		wallspeed=3;
		        		wallspawn=50;
		        		//change speeds and start timer
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
		//check timer
		if (change==true)
		{
			count++;
			//when time passes reset speeds
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
	//render start menu
	public void startmenu()
	{
		start.render();
	    start.update();
	}
	//render end menu
	public void endmenu()
	{
		end.render();
		end.update();
	}
	//running game class
	public void game()
	{
		//wall and powerdown detects
		topwalldetect();
	    bottomwalldetect();
		fusiondetect();
	    speeddetect();
	    fueldetect();
	    lightdetect();
	    //wall spawn                                                              
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
		    //score changes every time a wall spawns
		    current+=1;
	    }
	    //fuel spawns every 4 walls
	    if (frameCount % (wallspawn*4) == 0)
	    {
	    	Walls go = topwalls.get(topwalls.size()-1);  
		    Bottomwall go1 = botwalls.get(botwalls.size()-1);
		    k=random(go.h+30,(height-go1.h)-30);
	    	Fuelpowerup fuel = null;
	    	fuel = new Fuelpowerup(this,k,wallspeed);
        	fuels.add(fuel);
	    }
	    // a random powerdown spawns every 600 frames
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
	    //fuel is reduced every 25 frames
		if(frameCount % 25 == 0)
		{
			for(int i= ast.size()-1; i>=0;i--)
		    {
		    	Astro go = ast.get(i);  
		        go.fuel-=3;
		    }
		}
		//render powerdowns
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
	    //render fuel
	    for(int i= fuels.size()-1; i>=0;i--)
	    {
	    	Fuelpowerup go = fuels.get(i);  
	        go.update();
	        go.render();
	    }
	    //render astronaut
	    for(int i= ast.size()-1; i>=0;i--)
	    {
	    	Astro go = ast.get(i);  
	        go.update();
	        go.render();
	        go.fuel();
	    }
	    //render walls
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
	    //score board
	    fill(125,0,125);
	    rect((width/2-180),0,400,45);
	    textSize(32);
	    fill(255);
	    text("Score:"+current,width/2-160,36);
	    text("Highscore:"+high,width/2-5,36);
	    //check for high score
	    if(current>high)
	    {
	    	high=current;
	    }
	   
	}
	
	
}