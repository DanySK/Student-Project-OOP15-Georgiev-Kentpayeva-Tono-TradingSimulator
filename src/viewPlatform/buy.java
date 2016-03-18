package viewPlatform;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class buy extends JPanel{
	
	public buy(){
		this.setLayout(new BorderLayout());
		JTextArea name=new JTextArea(5,2);
		JTextArea surname=new JTextArea(5,2);
		JLabel lName=new JLabel("name: ");
		
		JButton up= new JButton("UP");
		JButton down= new JButton("DOWN");
		
		
		this.setSize(400,400);
		
		lName.setSize(5,5);
		name.setSize(5,5);
		
		//aggiungo gli elementi all'user interface
		this.add(lName,BorderLayout.NORTH);
		this.add(name,BorderLayout.NORTH);
		this.add(surname,BorderLayout.NORTH);
		
		this.add(up );
		this.add(down);
		
		
		
		//gestisco gli eventi grafici dell'user interface
		up.addActionListener(e->{
			
		});
		
		down.addActionListener(e->{
			
		});
		
		
	}
	
}
