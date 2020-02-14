package Game;

import java.awt.Graphics;
import java.awt.Image;

public class Explode {
	
	double x,y;
	static Image[] images = new Image[5];
	static {
		for (int i = 0; i < 5; i++) {
			
			images[i] = GameUtil.getImage("Image/boom/b" + "3" + ".gif");
			images[i].getWidth(null);
		}
	}
	
	int count;
	public void draw(Graphics g) {
		if(count <= 4) {
			g.drawImage(images[count], (int)x, (int)y, null);
			count++;
		}
	}
	
	public Explode(double x, double y) {
		this.x = x;
		this.y = y;
	}

}
