package viewPlatform;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Timer;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import modelPlatform.ValuesAsset;

/**
 * An example to show how we can create a dynamic chart.
*/
public class DynamicCandleStick extends ApplicationFrame implements ActionListener {

	/*_______________________________FIELDS CandleStick_______________________________________________________________*/
	int nCandele=5;
	ValuesAsset asset=null;
	/*______________________________________________________________________________________________*/
	

	//ValuesAsset asset=null;
	boolean isCandleGraph=true;
	
	
    /** The time series data. */
    private TimeSeries series;

    /** The most recent value added. */
    private double lastValue = 100.0;

    /** Timer to refresh graph after every 1/4th of a second */
    private Timer timer = new Timer(250, this);

    /**
     * Constructs a new dynamic chart application.
     *
     * @param title  the frame title.
     */
    public DynamicCandleStick(final String title, ValuesAsset asset) {
    	super(title);
		        
    	if(!this.isCandleGraph){
		        this.series = new TimeSeries("Random Data", Millisecond.class);
		
		        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
		        final JFreeChart chart = createChart(dataset);
		
		        timer.setInitialDelay(1000);
		
		        //Sets background color of chart
		        chart.setBackgroundPaint(Color.LIGHT_GRAY);
		
		        //Created JPanel to show graph on screen
		        final JPanel content = new JPanel(new BorderLayout());
		
		        //Created Chartpanel for chart area
		        final ChartPanel chartPanel = new ChartPanel(chart);
		
		        //Added chartpanel to main panel
		        content.add(chartPanel);
		
		        //Sets the size of whole window (JPanel)
		        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		
		        //Puts the whole content on a Frame
		        setContentPane(content);
		
		        timer.start();
    	}
    	else{
    		  
	    	  this.asset=asset;

	    	  final DefaultHighLowDataset dataset = createDataset();
	    	  final JFreeChart chart = createChart(dataset);
	    	  final ChartPanel chartPanel = new ChartPanel(chart);
	    	  chartPanel.setPreferredSize(new java.awt.Dimension(600, 350));
	    	  setContentPane(chartPanel);
    		
    	}
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    
    
    
    /*____________________________________________________________ per CandleStick ____________________________________________________________*/
   
    
    
    private DefaultHighLowDataset createDataset() {

	  	  int serice = this.nCandele;
	
	  	  Date[] date = new Date[serice];
	  	  double[] high = new double[serice];
	  	  double[] low = new double[serice];
	  	  double[] open = new double[serice];
	  	  double[] close = new double[serice];
	  	  double[] volume = new double[serice];
	
	
	
	  	  Calendar calendar = Calendar.getInstance();
	  	  calendar.set(2008, 5, 1);
	  
	  	 
	  	 for (int i = 0; i < serice; i++) {
		    	  date[i] = createData(2008, 8, i + 1);
		    	  high[i] = asset.getHigh();
		    	  low[i] = asset.getLow();
		    	  open[i] = asset.getOpen();
		    	  close[i] = asset.getClose();
		    	  volume[i] = 5;
	  	  }

  	 		DefaultHighLowDataset data = new DefaultHighLowDataset(
  			  "", date, high, low, open, close, volume);
  	  		return data;
  	  }
    
    
    
    
    
    private Date createData(int year, int month, int date) {
  	  Calendar calendar = Calendar.getInstance();
  	  calendar.set(year, month - 1, date);
  	  return calendar.getTime();
	  }
    /*____________________________________________________________________________________________________________________________________________________________________________________*/
    
    private JFreeChart createChart(final XYDataset dataset) {
        
    	final JFreeChart result;
    	if(!this.isCandleGraph){
	    	result = ChartFactory.createTimeSeriesChart(
	            "Dynamic Line And TimeSeries Chart",
	            "Time",
	            "Value",
	            dataset,
	            true,
	            true,
	            false
	        );
	
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
	        yaxis.setRange(0.0, 300.0);
    	}
    	else{
    		
    		
    		result=ChartFactory.createCandlestickChart(
  		    	  "Candlestick Demo", "Time", "Price", (OHLCDataset) dataset, false);
    	}

        return result;
    }
    /**
     * Generates an random entry for a particular call made by time for every 1/4th of a second.
     *
     * @param e  the action event.
     */
    public void actionPerformed(final ActionEvent e) {

        final double factor = 0.9 + 0.2*Math.random();
        this.lastValue = this.lastValue * factor;

        final Millisecond now = new Millisecond();
        this.series.add(new Millisecond(), this.lastValue);

        System.out.println("Current Time in Milliseconds = " + now.toString()+", Current Value : "+this.lastValue);
    }

    /**
     * Starting point for the dynamic graph application.
     *
     * @param args  ignored.
     */
   
}  