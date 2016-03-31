
import processing.core.PApplet;

public class Stars {
	PApplet parent;
	int elapsed=12;
	int color;
	Stars(PApplet p)
	{
		parent=p;
		color=255;
	}
	void update()
	{
		color--;
	}
	void render()
	{  
		parent.noStroke();
		parent.fill(color);
		parent.ellipse(parent.random(parent.width), parent.random(parent.height), 10, 10);
		
	}
}
