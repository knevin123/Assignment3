import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Fuelpowerup {
	//fields
	PApplet parent;
	PVector pos;
	int w;
	int h;
	PImage fuel;
	boolean up;
	float speed;
	Fuelpowerup(PApplet p,float y,float x)
	{
		//set up image
		parent=p;
		w=25;
		h=40;
		speed=x;
		up=true;
		pos = new PVector((parent.width), y);
		fuel=parent.loadImage("fuel.png");
	}
	//render image
	void render() 
	{
		parent.image(fuel, pos.x,pos.y,w, h);

	}
	//move image
	void update()
	{
		pos.x-=speed;
	}

}
