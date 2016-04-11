import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Light {
	PApplet parent;
	PVector pos;
	int w;
	int h;
	PImage speed;
	boolean up;
	Light(PApplet p)
	{
		parent=p;
		w=40;
		h=50;
		up=true;
		pos = new PVector((parent.width), (parent.height/2-(h/2)));
		speed=parent.loadImage("light.png");
	}
	void render() 
	{
		parent.image(speed, pos.x,pos.y,w, h);

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
