package viewPlatform;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class buy extends JPanel{
	
	public buy(){
		this.setLayout(new BorderLayout());
		
		JPanel nord= new JPanel();
		JPanel nord2= new JPanel();
		
		JPanel nord3= new JPanel();
		
		
		JPanel south= new JPanel();
		JPanel south2= new JPanel();
		
		
		JTextArea name=new JTextArea(1,7);
		JTextArea surname=new JTextArea(1,7);
		JLabel lName=new JLabel("name: ");
		JLabel lSurname=new JLabel("surname: ");
		
		JButton up= new JButton("UP");
		JButton down= new JButton("DOWN");
		
		
		
		lName.setSize(1,7);
		lSurname.setSize(1,7);
		name.setSize(5,5);
		
		//aggiungo gli elementi all'user interface
		nord.add(lName);
		nord.add(name);
		nord2.add(lSurname);
		nord2.add(surname);
		
		
		nord3.setLayout(new BorderLayout());
		nord3.add(nord,BorderLayout.NORTH);
		nord3.add(nord2,BorderLayout.SOUTH);
		
		south.add(up,BorderLayout.CENTER );
		south2.add(down,BorderLayout.SOUTH );
		
	
		//gestisco gli eventi grafici dell'user interface
		up.addActionListener(e->{
			
		});
		
		down.addActionListener(e->{
			
		});
		
		
		
		
		
		this.add(nord3,BorderLayout.NORTH);
		this.add(south,BorderLayout.CENTER);
		this.add(south2,BorderLayout.SOUTH);
		
		
		
	}
	
}
