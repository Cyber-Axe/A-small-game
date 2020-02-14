package Game;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Period;
import java.util.Date;

public class MyGameFrame extends Frame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image planeIMG = GameUtil.getImage("Image/plane.png");
	Image bg = GameUtil.getImage("Image/bg.jpg");
	
	Plane plane = new Plane(planeIMG,250,250);
	Shell shell = new Shell();
	Shell[] shells = new Shell[10];
	Explode booom;
	boolean Oneway = true;
	Date startTime =  new Date();
	Date endTime;
	double period;
	
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);
		shell.draw(g);	
		
		for (int i = 0; i < shells.length; i++) {
			shells[i].draw(g);
            boolean boom = shells[i].getRect().intersects(plane.getRect());
            if(boom) {
            	System.out.println("飞机死亡");
            	endTime =new Date();
            	period = (endTime.getTime() - startTime.getTime())/1000;
            	plane.life = false;
            	booom = new Explode(plane.x, plane.y);
            	booom.draw(g);
            	System.out.println("飞机爆炸");
            	Oneway = false;
            	
            	g.drawString("持续时间："+ period +" 秒", (int)plane.x, (int)plane.y);
            }
		}
		
	}
	
	//反复重画
	class PaintThread extends Thread {
		@Override
		public void run() {
			while(true) {
				//System.out.println("dcd");
				if(Oneway)
					repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.cancleDirection(e);
		}
		
	}
	
	public void lanchFrame() {
		this.setTitle("飞机大战");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setLocation(300, 300);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		new PaintThread().start();
		addKeyListener(new KeyMonitor());
		
		for (int i = 0; i < shells.length; i++) {
			shells[i] = new Shell();
		}
	}

	public static void main(String[] args) {
		
		MyGameFrame frame = new MyGameFrame();
		frame.lanchFrame();
	}
	
	private Image offScreenImage = null;
	
	//缓冲画面
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
}
