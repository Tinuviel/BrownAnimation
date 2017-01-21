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
	 * skapar en partikel med bestämda startkoordinater
	 * används ej men fanns med i beskrivningen att den skulle göras
	 * @param xs
	 * @param ys
	 */
	Particle(double xs, double ys){
		this.x = xs;
		this.y = ys;
	}

	/**
	 * Kollar om partikeln har krockat med väggen 
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
	 * Kollar avståndet till stillastående partiklar
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
	 * Ändrar positionen för partikeln med en vektor av längden L
	 * Ser till så att den inte hamnar utanför visningsfönstret
	 */
	public void randomMove(){
		int direction = rand.nextInt(359);
		double dirToRadians = Math.toRadians(direction);
		int x = (int) Math.round((this.x + (m.getL() * Math.cos(dirToRadians))));
		int y = (int) Math.round((this.y + (m.getL() * Math.sin(dirToRadians))));
		this.x = checkBorders(x);
		this.y = checkBorders(y);
		//slumpa fram antalet grader vinkeln på vektorn är

	}

	/**
	 * Kollar ifall koordinaterna är utanför fönstret
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
