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
			w=100;
			h=parent.random(25,(parent.height/2)-75);
		
		}
		
		void update()
		{	
			wallpos.x-=1.5;
		}
		
		void render()
		{
			parent.fill(255);
			parent.rect(wallpos.x,(parent.height-h),w,wallpos.y);
		}
}
