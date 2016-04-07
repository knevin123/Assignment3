import processing.core.PApplet;
import processing.core.PVector;

public class speedPowerup {
	PApplet parent;
	PVector pos;
	int w;
	int h;
	speedPowerup(PApplet p)
	{
		parent=p;
		pos = new PVector(parent.width/2, parent.height/2);
		w=30;
		h=45;
	}
	void render() 
	{
		parent.stroke(0,0,255);
		parent.line(pos.x,pos.y,pos.x+w,pos.y-h);
		parent.line(pos.x+w,pos.y-h,pos.x+w/2,pos.y-10);
		parent.line(pos.x+w/2,pos.y-10,pos.x+w,pos.y-10);
		parent.line(pos.x+w,pos.y-10,pos.x,(float) (pos.y+h/1.5));
		parent.line(pos.x,(float) (pos.y+h/1.5),pos.x+w/2,pos.y);
		parent.line(pos.x+w/2,pos.y,pos.x,pos.y);

	}
	void update()
	{
		pos.x++;
	}

}
