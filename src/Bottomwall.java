import processing.core.PApplet;
import processing.core.PVector;

public class Bottomwall {
	//fields
		PApplet parent;
		float w;
		float h;
		PVector wallpos;
		
		Bottomwall(PApplet p)
		{
			parent=p;
			wallpos = new PVector(0, 0);
			wallpos.y=parent.height;
			wallpos.x=parent.width;
			w=parent.random(30,70);
			h=parent.random(25,(parent.height/2)-30);
		
		}
		
		void update()
		{	
			wallpos.x--;
		}
		
		void render()
		{
			parent.fill(255);
			parent.rect(wallpos.x,(parent.height-h),w,wallpos.y);
		}
}
