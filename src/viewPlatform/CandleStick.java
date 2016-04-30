package viewPlatform;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


/**
 * An example to show how we can create a dynamic chart.
*/
public class CandleStick extends ApplicationFrame implements ActionListener,Graph {

	/*_______________________________FIELDS CandleStick_______________________________________________________________*/
	OHLCSeriesCollection asset=null;
	/*______________________________________________________________________________________________*/
	

	public boolean isUpDate=true;
   
    //private OHLCSeries series=new OHLCSeries("ohlcSeries");
	
   
    /** Timer to refresh graph after every 1/4th of a second */
    private Timer timer = new Timer(10, this); //mezzo minuto di intervallo tra una candelae l'altra

    /**
     * Constructs a new dynamic chart application.
     *
     * @param title  the frame title.
     */
    public CandleStick(final String title, OHLCSeriesCollection asset) {
    		
    		super(title);
		        
    	
    		 // this.series = new TimeSeries("Random Data", Millisecond.class);
    		  //OHLCSeries series=new OHLCSeries("asd");
    		
    		  //final OHLCSeriesCollection dataset = new OHLCSeriesCollection();
		      //dataset.addSeries(this.series);
		        
    		 
    		  
    		  
	    	  this.asset=asset;

	    	  
	    	  timer.setInitialDelay(1000);
	  		
	    	  final JFreeChart chart = createChart(asset);
	    	  final ChartPanel chartPanel = new ChartPanel(chart);
	    	  chartPanel.setPreferredSize(new java.awt.Dimension(600, 350));
	    	  setContentPane(chartPanel);

		      timer.start();
    		
    	
    }

     
    
    
    
    private JFreeChart createChart(final XYDataset dataset) {
        
    	final JFreeChart result;
    	result=ChartFactory.createCandlestickChart( "Candlestick Demo", "Time", "Price", (OHLCDataset) dataset, false);
    	
	        final XYPlot plot = result.getXYPlot();
	
	        plot.setBackgroundPaint(new Color(0xffffe0));
	        plot.setDomainGridlinesVisible(true);
	        plot.setDomainGridlinePaint(Color.lightGray);
	        plot.setRangeGridlinesVisible(true);
	        plot.setRangeGridlinePaint(Color.lightGray);
	
	        
	        ValueAxis xaxis = plot.getDomainAxis();
	        xaxis.setAutoRange(true);
	
	        //Domain axis would show data of 60 seconds for a time
	        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
	        xaxis.setVerticalTickLabels(true);
	
	        ValueAxis yaxis = plot.getRangeAxis();
	        yaxis.setRange(0.0, 3.0);
	        
    	
    	

        return result;
    }
    /**
     * Generates an random entry for a particular call made by time for every 1/4th of a second.
     *
     * @param e  the action event.
     */
    
    int n=0;
    
    
    public void actionPerformed(final ActionEvent e) {
    	
    	this.update();
    	
        //istantaneo le prime candele per una realizzazione grafica migliore
        if(n<14){
	         
	         timer.setDelay(10);// = new Timer(10, this); //mezzo minuto di intervallo tra una candelae l'altra

        }
        
        
        else{
        	 timer.setDelay(3000);// = new Timer(3000, this); //mezzo minuto di intervallo tra una candelae l'altra

       }
        
        n++;
        
        isUpDate=false;
		
        
        //this.series.add( new Millisecond(),  open,  high,  low,  close);


       // System.out.println("Current Time in Milliseconds = " + now.toString()+", Current Value : "+this.lastValue);
    	
    }





	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		isUpDate=true;
		
	}

    /**
     * Starting point for the dynamic graph application.
     *
     * @param args  ignored.
     */
   
}  