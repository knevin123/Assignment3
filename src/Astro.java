
import processing.core.PApplet;
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
	int elapsed = 10;
	
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
		
	
	}
	
	void update()
	{	
		if (parent.keyPressed)
		{
			if (parent.key=='w' && elapsed > 10)
			{
				pos.y-=50;
				elapsed = 0;
			}
		}
		pos.y+=2;
		elapsed ++;
	}
	
	void render()
	{
		parent.pushMatrix();
		parent.translate(pos.x, pos.y);
		parent.rotate(theta);
		parent.stroke(255);
		parent.line(- halfW, halfW, 0, - halfW);
		parent.line(0, - halfW, halfW, halfW);
		parent.line(halfW, halfW, 0, 0);
		parent.line(- halfW, halfW, 0, 0);
		parent.popMatrix();
	} 
	
}

