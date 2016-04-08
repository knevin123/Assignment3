import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Startmenu {
	PApplet parent;
	PImage normal;
	PVector pos;
	boolean up;
	PVector text1;
	PVector text2;
	Startmenu(PApplet p)
	{
		parent=p;
		normal=parent.loadImage("sprite2.png");
		pos = new PVector(0, 0);
		text1 = new PVector(-150, parent.height/3);
		text2 = new PVector(parent.width, (parent.height/2));
		pos.x=parent.width/2;
		pos.y=(float) (parent.height/1.35);
	}
	void render()
	{
		parent.textSize(64);
		parent.fill(130);
		parent.rect(0,parent.height/3-64, (float) (parent.width/1.5), 94);
		parent.fill(255);
		parent.text("Space", text1.x, text1.y);
		parent.fill(130);
		parent.rect((float) (parent.width/1.75)-75,(parent.height/2)-68,parent.width, 94);
		parent.fill(255);
		parent.text("Hoopers", text2.x, (float) (parent.height/2));
		
		parent.image(normal,pos.x-50,pos.y,100,110);
	}
	void update()
	{
		pos.x++;
		if(text1.x<parent.width/4)
		{
			text1.x++;
		}
		if(text2.x>parent.width/1.75)
		{	
			text2.x--;
		}
		if(pos.y<parent.height/1.35-80)
		{
			up=false;
		}
		if(pos.y>parent.height/1.35+80)
		{
			up=true;
		}
		
		if(up==true)
		{
			pos.y-=2;
		}
		else
		{
			pos.y+=2;
		}
		if(pos.x>parent.width)
		{
			pos.x=0;
		}
	}

}