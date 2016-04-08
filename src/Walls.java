import processing.core.PApplet;
import processing.core.PVector;

public class Walls {
	//fields
	PApplet parent;
	float w;
	float h;
	PVector wallpos;
	float speed;
	Walls(PApplet p)
	{
		parent=p;
		wallpos = new PVector(0, 0);
		wallpos.y=0;
		wallpos.x=parent.width;
		w=100;
		speed=(float) 1.5;
		h=parent.random(25,(parent.height/2)-75);
	
	}
	
	void update()
	{	
		wallpos.x-=speed;
	}
	
	void render()
	{
		parent.fill(255);
		parent.rect(wallpos.x,wallpos.y,w,h);
	}

}
