package viewPlatform;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modelPlatform.ValuesAsset;

public class ViewPlatformImpl extends JFrame implements ViewPlatform{

		
			
	public ViewPlatformImpl(){
		
		super("Trading Platoform");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel canvas =new JPanel();
		
		JFrame graph=this.drawGraph();
		JPanel ui=this.uI();
		JPanel buy=this.buy();
		
		graph.pack();
		ui.setSize(400,1400);
		
		
		
		ui.setVisible(true);
		buy.setVisible(true);
		
		
		this.setSize(1800, 1800);
		
		canvas.add(buy,BorderLayout.WEST);
		canvas.add(graph.getContentPane(),BorderLayout.CENTER);
		canvas.add(ui,BorderLayout.EAST);
		
		
		//this.pack();
		this.setContentPane(canvas);
		this.setVisible(true);
		
		
	}
		
			@Override
			public JFrame drawGraph() {
				// TODO Auto-generated method stub
				
				return new Candlestick("MSFT");
			    
				
			}
			
			
			@Override
			public JPanel uI() {
				// TODO Auto-generated method stub
				return new uI();
				
				
			}

			
			@Override
			public JPanel buy() {
				// TODO Auto-generated method stub
				return new buy();
			}
			

			
			//_______________________________metodi per il controller____________________________________
			@Override
			public void setValueGraph(ValuesAsset asset ) {
				// TODO Auto-generated method stub
				asset.toString();
				
			}
		}
		
		
		

