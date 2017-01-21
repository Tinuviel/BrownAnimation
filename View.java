import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Lovisa Colerus
 * 2016
 *
 */
public class View extends JPanel{
	//JLabel label = new JLabel("POLLEN");
	Model m;
	Color[] colors = new Color[10];
	FollowParticles fp;
	//colors[8] = Color.


	/**
	 * h�mtar v�rden fr�n Model och FollowParticles
	 * ritar f�nstret partiklarna ska visas i
	 * V�rdes�tter en lista med f�rger som de 10 partiklarna ska f�ljas med
	 * @param m
	 */
	public View(Model m, FollowParticles fp){
		this.m = m;
		this.fp = fp;
		setPreferredSize(m.getDimension());
		setBackground(Color.white);
		colors[0] = Color.BLUE;
		colors[1] = Color.RED;
		colors[2] = Color.PINK;
		colors[3] = Color.GRAY;
		colors[4] = Color.GREEN;
		colors[5] = Color.YELLOW;
		colors[6] = Color.BLACK;
		colors[7] = Color.MAGENTA;
		colors[8] = Color.CYAN;
		colors[9] = Color.ORANGE;

	}
	/**
	 * ritar alla partiklar
	 * ritar de kors med f�rger som f�ljer de f�ljda partiklarna
	 */
	public void paint(Graphics g){
		super.paintComponent(g);
		for(Particle p: m.allParticles){
			g.drawRect((int)Math.round(p.x), (int)Math.round(p.y), 1, 1);
		}	
		for(Particle p: m.trackedParticles){
			int index = m.trackedParticles.indexOf(p);
			if(fp.change[index] != false){
				g.setColor(colors[index]);
				g.drawLine((int)p.x, (int)p.y, 0, (int)p.y);
				g.drawLine((int)p.x, (int)p.y, 500, (int)p.y);
				g.drawLine((int)p.x, (int)p.y, (int)p.x, 0);
				g.drawLine((int)p.x, (int)p.y, (int)p.x, 500);
				g.drawString(""+index, (int)p.x, (int)p.y);
			}

		}
	}
}
