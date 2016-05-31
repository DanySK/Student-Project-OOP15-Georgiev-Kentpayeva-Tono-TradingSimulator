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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.Series;
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
public class CandleStick extends ApplicationFrame implements ActionListener {

	/*_______________________________FIELDS CandleStick_______________________________________________________________*/
	OHLCSeriesCollection dataset=null;
	/*______________________________________________________________________________________________*/
	

	public boolean isUpDate=true;
	
	CombinedDomainXYPlot plotComb;
	
	TimeSeriesCollection dataset1=null;
    TimeSeriesCollection dataset2=null;
    
    
   
    //private OHLCSeries series=new OHLCSeries("ohlcSeries");
	
   
    /** Timer to refresh graph after every 1/4th of a second */
    //private Timer timer = new Timer(10, this); //mezzo minuto di intervallo tra una candelae l'altra

    /**
     * Constructs a new dynamic chart application.
     *
     * @param title  the frame title.
     */
    public CandleStick(final String title) {
    		
    		super(title);
    		    
    	
		      //indicatori tecnici
	    	  dataset2 =  new TimeSeriesCollection(this.series2);
			  //candele  
	    	  dataset=new OHLCSeriesCollection();
		    
    		 
	    	  final JFreeChart chart = createChart(dataset);
	    	  final ChartPanel chartPanel = new ChartPanel(chart);
	    	  chartPanel.setPreferredSize(new java.awt.Dimension(600, 350));
	    	  setContentPane(chartPanel);

    }

     
    
    
    
    private JFreeChart createChart(final OHLCDataset dataset) {
        
    	
    	/*indicatore tecnico
    	final JFreeChart result1;
		result1 = ChartFactory.createTimeSeriesChart(
            "Dynamic Line And TimeSeries Chart",
            "Time",
            "Value",
            (XYDataset) this.dataset2,
            true,
	            true,
            false
        );
		
		
		XYPlot subplot1 = result1.getXYPlot();//new CategoryPlot(dataset2, null, rangeAxis2, renderer2);
		subplot1.setDomainGridlinesVisible(true);
		ValueAxis valori=subplot1.getDomainAxis();
		valori.setAutoRange(true);
	    valori.setFixedAutoRange(60000.0);  // 60 seconds
	    valori= subplot1.getRangeAxis();

		
		dataset1 = (TimeSeriesCollection) createDataset1();
		NumberAxis rangeAxis1 = new NumberAxis("Value");
		rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
		renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		*/

		final JFreeChart result2;
		result2 = ChartFactory.createTimeSeriesChart(
            "Dynamic Line And TimeSeries Chart",
            "Time",
            "Value",
            this.dataset,
            true,
            true,
            false
        );
    	
    	XYPlot subplot2 = result2.getXYPlot();// new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
		subplot2.setDomainGridlinesVisible(true);
	
		
		//
    	
    	
    	final JFreeChart result;
    	result=ChartFactory.createCandlestickChart( "Candlestick Demo", "Time", "Price", (OHLCDataset) dataset, false);
    	
	        final XYPlot plotCandle = result.getXYPlot();
	
	        plotCandle.setBackgroundPaint(new Color(0xffffe0));
	        plotCandle.setDomainGridlinesVisible(true);
	        plotCandle.setDomainGridlinePaint(Color.lightGray);
	        plotCandle.setRangeGridlinesVisible(true);
	        plotCandle.setRangeGridlinePaint(Color.lightGray);
	
	        
	        ValueAxis xaxis = plotCandle.getDomainAxis();
	        xaxis.setAutoRange(true);
	
	        //Domain axis would show data of 60 seconds for a time
	        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
	        xaxis.setVerticalTickLabels(true);
	
	        ValueAxis yaxis = plotCandle.getRangeAxis();
	        yaxis.setAutoRange(true);
	        
	        NumberAxis axis= (NumberAxis) plotCandle.getRangeAxis();
	        axis.setAutoRangeIncludesZero(false);
	        
    	
	        
	        
	        plotComb = new CombinedDomainXYPlot(new NumberAxis("Domain"));
			
	        ValueAxis axis1 = plotComb.getDomainAxis();
	        axis1.setAutoRange(true);
	        axis1.setFixedAutoRange(60000.0);  // 60 seconds
	        axis1 = plotComb.getRangeAxis();
	        plotComb.setGap(10.0);
			
	        JFreeChart resultF = new JFreeChart("Combined Domain Category Plot Demo",plotComb);
			
	        plotComb.add(plotCandle,2);
	        plotComb.add(subplot2,2);
	        
	        //plotComb.add(plot,2);
	        
        return resultF;
    }
    /**
     * Generates an random entry for a particular call made by time for every 1/4th of a second.
     *
     * @param e  the action event.
     */
    
    int n=0;
    private double lastValue = 100.0;
    private double lastValue2 = 100.0;

    private TimeSeries series;
    private TimeSeries series2;

    private TimeSeries series3;

    
    public void actionPerformed(final ActionEvent e) {

    	n++;
    	
    	
        final double factor = 0.9 + 0.2*Math.random();
        this.lastValue2 = this.lastValue2 * factor;
        
        double giocata=0;
       
        
        
        final Millisecond now = new Millisecond();
        this.series.add(new Millisecond(), giocata);//this.lastValue);
        this.series2.add(new Millisecond(), this.lastValue2);

        this.series3.add(new Millisecond(), this.lastValue);//giocata);


    	
       
    }





	private XYDataset createDataset1() {
		// TODO Auto-generated method stub
		
		Series asset= new TimeSeries("EUR/USD");
		
		Series asset2= new TimeSeries("EUR");
		
		Series asset3= new TimeSeries("EURo");
		
		
		AbstractSeriesDataset dataset=new TimeSeriesCollection();
		
		((TimeSeries) asset).add(new Millisecond(),10);
		((TimeSeries) asset2).add(new Millisecond(),20);
		
		((TimeSeries) asset3).add(new Millisecond(),30);
		
		
		this.series.add(new Millisecond(),10);
		this.series2.add(new Millisecond(),10);
		
		this.series3.add(new Millisecond(),10);
		
		((TimeSeriesCollection) dataset).addSeries(this.series);//(TimeSeries) asset);
		
		
		((TimeSeriesCollection) dataset).addSeries(this.series2);//(TimeSeries) asset2);
		
		
		((TimeSeriesCollection) dataset).addSeries(this.series3);//(TimeSeries) asset2);
		
		/*_______________________________________________________________________

		secondo=true;
        this.series2 = new TimeSeries("Random Data", Millisecond.class);

        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series2);
        //final JFreeChart chart = createChart(dataset);
        */
	
	
		return  (XYDataset) dataset;
	}
	
	
	

    public void setSeries(OHLCSeries serie){
    	this.dataset.addSeries(serie);
    }
   
}  