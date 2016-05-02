package viewPlatform;

import java.awt.BorderLayout;
import java.awt.event.ContainerListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.XYDataset;

import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;

public class ViewPlatformImpl extends JFrame implements ViewPlatform{
	
	public boolean isUpDateCtr=false;

	public boolean isUP=false;
	public boolean isDown=false;
	
	
	AbstractSeriesDataset asset=null;
	public boolean isCandleGraph;//=false;
	
	public ViewPlatformImpl(AbstractSeriesDataset asset,boolean isCandleGraph){
		
		super("Trading Platoform");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.isCandleGraph=isCandleGraph;
        this.asset=asset;
        JPanel canvas =new JPanel();
		
		JFrame graph=this.drawGraph(isCandleGraph);
		
		
		uI ui=(viewPlatform.uI) this.uI();
		JPanel buy=this.buy();
		
		
		if(ui.isUp){
			
		}
		
		
		
		if(isCandleGraph){
			if(((CandleStick)graph).isUpDate==true){
				System.out.println("bene");
				isUpDateCtr=true;
			}
			else{
				isUpDateCtr=false;
			}
		}
		else{
			if(((DynamicLinearStick)graph).isUpDate==true){
				System.out.println("bene");
				isUpDateCtr=true;
			}
			else{
				isUpDateCtr=false;
			}
		}
		
		
		
		//graph.pack();
		ui.setSize(400,1400);
		
		//assegno l'asset all'ui per prendere il punto di puntata nel grafico
		//((viewPlatform.uI) ui).setAssetValues(asset);
		
		
		//______________________________________________________________________
		
		
		ui.pack();
		//ui.setVisible(true);
		buy.setVisible(true);
		
		
		
		//buy.setSize(1000,1000);
		//buy.pack()
		//this.setSize(1800, 1800);
		
		
		
		
		canvas.add(buy,BorderLayout.WEST);
		canvas.add(graph.getContentPane(),BorderLayout.CENTER);
		canvas.add(ui.getContentPane(),BorderLayout.EAST);
		
		
		//this.pack();
		this.add(canvas);
		//this.setContentPane(canvas);
		this.setVisible(true);
		this.pack();
		
		
		
		/*cambio grafico
		//Pause for 2 seconds
       
        
        
        System.out.println("cambio grafico");
        //this.removeAll();
        this.getContentPane();
        canvas.remove(2);
        //canvas.removeContainerListener((ContainerListener) graph.getContentPane());
        graph=this.drawGraph(true);
        canvas.add(graph.getContentPane(),BorderLayout.CENTER);
        this.add(canvas);
        this.setVisible(true);
		//this.pack();
		/**/
		
		
	}
	
	
		
	@Override
	public JFrame drawGraph(boolean isCandleStick) {
		// TODO Auto-generated method stub
		//asset=new ModelPlatformImpl().dataFeed();
				
				
				
		//return new CandleStick("MSFT",asset);
		
		
		//System.out.println("ERRORE?");
		this.isCandleGraph=isCandleStick;
		return this.isCandleGraph? new CandleStick("MSFT",(OHLCSeriesCollection) asset) : new DynamicLinearStick("MSFT",(XYDataset) asset);
			
	}
			
			
	@Override
	public JFrame uI() {
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
	public void setValueGraph(AbstractSeriesDataset asset ) {
		// TODO Auto-generated method stub
		this.asset=asset;
			
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}



	@Override
	public void refreshGraph(AbstractSeriesDataset ass) {
		// TODO Auto-generated method stub
		//this.asset=ass;
			 
	}
			
			
			
			
}