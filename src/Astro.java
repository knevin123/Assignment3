
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
		normal=parent.loadImage("sprite1.png");
		jump=parent.loadImage("sprite2.png");
				
	
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
		else
		{
			parent.image(normal,pos.x-(w/2),pos.y,w,w);
		}
		
	} 
	
}

