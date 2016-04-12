
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Endmenu {
	PApplet parent;
	PImage normal;
	PVector pos;
	boolean up;
	float w;
	float h;
	PVector text1;
	PVector text2;
	Endmenu(PApplet p)
	{
		parent=p;
		normal=parent.loadImage("sprite2.png");
		pos = new PVector(0, 0);
		text1 = new PVector(-150, parent.height/3);
		text2 = new PVector(parent.width, (parent.height/2));
		pos.x=parent.width/2;
		w=100;
		h=110;
		pos.y=(float) (parent.height/1.35);
	}
	void render()
	{
		parent.textSize(64);
		parent.fill(190);
		parent.ellipse(parent.width/2,parent.height/3+20, 400,400);
		parent.fill(150);
		parent.stroke(70);
		parent.ellipse(parent.width/2+50,parent.height/3+150, 50,50);
		parent.ellipse(parent.width/2+75,parent.height/3-100, 100,100);
		parent.ellipse(parent.width/2-80,parent.height/3+80, 60,60);
		parent.ellipse(parent.width/2-120,parent.height/3+100, 75,75);
		parent.ellipse(parent.width/2-100,parent.height/3-100, 40,50);
		parent.ellipse(parent.width/2+120,parent.height/3+40, 75,50);
		parent.fill(255);
		parent.stroke(0);
		parent.text("Game", text1.x, text1.y);
		parent.text("Over", text2.x, text1.y+100);
		
		parent.image(normal,pos.x,pos.y,w,h);
	}
	void update()
	{
		pos.x++;
		if(text1.x<(parent.width/4)+75)
		{
			text1.x+=2;
		}
		if(text2.x>(parent.width/1.75)-75)
		{	
			text2.x-=2;
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
