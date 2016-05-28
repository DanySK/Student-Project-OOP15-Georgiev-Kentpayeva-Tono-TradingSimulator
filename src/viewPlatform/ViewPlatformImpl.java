package viewPlatform;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.XYDataset;

import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;

public class ViewPlatformImpl extends JFrame implements ViewPlatform{
	
	private int durataDiGioco;
	
	public boolean isUpDateCtr=false;

	public boolean isIndicatoreReady=false;
	
	public boolean isUP=false;
	public boolean isDown=false;
	
	
	public JButton up=null,down=null;
	
	boolean isCandleGraph=false;
	
	
	TimeSeriesCollection dataset=null;
	OHLCSeriesCollection datasetCandle=null;
	
	//elementi grafici
	JPanel ui=null;
	JPanel buy=null;
	
	
	//TimeSeriesCollection dataset;
	//public boolean isCandleGraph;//=false;
	
	
	
	//public JFrame graph=null;//this.drawGraph(isCandleGraph);
	
	public GraficiCombinati graficoALinee= new GraficiCombinati("MSFT");
	public CandleStick graficoACandele=new CandleStick("MSFT");
	
	JPanel canvas =new JPanel();
	JPanel canvas2 =new JPanel();
		
	public ViewPlatformImpl(){
		
		super("Trading Platoform");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.isCandleGraph=isCandleGraph;
        this.dataset=new TimeSeriesCollection();
        
		//graph=obj;//this.drawGraph();
		
		
		
		
		
		 ui=(JPanel) this.uI();
		 buy=this.buy();
		
		
		//cerco il bottone
		//System.out.println("qua----->"+((JButton)((JPanel)((JPanel)ui.getComponent(2)).getComponent(0)).getComponent(0)).toString());
		
		
		//BOTTON DOWN
		down=((JButton)((JPanel)ui.getComponent(2)).getComponent(0));
		((JButton)((JPanel)ui.getComponent(2)).getComponent(0)).addActionListener(e->{
			System.out.println("premuto DOWN");
			this.AvvioGiocata();
			
		});
		
		
		//BOTTON UP
		up=((JButton)((JPanel)ui.getComponent(1)).getComponent(0));
		((JButton)((JPanel)ui.getComponent(1)).getComponent(0)).addActionListener(e->{
			System.out.println("premuto DOWN");
			this.AvvioGiocata();
		});
				
		
		
		
		graficoALinee.pack();
		graficoACandele.pack();
		
		ui.setSize(400,1400);
		
		//assegno l'asset all'ui per prendere il punto di puntata nel grafico
		//((viewPlatform.uI) ui).setAssetValues(asset);
		
		
		//______________________________________________________________________
		
		
		buy.setVisible(true);
		
		
		canvas.add(buy,BorderLayout.WEST);
		canvas.add(graficoALinee.getContentPane(),BorderLayout.CENTER);
		canvas2.add(graficoACandele.getContentPane(),BorderLayout.SOUTH);
		
		canvas2.add(ui,BorderLayout.EAST);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		canvas.setSize(screenSize.width,screenSize.height);

		this.graficoALinee.getContentPane().setVisible(false);
		
		
		//this.pack();
		this.add(canvas,BorderLayout.WEST);
		this.add(canvas2,BorderLayout.SOUTH);
		
		//this.setContentPane(canvas);
		this.setVisible(true);
		this.pack();
		
	/*
			
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
		
		//_________________
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//cambio grafico
		this.isCandleGraph=!this.isCandleGraph;
		graph=this.changeGraph();
		//__________________
		
		
		*/
	}
	
	public void AvvioGiocata(){
		this.isUP=true;
		graficoALinee.setDurataDiGioco(durataDiGioco);
		graficoALinee.setIsUP(true);
				
	}
		
	@Override
	public JFrame drawGraph() {
		// TODO Auto-generated method stub
		
		//this.isCandleGraph=isCandleStick;
		//System.out.println("ERRORE?"+this.isCandleGraph);
		
		return this.isCandleGraph? new CandleStick("MSFT") : new GraficiCombinati("MSFT");
		
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
	
	public Boolean getIsDown(){
		return this.isDown;
	}
	
	
	public void setData(TimeSeries serie)
	{
		((TimeSeriesCollection) this.dataset).addSeries(serie);
		graficoALinee.setData(serie);
		
	}
		
	public void setDataCandle(OHLCSeries serie)
	{
		this.datasetCandle.addSeries(serie);
		
	}
	
	//getter e setter per scegliere il tipo di grafico
	public boolean getCandleStick(){
		return this.isCandleGraph;
	}
	
	public void setCandleStick(boolean isCandleGraph){
		 this.isCandleGraph=isCandleGraph;
	}
	
	//cambio grafico
	public void changeGraph(boolean changeToCandle){
		if(changeToCandle){
			this.graficoACandele.getContentPane().setVisible(true);
			this.graficoALinee.getContentPane().setVisible(false);
			
		}
		else{
			this.graficoACandele.getContentPane().setVisible(false);
			this.graficoALinee.getContentPane().setVisible(true);
		}
		//return this.isCandleGraph? new CandleStick("MSFT") : new GraficiCombinati("MSFT");
		
	}
		
	//setto il dataset
	public void setDataSet(OHLCSeries dataset){
		this.graficoACandele.setSeries(dataset);
	}


	public void setDurataDiGioco(int durataDiGioco){
		this.durataDiGioco=durataDiGioco;
	}



	//restituisco al controller la parte grafica che regola le combobox per cambiare grafico
	public buy getBuy(){
		return (buy)this.buy;
	}
	
	
			
}