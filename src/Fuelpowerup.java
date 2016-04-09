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
	float speed;
	Fuelpowerup(PApplet p,float y,float x)
	{
		parent=p;
		w=30;
		h=50;
		speed=x;
		up=true;
		pos = new PVector((parent.width), y);
		fuel=parent.loadImage("fuel.png");
	}
	void render() 
	{
		parent.image(fuel, pos.x,pos.y,w, h);

	}
	void update()
	{
		pos.x-=speed;
	}

}
