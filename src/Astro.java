
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

class Astro
{
	// Fields!
	PApplet parent;
	PVector pos;
	PVector forward;
	float theta = 0.0f;
	float w;
	float halfW;
	float speed = 5.0f;
	float fuel;
	int c;
	int elapsed = 8;
	PImage normal;
	PImage jump;
	
	// Constructor!!
	// HAS NO RETURN TYPE
	// Name is always the same as the class
	Astro(float x, float y, float w,PApplet p)
	{
		
		parent = p;
		pos = new PVector(x, y);
		this.w = w; // Disambiguate w by using this
		this.halfW = w * 0.5f;
		this.theta = 0.0f;
		fuel=100;
		normal=parent.loadImage("sprite1.png");
		jump=parent.loadImage("sprite2.png");
		c=parent.color(0,255,0);
				
	
	}
	
	void update()
	{	
		if (parent.keyPressed)
		{
			if (parent.key=='w' /*&& elapsed > 8*/)
			{
				pos.y-=10;
				elapsed = 0;
			}
		}
		pos.y+=2.5;
		elapsed ++;
	}
	
	void render()
	{
		if (parent.keyPressed)
		{
			if (parent.key=='w')
			{
				parent.image(jump,pos.x-(w/2),pos.y,w,w);
			}
		}
		parent.image(normal,pos.x-(w/2),pos.y,w,w);
		
		
	} 
	void fuel()
	{
		if(fuel<20)
		{
			c=parent.color(245,85,30);
		}
		if(fuel>20 && fuel<40)
		{
			c=parent.color(242,151,31);
		}
		if(fuel>20 && fuel<40)
		{
			c=parent.color(227,167,2);
		}
		if(fuel>40 && fuel<70)
		{
			c=parent.color(227,219,2);
		}
		if(fuel>70)
		{
			c=parent.color(170,232,37);
		}
		
		parent.fill(c);
		if(fuel>0)
		{
			parent.rect((float) (pos.x-w/1.5), pos.y+w-10,10,-fuel);
		}
	}
}

