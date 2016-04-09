import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Fuelpowerup {
	PApplet parent;
	PVector pos;
	int w;
	int h;
	PImage fuel;
	boolean up;
	Fuelpowerup(PApplet p)
	{
		parent=p;
		w=30;
		h=50;
		up=true;
		pos = new PVector((parent.width), (parent.height/2-(h/2)));
		fuel=parent.loadImage("fuel.png");
	}
	void render() 
	{
		parent.image(fuel, pos.x,pos.y,w, h);

	}
	void update()
	{
		pos.x-=2;

		if(pos.y<parent.height/2-200)
		{
			up=false;
		}
		if(pos.y>parent.height/2+200)
		{
			up=true;
		}
		
		if(up==true)
		{
			pos.y-=4;
		}
		else
		{
			pos.y+=4;
		}
	}

}
