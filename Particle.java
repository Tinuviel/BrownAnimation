import java.util.*;

/**
 * 
 * @author Lovisa Colerus
 * 2016
 *
 */
public class Particle {
	double x, y;
	boolean isMoving = true;
	Random rand = new Random ();
	Model m;

	/**
	 * Skapar en pollenpartikel med slumpade startkoordinater
	 * @param m
	 */
	Particle(Model m){
		this.x = (200 + rand.nextDouble()* 50);
		this.y = (rand.nextDouble()* m.getSide());
		this.m = m;
		this.isMoving = true;
	}

	/**
	 * skapar en partikel med best�mda startkoordinater
	 * anv�nds ej men fanns med i beskrivningen att den skulle g�ras
	 * @param xs
	 * @param ys
	 */
	Particle(double xs, double ys){
		this.x = xs;
		this.y = ys;
	}

	/**
	 * Kollar om partikeln har krockat med v�ggen 
	 * eller en annan partikel
	 * @return om partikeln har stannat (false)
	 */
	public boolean isMoving(){
		if((this.x <5) || this.y < 5 || this.x > (m.getSide()-5) ||
				this.y > (m.getSide()-5)){
			return false;

		}else{
			for(Particle p : m.stillParticles){
				if(this.checkDistance(p)){
					return false;
				}
			}
			return true;
		}
	}


	/**
	 * Kollar avst�ndet till stillast�ende partiklar
	 * @param p
	 * @return true om de har krockat
	 */
	public boolean checkDistance(Particle p){
		if(p.x-1 <= this.x && this.x < p.x+1){
			if(p.y-1 <= this.y && this.y <= p.y+1){
				return true;
			}else{
				return false;
			}
		}else{
			return false;	
		}
	}

	/**
	 * �ndrar positionen f�r partikeln med en vektor av l�ngden L
	 * Ser till s� att den inte hamnar utanf�r visningsf�nstret
	 */
	public void randomMove(){
		int direction = rand.nextInt(359);
		double dirToRadians = Math.toRadians(direction);
		int x = (int) Math.round((this.x + (m.getL() * Math.cos(dirToRadians))));
		int y = (int) Math.round((this.y + (m.getL() * Math.sin(dirToRadians))));
		this.x = checkBorders(x);
		this.y = checkBorders(y);
		//slumpa fram antalet grader vinkeln p� vektorn �r

	}

	/**
	 * Kollar ifall koordinaterna �r utanf�r f�nstret
	 * @param x
	 * @return
	 */
	public int checkBorders(int x){
		if(0 <= x && x <= m.getSide()){
			return x;
		}else if(x > m.getSide()){
			return m.getSide();
		}else{
			return 0;
		}
	}
}
