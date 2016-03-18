package viewPlatform;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modelPlatform.*;

public class uI extends JFrame{

	
	ValuesAsset asset=null;
	
	public uI(){
		this.setLayout(new BorderLayout());
		
		JPanel nord= new JPanel();
		JPanel nord2= new JPanel();
		
		JPanel nord3= new JPanel();
		
		
		JPanel south= new JPanel();
		JPanel south2= new JPanel();
		
		
		JTextArea name=new JTextArea(1,7);
		JTextArea surname=new JTextArea(1,7);
		JLabel lName=new JLabel("name: ");
		
		JButton up= new JButton("UP");
		JButton down= new JButton("DOWN");
		
		
		
		lName.setSize(5,5);
		name.setSize(5,5);
		
		//aggiungo gli elementi all'user interface
		nord.add(lName,BorderLayout.CENTER);
		nord.add(name,BorderLayout.CENTER);
		nord2.add(surname,BorderLayout.SOUTH);
		
		nord3.add(nord,BorderLayout.NORTH);
		nord3.add(nord2,BorderLayout.SOUTH);
		
		south.add(up,BorderLayout.CENTER );
		south2.add(down,BorderLayout.SOUTH );
		
	
		//gestisco gli eventi grafici dell'user interface
		up.addActionListener(e->{
			this.takePoint();
		});
		
		down.addActionListener(e->{
			this.takePoint();
		});
		
		
		
		
		
		//this.add(nord,BorderLayout.NORTH);
		this.add(nord3,BorderLayout.NORTH);
		this.add(south,BorderLayout.CENTER);
		this.add(south2,BorderLayout.SOUTH);
		
		//this.setContentPane(total);
		
		
		
	}
	
	public void setAssetValues(ValuesAsset asset){
		this.asset=asset;
	}
	
	public void takePoint(){
		System.out.println("punto preso--> "+this.asset.toString());
	}
}
