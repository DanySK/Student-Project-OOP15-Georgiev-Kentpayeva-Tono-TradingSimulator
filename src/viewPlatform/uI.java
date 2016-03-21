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
	private int conto=2000;
	private int guadagno=88;
	
	public uI(){
		this.setLayout(new BorderLayout());
		
		JPanel nord= new JPanel();
		JPanel nord2= new JPanel();
		
		JPanel nord3= new JPanel();
		
		
		JPanel south= new JPanel();
		JPanel south2= new JPanel();
		
		
		JTextArea name=new JTextArea(1,7);
		JLabel lImporto=new JLabel("importo: $");
		JLabel lContoDemo=new JLabel("CONTO DEMO: ");
		JLabel lContoDemoVal=new JLabel(Integer.toString(this.conto)+" $");
		
		
		JLabel lGuadagno=new JLabel("Guadagno: ");
		JLabel lGuadagnoVal=new JLabel(Integer.toString(this.guadagno)+" %");
		
		JButton up= new JButton("UP");
		JButton down= new JButton("DOWN");
		
		nord2.setLayout(new BorderLayout());
		
		
		lImporto.setSize(1,7);
		lContoDemoVal.setSize(1,7);
		name.setSize(5,5);
		
		//aggiungo gli elementi all'user interface
		nord.add(lImporto);
		nord.add(name);
		nord2.add(lContoDemo,BorderLayout.NORTH);
		nord2.add(lContoDemoVal,BorderLayout.NORTH);
		nord2.add(lGuadagno,BorderLayout.SOUTH);
		nord2.add(lGuadagnoVal,BorderLayout.SOUTH);
		
		
		nord3.setLayout(new BorderLayout());
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
		
		
		
		
		
		this.add(nord3,BorderLayout.NORTH);
		this.add(south,BorderLayout.CENTER);
		this.add(south2,BorderLayout.SOUTH);
		
		
		
		
		
	}
	
	public void setAssetValues(ValuesAsset asset){
		this.asset=asset;
	}
	
	public void takePoint(){
		System.out.println("punto preso--> "+this.asset.toString());
	}
}
