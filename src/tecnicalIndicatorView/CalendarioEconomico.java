package tecnicalIndicatorView;

import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CalendarioEconomico {

	  static Calendar cal;
	  static int a=0;
	
	public static void main(String[] s){
		System.out.println("calendario");
		/*cal = Calendar.getInstance();
		cal.set(1995, 8, 11, 5, 20, 30);
		cal.set(a, 0);
		System.out.println("calendario  "+cal.getFirstDayOfWeek());*/
		
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		
		catch (ClassNotFoundException e) {}
		
		catch (InstantiationException e) {}
		
		catch (IllegalAccessException e) {}
		
		catch (UnsupportedLookAndFeelException e) {}
		
		 
		
		//Prepare frame
		
		frmMain = new JFrame ("Gestionnaire de clients"); //Create frame
		
		frmMain.setSize(330, 375); //Set size to 330x375 pixels
		
		pane = frmMain.getContentPane(); //Get content pane
		
		pane.setLayout(null); //Apply null layout
		
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
		
		 
		
		//Create controls
		
		lblMonth = new JLabel ("January");
		
		lblYear = new JLabel ("Change year:");
		
		cmbYear = new JComboBox();
		
		btnPrev = new JButton ("<<");
		
		btnNext = new JButton (">>");
		
		mtblCalendar = new DefaultTableModel();
		
		tblCalendar = new JTable(mtblCalendar);
		
		stblCalendar = new JScrollPane(tblCalendar);
		
		pnlCalendar = new JPanel(null);

		
		
	}
	
}
