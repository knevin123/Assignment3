import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class speedPowerup {
	PApplet parent;
	PVector pos;
	int w;
	int h;
	PImage speed;
	speedPowerup(PApplet p)
	{
		parent=p;
		w=50;
		h=90;
		pos = new PVector((parent.width/2-(w/2)), (parent.height/2-(h/2)));
		speed=parent.loadImage("speed.png");
	}
	void render() 
	{
		parent.image(speed, pos.x,pos.y,w, h);

	}
	void update()
	{
		pos.x++;
	}

}
