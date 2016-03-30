import processing.core.PApplet;
import processing.core.PVector;

public class Walls {
	//fields
	PApplet parent;
	float w;
	float h;
	PVector wallpos;
	
	Walls(PApplet p)
	{
		parent=p;
		wallpos.y=0;
		wallpos.x=parent.width;
		w=parent.random(30,70);
		h=parent.random(25,parent.height-30);
	
	}
	
	void update()
	{	
		wallpos.x--;
	}
	
	void render()
	{
		parent.fill(255);
		parent.rect(wallpos.x,wallpos.y,w,h);
	}

}
