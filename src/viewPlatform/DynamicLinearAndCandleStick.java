package viewPlatform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Timer;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
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
public class DynamicLinearAndCandleStick extends ApplicationFrame implements ActionListener {

	

	
	
    /** The time series data. */
    private TimeSeries series;
    private OHLCSeries series2;

    /** The most recent value added. */
    private double lastValue = 100.0;
    private double lastValue2 = 100.0;

    /** Timer to refresh graph after every 1/4th of a second */
    private Timer timer = new Timer(250, this);

    private Timer timer2 = new Timer(250, this);
    
    
    TimeSeriesCollection dataset=null;
    OHLCSeriesCollection dataset2=null;
    
    boolean secondo=false;

	
    
    /**
     * Constructs a new dynamic chart application.
     *
     * @param title  the frame title.
     */
    public DynamicLinearAndCandleStick(final String title, XYDataset asset) {
    	super(title);
    	
    			System.out.println("AVVIO 2 GRAFICI");
    	
    			
    			timer.setInitialDelay(1000);
		        //timer2.setInitialDelay(1000);
				
		        timer.start();
		        //timer2.start();
		        
    	        this.series = new TimeSeries("Random Data", Millisecond.class);
    	        this.series2 =  new OHLCSeries("EUR/USD");
    			
		        dataset = new TimeSeriesCollection(this.series);
		        dataset2 = new OHLCSeriesCollection();;
			       
		        final JFreeChart chart = createChart(dataset);
		        final JFreeChart chart2 = createChart(dataset2);
				
		        
		        //Sets background color of chart
		        chart.setBackgroundPaint(Color.LIGHT_GRAY);
		        chart2.setBackgroundPaint(Color.LIGHT_GRAY);
				
		        //Created JPanel to show graph on screen
		        final JPanel content = new JPanel(new BorderLayout());
		
		        //Created Chartpanel for chart area
		        final ChartPanel chartPanel = new ChartPanel(chart);
		        chartPanel.setChart(chart2);
		        //Added chartpanel to main panel
		        content.add(chartPanel);
		
		        //Sets the size of whole window (JPanel)
		        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		
		        //Puts the whole content on a Frame
		        setContentPane(content);
		
		         
		       
    	
		        
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    
    
    private JFreeChart createChart(final XYDataset dataset) {
        
    	final JFreeChart result1;
    		result1 = ChartFactory.createTimeSeriesChart(
	            "Dynamic Line And TimeSeries Chart",
	            "Time",
	            "Value",
	            this.dataset2,
	            true,
	            true,
	            false
	        );
    		
    		final JFreeChart result2;
    		result2 = ChartFactory.createTimeSeriesChart(
	            "Dynamic Line And TimeSeries Chart",
	            "Time",
	            "Value",
	            this.dataset2,
	            true,
	            true,
	            false
	        );
    		
    		
    		/*___________________________________________________________COMBINO DUE GRAFICI__________________________________________________________________________*/
			
			
			
			System.out.println("ok");
			CategoryDataset dataset1 = createDataset1();
			NumberAxis rangeAxis1 = new NumberAxis("Value");
			rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
			renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
			
			CategoryPlot subplot1 =new CategoryPlot((CategoryDataset) dataset1, null, rangeAxis1, renderer1); 
			subplot1.setDomainGridlinesVisible(true);
			
			System.out.println("ok2");
			
			XYDataset dataset2 = createDataset2();
			NumberAxis rangeAxis2 = new NumberAxis("Value");
			rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			BarRenderer renderer2 = new BarRenderer();
			renderer2.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
			
			CategoryPlot subplot2 =new CategoryPlot(dataset1, null, rangeAxis1, renderer1); 
			subplot2.setDomainGridlinesVisible(true);

			
			
			CategoryAxis domainAxis = new CategoryAxis("Category"); 
			CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis); 
			plot.setGap(10.0);
			
			
			/*CategoryAxis domainAxis = new CategoryAxis("Category");
			CombinedDomainCategoryPlot plot1 = new CombinedDomainCategoryPlot(domainAxis);
			*/
			System.out.println("ok3");
			
			plot.add(subplot1, 2);
			System.out.println("ok4");
			
			plot.add(subplot2, 1);
			
			System.out.println("ok5");
			JFreeChart result = new JFreeChart(
			"Combined Domain Category Plot Demo",
			//new Font("SansSerif", Font.BOLD, 12),
			plot//,
			//true
			);
			
			

			
			/*____________________________________________________________________________________________________________________________________________________*/
			
    		
	
	        //final XYPlot plot = result.getXYPlot();
	        
			System.out.println("ok6");
			
	        plot.setBackgroundPaint(new Color(0xffffe0));
	        plot.setDomainGridlinesVisible(true);
	        plot.setDomainGridlinePaint(Color.lightGray);
	        plot.setRangeGridlinesVisible(true);
	        plot.setRangeGridlinePaint(Color.lightGray);
	        
	        CategoryAxis xaxis = plot.getDomainAxis();
	        //((ValueAxis) xaxis).setAutoRange(true);
	        
	        /*Domain axis would show data of 60 seconds for a time
	        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
	        xaxis.setVerticalTickLabels(true);*/
	        System.out.println("ok7");
			
	        ValueAxis yaxis = plot.getRangeAxis();
	        System.out.println("ok8");
			
	        //yaxis.setRange(0.0, 300.0);
	        
	        System.out.println("ok-fine");
			
	        return result;
    }
    
    
    

	private CategoryDataset createDataset1() {
		// TODO Auto-generated method stub
		
		TimeSeries series2;

        series2 = new TimeSeries("Random Data", Millisecond.class);

        final CategoryDataset dataset = TimeSeriesCollection(series2);
        //final JFreeChart chart = createChart(dataset);
        secondo=false;
		return  dataset;
	}

	private CategoryDataset TimeSeriesCollection(TimeSeries series22) {
		// TODO Auto-generated method stub
		return null;
	}

	private XYDataset createDataset2() {
		// TODO Auto-generated method stub
		//TimeSeries series2;
		
		timer2.setInitialDelay(1000);
		timer2.start();	
		
		
		Series asset= new OHLCSeries("EUR/USD");
		AbstractSeriesDataset dataset=new OHLCSeriesCollection();
		
		((OHLCSeries) asset).add(new Millisecond(),2,3,4,5);
		((OHLCSeriesCollection) dataset).addSeries((OHLCSeries) asset);
		
		/*_______________________________________________________________________

		secondo=true;
        this.series2 = new TimeSeries("Random Data", Millisecond.class);

        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series2);
        //final JFreeChart chart = createChart(dataset);
        */
		
		return  (OHLCDataset) dataset;
	}
    
    
    /**
     * Generates an random entry for a particular call made by time for every 1/4th of a second.
     *
     * @param e  the action event.
     */
	
	
	
    public void actionPerformed(final ActionEvent e) {
    	
        final double factor = 0.9 + 0.2*Math.random();
        this.lastValue2 = this.lastValue2 * factor;
        
        if(secondo){
        	this.lastValue=6;
        }
        
        final Millisecond now = new Millisecond();
        this.series.add(new Millisecond(), this.lastValue);
        this.series2.add(new Millisecond(), 1,2,3,4);


        //System.out.println("Current Time in Milliseconds = " + now.toString()+", Current Value : "+this.lastValue);
    }

    /**
     * Starting point for the dynamic graph application.
     *
     * @param args  ignored.
     */
   
}  
