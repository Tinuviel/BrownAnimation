import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 * Skapar en simulation av pollenpartiklar som rör sig slumpmässigt 
 * och fastnar i kanterna på fönstret och i stillastående partiklar
 * Låter användaren föja upp till 10 partiklars bana
 * @author Lovisa Colérus
 * 2016
 *
 */

public class BrownAnimation extends JFrame{

	/**
	 * lägger till Manipulationsobjektet med add
	 * Skapar en JFrame som ritar upp partiklarna
	 * skapar en annan JFrame (via FollowParticles) som ritar upp en tabell
	 * @param args
	 */
	BrownAnimation(){
		super("Brown animation");
		setVisible(true);
		this.setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Model m = new Model(500);
		FollowParticles fp = new FollowParticles(m);
		View v = new View(m, fp);
		Manipulation mp = new Manipulation(m,v);
		(new Simulation(m, v, fp)).start();
		setSize(700, 600);
		setLocation(50,50);
		add(v);
		add(mp);		
		pack();
	}

	public static void main(String[] args){
		new BrownAnimation();
	}



}
