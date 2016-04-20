import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class SpeedPowerup {
	//fields
	PApplet parent;
	PVector pos;
	int w;
	int h;
	PImage speed;
	boolean up;
	SpeedPowerup(PApplet p)
	{
		//set up image
		parent=p;
		w=60;
		h=80;
		up=true;
		pos = new PVector((parent.width), (parent.height/2-(h/2)));
		speed=parent.loadImage("speed.png");
	}
	//render image
	void render() 
	{
		parent.image(speed, pos.x,pos.y,w, h);

	}
	//update image
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
