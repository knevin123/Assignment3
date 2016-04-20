import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Fusion {
	//fields
	PApplet parent;
	PVector pos;
	int w;
	int h;
	PImage speed;
	boolean up;
	Fusion (PApplet p)
	{
		//set up image
		parent=p;
		w=70;
		h=60;
		up=true;
		pos = new PVector((parent.width), (parent.height/2-(h/2)));
		speed=parent.loadImage("fusion.png");
	}
	//render image
	void render() 
	{
		parent.image(speed, pos.x,pos.y,w, h);

	}
	//move image
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