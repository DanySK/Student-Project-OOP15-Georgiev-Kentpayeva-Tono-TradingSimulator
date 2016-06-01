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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.Series;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


/**
 * An example to show how we can create a dynamic chart.
*/
public class GraficiCombinati extends ApplicationFrame implements ActionListener {

	XYPlot subplot2;

	boolean isUp=false;
	
    /** The time series data. */
    private TimeSeries series;
    private TimeSeries series2;
    MovingAverage av;

    private TimeSeries series3;

    /** The most recent value added. */
    private double lastValue = 100.0;
    private double lastValue2 = 100.0;

    /** Timer to refresh graph after every 1/4th of a second */
    private Timer timer = new Timer(250, this);

    private Timer timer2 = new Timer(250, this);
    
    
    TimeSeriesCollection dataset;
    TimeSeriesCollection dataset1=null;
    TimeSeriesCollection dataset2=null;
    TimeSeriesCollection datasetInd;
    
    //serie indicatori
    /*TimeSeries mediaMobilSemplice=null;
	TimeSeries  mediaMobilEsponenziale=null;
	
	TimeSeries  mediaMobilePonderata=null;
	
	
	TimeSeries  CalcoloRSI=null;
	
	//Bande Di Boolinger
	TimeSeries  bandaDiBoolingerSup=null;
	TimeSeries  bandaDiBoolingerInf=null;
	
	//MACD
	TimeSeries mACDDIff=null;
	TimeSeries mACDSingle=null;
	
	TimeSeries  stocastico=null;
	
	TimeSeries  serieFibonacci=null;*/
    
    boolean secondo=false;

    //elemento grafico
    CombinedDomainXYPlot plot;
    
	
    /**
     * Constructs a new dynamic chart application.
     *
     * @param title  the frame title.
     */
    public GraficiCombinati(final String title) {
    	super(title);
    	
    			System.out.println("AVVIO 2 GRAFICI");
    	
    			
    			timer.setInitialDelay(1000);
		        //timer2.setInitialDelay(1000);
				
		        timer.start();
		        //timer2.start();
		        
    	        this.series = new TimeSeries("Random Data", Millisecond.class);
    	        this.series2 = new TimeSeries("Random Data", Millisecond.class);
    			
    	        this.series3 = new TimeSeries("Random Data", Millisecond.class);
    	        
    	       /* //serie indicatori
    	        TimeSeries mediaMobilSemplice=new TimeSeries("Random Data", Millisecond.class);
    	    	TimeSeries  mediaMobilEsponenziale=new TimeSeries("Random Data", Millisecond.class);
    	    	
    	    	TimeSeries  mediaMobilePonderata=new TimeSeries("Random Data", Millisecond.class);
    	    	
    	    	
    	    	TimeSeries  CalcoloRSI=null;
    	    	
    	    	//Bande Di Boolinger
    	    	TimeSeries  bandaDiBoolingerSup=new TimeSeries("Random Data", Millisecond.class);
    	    	TimeSeries  bandaDiBoolingerInf=new TimeSeries("Random Data", Millisecond.class);
    	    	
    	    	//MACD
    	    	TimeSeries mACDDIff=new TimeSeries("Random Data", Millisecond.class);
    	    	TimeSeries mACDSingle=new TimeSeries("Random Data", Millisecond.class);
    	    	
    	    	TimeSeries  stocastico=new TimeSeries("Random Data", Millisecond.class);
    	    	
    	    	TimeSeries  serieFibonacci=new TimeSeries("Random Data", Millisecond.class);*/
    	    	
    			
    	        
		        dataset = new TimeSeriesCollection();
		        dataset2 =  new TimeSeriesCollection(this.series2);
		        datasetInd=new TimeSeriesCollection();
		        av=new MovingAverage();
			       
		        final JFreeChart chart = createChart( this.dataset);
		        final JFreeChart chart2 = createChart((XYDataset) this.datasetInd);
				
		        
		        //Sets background color of chart
		        chart.setBackgroundPaint(Color.LIGHT_GRAY);
		        chart2.setBackgroundPaint(Color.LIGHT_GRAY);
				
		        //Created JPanel to show graph on screen
		        final JPanel content = new JPanel(new BorderLayout());
		
		        //Created Chartpanel for chart area
		        final ChartPanel chartPanel = new ChartPanel(chart2);
		        chartPanel.setChart(chart2);
		        //Added chartpanel to main panel
		        content.add(chartPanel);
		
		        //Sets the size of whole window (JPanel)
		        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		
		        //Puts the whole content on a Frame
		        setContentPane(content);
		
		         
		        try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        //timer.stop();
		        
		        
    	
		        
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    
    
    //CategoryDataset dataset2 = null;//createDataset2();
	
    
    private JFreeChart createChart(final XYDataset dataset) {
        
	    	final JFreeChart result1;
			result1 = ChartFactory.createTimeSeriesChart(
	            "Dynamic Line And TimeSeries Chart",
	            "Time",
	            "Value",
	            (XYDataset) this.dataset,
	            true,
 	            true,
	            false
	        );
			
	    	
    		final JFreeChart result2;
    		result2 = ChartFactory.createTimeSeriesChart(
	            "Dynamic Line And TimeSeries Chart",
	            "Time",
	            "Value",
	            (XYDataset) this.datasetInd,
	            true,
	            true,
	            false
	        );
    		
    		
    		
    		/*___________________________________________________________COMBINO DUE GRAFICI__________________________________________________________________________*/
			
			
    		dataset2 = (TimeSeriesCollection) createDataset2();
			NumberAxis rangeAxis2 = new NumberAxis("Value");
			rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			
			
			XYPlot subplot1 = result1.getXYPlot();//new CategoryPlot(dataset2, null, rangeAxis2, renderer2);
			subplot1.setDomainGridlinesVisible(true);
			ValueAxis valori=subplot1.getDomainAxis();
			valori.setAutoRange(true);
		    valori.setFixedAutoRange(60000.0);  // 60 seconds
		    valori= subplot1.getRangeAxis();

			
			dataset1 = createDataset1();
			NumberAxis rangeAxis1 = new NumberAxis("Value");
			rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
			renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
			
			subplot2 = result2.getXYPlot();// new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
			subplot2.setDomainGridlinesVisible(true);
		
			
			
			
			
			
			plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
			
			//regolo asse y
			
			ValueAxis axis = plot.getDomainAxis();
	        axis.setAutoRange(true);
	        axis.setFixedAutoRange(60000.0);  // 60 seconds
	        axis = plot.getRangeAxis();
			plot.setGap(10.0);
			
			
			/*CategoryAxis domainAxis = new CategoryAxis("Category");
			CombinedDomainCategoryPlot plot1 = new CombinedDomainCategoryPlot(domainAxis);
			*/
			
			plot.add(subplot1, 2);
			
			//this.addSubPlot(subplot2);
			
			JFreeChart result = new JFreeChart("Combined Domain Category Plot Demo",plot);
			
			//this.addSubPlot(subplot2);
			
			//this.addSubPlot(subplot2);
			
			
			/*____________________________________________________________________________________________________________________________________________________*/
			
    		
	
	        //final XYPlot plot = result.getXYPlot();
	        
			
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
	        
	        //yaxis.setRange(0.0, 300.0);
	        
	        
	        return result;
    }
    
    
    

	private TimeSeriesCollection createDataset1() {
		// TODO Auto-generated method stub
		
		TimeSeries series2;

        series2 = new TimeSeries("Random Data", Millisecond.class);

        final TimeSeriesCollection dataset = new TimeSeriesCollection(series2);
        //final JFreeChart chart = createChart(dataset);
        secondo=false;
		return  dataset;
	}

	private XYDataset createDataset2() {
		// TODO Auto-generated method stub
		//TimeSeries series2;
		
		/*timer2.setInitialDelay(1000);
		timer2.start();	
		*/
		
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

	
	
    
    /**
     * Generates an random entry for a particular call made by time for every 1/4th of a second.
     *
     * @param e  the action event.
     */
	
	
	
	int n=0,intervalloDiGiocata=0;

	private int durataDiGioco;
	
    public void actionPerformed(final ActionEvent e) {
    	
    	n++;
    	
    	
        final double factor = 0.9 + 0.2*Math.random();
        this.lastValue2 = this.lastValue2 * factor;
        
        double giocata=0;
        
        if(intervalloDiGiocata==durataDiGioco){
        	this.isUp=false;
        }
        
        if(this.isUp){
        	intervalloDiGiocata++;
        	giocata=1000;
        	//this.isUp=false;
        }
        else{
        	
        	intervalloDiGiocata=0;
        	giocata=0;
        }
        
        if(secondo){
        	this.lastValue=6;
        }
        
        final Millisecond now = new Millisecond();
        this.series.add(new Millisecond(), giocata);//this.lastValue);
        this.series2.add(new Millisecond(), this.lastValue2);

        this.series3.add(new Millisecond(), this.lastValue);//giocata);


        //System.out.println("Current Time in Milliseconds = " + now.toString()+", Current Value : "+this.lastValue);
    }

	
	 // aggiungo serie ala dataset
	public void setData(TimeSeries serie)
	{
		this.dataset.addSeries(serie);
		
	}
	
	
	public void setDurataDiGioco(int durataDiGioco){
		this.durataDiGioco=durataDiGioco;
	}


	//___________________________________________________________________________
	
	public void addSubPlot(){
			plot.add(this.subplot2, 2);
			
	}
	public void removeSubPlot(){
		
		plot.remove(this.subplot2);
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------
	public void insSemplice(TimeSeries serie)
	{
		
		this.datasetInd.addSeries(serie);
		
		
	}
	
	/*public void insEsp(TimeSeries serie)
	{
		this.datasetInd.addSeries(serie);
		
	}
	
	public void insPonderata(TimeSeries serie)
	{
		this.datasetInd.addSeries(serie);
		
	}
	
	public void insBolingerSup(TimeSeries serie)
	{
		this.datasetInd.addSeries(serie);
		
	}
	
	public void insBolingerInf(TimeSeries serie)
	{
		this.datasetInd.addSeries(serie);
		
	}
	
	public void insMacdDiff(TimeSeries serie)
	{
		this.datasetInd.addSeries(serie);
		
	}
	
	public void insMacdSingle(TimeSeries serie)
	{
		this.datasetInd.addSeries(serie);
		
	}
	
	
	
	public void insStocastico(TimeSeries serie)
	{
		this.datasetInd.addSeries(serie);
		
	}
	*/
	
	
   
}  
