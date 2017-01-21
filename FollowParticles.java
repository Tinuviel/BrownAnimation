import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

/**
 * 
 * @author Lovisa Colerus
 * 2016
 *
 */

public class FollowParticles extends JFrame implements TableModelListener{
	String[] columnNames = {"Particle",	"Coordiantes", "State", "Tracking"};
	Model m;
	boolean change[];
	JTable table;
	DefaultTableModel tableModel;

	/**
	 * Tar in v�rden fr�n Model
	 * Ritar upp en JFrame med en tabell i �ver de 10 f�ljbara partiklarna
	 * 
	 * @param m
	 */
	FollowParticles(Model m){
		super("Following");
		this.m = m;
		this.change =  new boolean[10];
		this.tableModel = new DefaultTableModel(this.giveInfo(), columnNames);
		change[0] = true;
		setSize(500, 300);
		setLocation(750,50);
		
		
		this.table = new JTable(this.tableModel){
			//S�tter vilken klass-sort kolumnerna ska ha
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				default:
					return Boolean.class;
				}
			}
			//S�ger att endast den sista kolumnen f�r �ndras av anv�ndaren
			public boolean isCellEditable(int row, int column) { 
				if(column == 3){
					return true;
				}else{
					return false; 
				}

			};
		};
		table.getModel().addTableModelListener(this);	
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		setVisible(true);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	/**
	 * Uppdaterar vilken tableModel som tabellen ritas med
	 * @return DefaultTableModel tableModel
	 */
	public DefaultTableModel updateTableModel(){
		DefaultTableModel tableModel = new DefaultTableModel(this.giveInfo(), columnNames);
		tableModel.addTableModelListener(this);
		return tableModel;
	}



	/**
	 * Skapar matrisen med info som ska ritas upp i tabellen
	 * @return Object[][] data
	 */
	public Object[][] giveInfo(){
		Object[][] data = new Object[10][4];
		String s;
		for(int i = 0; i<10; i++){
			Particle p = this.m.allParticles.elementAt(i);
			if(p.isMoving){
				s = "Moving";
			}else{
				s = "Stopped";
			}
			Object[] n = {i+1, (int)Math.round(p.x) + ", " + (int)Math.round(p.y), s, change[i]};
			data[i] = n;
		}
		return data;

	}
	
	
	/**
	 * Ifall anv�ndaren �ndrar p� checkboxarna i tabellen (tracking or not tracking)
	 * �ndras v�rdet i change och n�r den ritas upp igen g�r den det med samma v�rde
	 * som anv�ndaren bet�mde
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel tableModel = (TableModel)e.getSource();
		change[row] = (boolean) tableModel.getValueAt(row, column);
	}
}

