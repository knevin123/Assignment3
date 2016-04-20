
import processing.core.PApplet;

public class Stars {
	//fields
	PApplet parent;
	int elapsed=12;
	int color;
	float posx;
	float posy;
	boolean colchange;
	Stars(PApplet p)
	{
		//set up stars
		parent=p;
		color=(int)parent.random(0,255);
		colchange=true;
		posy=parent.random(parent.height);
		posx=parent.random(parent.width);
	}
	//change color of stars
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
	//draw star
	void render()
	{  
		parent.noStroke();
		parent.fill(color);
		parent.ellipse(posx, posy, 3, 3);
		
	}
}
