
import processing.core.PApplet;

public class Stars {
	PApplet parent;
	int elapsed=12;
	int color;
	float posx;
	float posy;
	boolean colchange;
	Stars(PApplet p)
	{
		parent=p;
		color=(int)parent.random(0,255);
		colchange=true;
		posy=parent.random(parent.height);
		posx=parent.random(parent.width);
	}
	void update()
	{	
		if(color==0)
		{
			colchange=!colchange;
		}
		if(color==255)
		{
			colchange=!colchange;
		}
		if (colchange==false)
		{
			color--;
		}
		if (colchange==true)
		{
			color++;
		}
	}
	void render()
	{  
		parent.noStroke();
		parent.fill(color);
		parent.ellipse(posx, posy, 3, 3);
		
	}
}
