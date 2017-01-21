import java.awt.Dimension;
import java.util.Vector;
/**
 * 
 * @author Lovisa Colerus
 * 2016
 *
 */

public class Model {
	int ms;
	int L;
	int side;

	public Vector<Particle> allParticles = new Vector<Particle>();
	public Vector<Particle> stillParticles = new Vector<Particle>();
	public Vector<Particle> trackedParticles = new Vector<Particle>();
	/**
	 * Skapar listan med alla partiklar 
	 * och en lista med de följda partiklarna
	 * Eftersom alla är slumpgenererade tar den endast de 10 första
	 * i allParticles-listan
	 * @param side storleken på fönstret
	 */
	Model(int side){
		this.side = side;
		this.L = 3;
		this.ms = 150;

		for(int i = 0; i<10000; i++){
			allParticles.add(new Particle(this));
		}
		for(int i = 0; i<10; i++){
			trackedParticles.add(allParticles.elementAt(i));
		}

	}

	/**
	 * slumpförflyttar alla partiklar
	 * ändrar isMoving ifall en partikel har krockat
	 */
	public void moveAll(){		
		for(Particle p: allParticles){
			if(p.isMoving == true){
				p.randomMove();
				p.isMoving = p.isMoving();

				if(p.isMoving == false){
					stillParticles.add(p);
				}
			}
		}
	}

	public Dimension getDimension(){
		return new Dimension(this.side, this.side);
	}

	public int getSide(){
		return this.side;
	}


	public void setL(int L){
		//ändrar längden L som partiklarna förflyttar sig
		this.L = L;
	}

	public int getL(){
		return this.L;
	}

	public int getMS(){
		return ms;
	}

	public void setTimeStep(int ms){
		//ändrar tidssteget mellan omritningarna
		this.ms = ms;
	}


}
