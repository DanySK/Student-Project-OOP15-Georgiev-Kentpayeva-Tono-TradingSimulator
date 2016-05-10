package viewPlatform;

import java.awt.BorderLayout;
import java.awt.event.ContainerListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.XYDataset;

import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;

public class ViewPlatformImpl extends JFrame implements ViewPlatform{
	
	public boolean isUpDateCtr=false;

	public boolean isIndicatoreReady=false;
	
	public boolean isUP=false;
	public boolean isDown=false;
	
	
	public JButton up=null;
	
	AbstractSeriesDataset dataset=null;
	//TimeSeriesCollection dataset;
	public boolean isCandleGraph;//=false;
	
	
	
	public JFrame graph=null;//this.drawGraph(isCandleGraph);
	
	
	public ViewPlatformImpl(boolean isCandleGraph){
		
		super("Trading Platoform");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.isCandleGraph=isCandleGraph;
        this.dataset=new TimeSeriesCollection();
        JPanel canvas =new JPanel();
		
		graph=this.drawGraph(isCandleGraph);
		
		JPanel ui=(JPanel) this.uI();
		JPanel buy=this.buy();
		
		
		//cerco il bottone
		//System.out.println("qua----->"+((JButton)((JPanel)((JPanel)ui.getComponent(2)).getComponent(0)).getComponent(0)).toString());
		
		
		//BOTTON DOWN
		up=((JButton)((JPanel)ui.getComponent(2)).getComponent(0));
		((JButton)((JPanel)ui.getComponent(2)).getComponent(0)).addActionListener(e->{
			System.out.println("premuto DOWN");
			this.isUP=true;
		});
		
		
		
		
		graph.pack();
		ui.setSize(400,1400);
		
		//assegno l'asset all'ui per prendere il punto di puntata nel grafico
		//((viewPlatform.uI) ui).setAssetValues(asset);
		
		
		//______________________________________________________________________
		
		
		//ui.pack();
		//ui.setVisible(true);
		buy.setVisible(true);
		
		
		
		//buy.setSize(1000,1000);
		//buy.pack()
		//this.setSize(1800, 1800);
		
		
		
		
		canvas.add(buy,BorderLayout.WEST);
		canvas.add(graph.getContentPane(),BorderLayout.CENTER);
		canvas.add(ui,BorderLayout.EAST);
		
		
		//this.pack();
		this.add(canvas);
		//this.setContentPane(canvas);
		this.setVisible(true);
		this.pack();
		
		
		
		
		
			
			
			if(isCandleGraph){
				if(((CandleStick)graph).isUpDate==true){
					//System.out.println("bene");
					isUpDateCtr=true;
				}
				else{
					isUpDateCtr=false;
				}
			}
			else{
				if(((GraficiCombinati)graph).isUpDate==true){
					//System.out.println("bene");
					isUpDateCtr=true;
				}
				else{
					isUpDateCtr=false;
				}
			}
		
		
	}
	
	
		
	@Override
	public JFrame drawGraph(boolean isCandleStick) {
		// TODO Auto-generated method stub
		
		this.isCandleGraph=false;//isCandleStick;
		System.out.println("ERRORE?"+this.isCandleGraph);
		
		return this.isCandleGraph? new CandleStick("MSFT",(OHLCSeriesCollection) dataset) : new GraficiCombinati("MSFT");
	}
			
			
	@Override
	public JPanel uI() {
		// TODO Auto-generated method stub
		uI uitmp=new uI();
		
		//while(!uitmp.isUp){
			if(uitmp.isUp){
				System.out.println("pushed UP in view2");
				
				this.isUP=true;
				uitmp.isUp=false;
			}
		//}
		
		return (JPanel) uitmp.getContentPane();
				
				
	}

			
	@Override
	public JPanel buy() {
		// TODO Auto-generated method stub
		return new buy();
	}
			

			
	//_______________________________metodi per il controller____________________________________
	@Override
	public void setValueGraph(TimeSeriesCollection dataset ) {
		// TODO Auto-generated method stub
		this.dataset=dataset;
			
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
	
	
	public JButton getButtonUp(){
		return up;
	}
	
	public Boolean getIsUp(){
		return this.isUP;
	}
	
	/*________MATERIALE THREAD DEL PROF ___________________
	
	public void updateCounter(final int value) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                	//ViewPlatformImpl.this.drawGraph(false);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }*/
	
	
	
	public void setData(TimeSeries serie)
	{
		((TimeSeriesCollection) this.dataset).addSeries(serie);
		
	}
			
			
	
			
}