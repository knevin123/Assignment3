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
		
	}

}
