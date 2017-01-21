import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Lovisa Colerus
 * 2016
 *
 */

class Simulation extends Thread{
	Model m;
	View v;
	FollowParticles fp;

	/**
	 * startar simuleringen + sparar referenser
	 * @param m
	 * @param v
	 */
	Simulation(Model m, View v, FollowParticles fp){
		this.m = m;
		this.v = v;
		this.fp = fp;

	}

	/**
	 * stegar fram tiden och begär omritning med jämna mellanrum
	 * stegas fram parallellt med att resten av programmet körs
	 * flyttar alla partiklar
	 * ritar om partiklarna och dess fönster
	 * ritar om tabellen över de följda partiklarna
	 */
	public void run(){
		while(true){
			m.moveAll();
			v.repaint();
			DefaultTableModel dtm = fp.updateTableModel();
			fp.table.setModel(dtm);
			fp.table.repaint();


			try {
				sleep( m.getMS() );
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}
}
