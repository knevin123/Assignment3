import processing.core.PApplet;

public class Startmenu {
	PApplet parent;
	Startmenu(PApplet p)
	{
		parent=p;
	}
	void render()
	{
		parent.textSize(64);
		parent.fill(130);
		parent.rect(0,parent.height/3-64, (float) (parent.width/1.5), 94);
		parent.fill(255);
		parent.text("Space", parent.width/4, parent.height/3);
		parent.fill(130);
		parent.rect((float) (parent.width/1.75)-75,(parent.height/2)-68,parent.width, 94);
		parent.fill(255);
		parent.text("Hoopers", (float) (parent.width/1.75), (float) (parent.height/2));
	}

}
