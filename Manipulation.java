import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 * hanterar all indata fr�n anv�ndare, scrollbars, knapptryck osv
 * @author Lovisa Col�rus
 * 2016
 *
 */
public class Manipulation extends JPanel implements  AdjustmentListener{
	Model m;
	View v;
	JScrollBar sc1 = new JScrollBar(JScrollBar.VERTICAL, 3, 0, 0, 300);
	JScrollBar sc2 = new JScrollBar(JScrollBar.VERTICAL, 50, 0, 0, 300);
	JLabel j = new JLabel("  T      L");

	/**
	 * Skapar tv� scrollbars och s�ger vad de ska g�ra
	 * Den ena �ndrar l�ngden p� f�rflyttnignsvektorn
	 * Den andra �ndrar tiden mellan f�rflyttningar
	 * @param m
	 * @param v
	 */
	public Manipulation(Model m, View v){
		this.m = m;
		this.v = v;
		setLayout(new BorderLayout());
		sc1.setPreferredSize(new Dimension(20,250));
		sc2.setPreferredSize(new Dimension(20,250));
		add(sc1, BorderLayout.EAST);
		add(sc2, BorderLayout.WEST);
		add(j, BorderLayout.SOUTH);
		sc1.addAdjustmentListener(this);
		sc2.addAdjustmentListener(this);
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		m.setL(sc1.getValue());
		m.setTimeStep(sc2.getValue());

	}

}
