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

import tecnicalIndicatorView.CalendarioEconomico;


/**
 * An example to show how we can create a dynamic chart.
*/
public class CandleStick extends ApplicationFrame implements ActionListener {

	
	/*_______________________________FIELDS _______________________________________________________________*/
	
	private static final String[] INDICATORI = {"Medie Mobili","Medie Mobili Esponenziali","MACD Diff","MACD Single","Stocastico", "Calendario Economico","RSI","Bande di Bollinger"};
	
	
	OHLCSeriesCollection dataset=null;
	/*______________________________________________________________________________________________*/
	

	TimeSeriesCollection mediaMobilSemplice;
	TimeSeriesCollection  mediaMobilEsponenziale;	
		
		
	TimeSeriesCollection  CalcoloRSI;
		
	//Bande Di Boolinger
	TimeSeriesCollection  bandaDiBoolingerSup;
	TimeSeriesCollection  bandaDiBoolingerInf;
		
	//MACD
	TimeSeriesCollection mACDDIff;
	TimeSeriesCollection mACDSingle;	
	TimeSeriesCollection  stocastico;
	
	
	TimeSeriesCollection dataset1=null;
    TimeSeriesCollection dataset2=null;
    
  //elementi grafici
    XYPlot subplot2,subPlotMEsp,	subPlotMACDDiff,	subPlottMACDSingle,	subPlotStocastico;
    CombinedDomainXYPlot plotComb;
    
    
   
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
		

    	//indicatori tecnici
		final JFreeChart resultMedia;
		resultMedia = ChartFactory.createTimeSeriesChart(
            "Dynamic Line And TimeSeries Chart",
            "Time",
            "Value",
            (XYDataset) this.mediaMobilSemplice,
            true,
            true,
            false
        );
		
		final JFreeChart resultMEsp;
		resultMEsp = ChartFactory.createTimeSeriesChart(
            "Dynamic Line And TimeSeries Chart",
            "Time",
            "Value",
            (XYDataset) this.mediaMobilEsponenziale,
            true,
            true,
            false
        );
		
		
		final JFreeChart resultMACDDiff;
		resultMACDDiff = ChartFactory.createTimeSeriesChart(
            "Dynamic Line And TimeSeries Chart",
            "Time",
            "Value",
            (XYDataset) this.mACDDIff,
            true,
            true,
            false
        );
		
		final JFreeChart resultMACDSingle;
		resultMACDSingle = ChartFactory.createTimeSeriesChart(
            "Dynamic Line And TimeSeries Chart",
            "Time",
            "Value",
            (XYDataset) this.mACDSingle,
            true,
            true,
            false
        );
		
		final JFreeChart resultStocastico;
		resultStocastico = ChartFactory.createTimeSeriesChart(
            "Dynamic Line And TimeSeries Chart",
            "Time",
            "Value",
            (XYDataset) this.stocastico,
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
	        
	        
	        //INDICATORI TECNICI 
			
			subplot2 = resultMedia.getXYPlot();// new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
			subplot2.setDomainGridlinesVisible(true);
		
			
			subPlotMEsp = resultMEsp.getXYPlot();// new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
			subPlotMEsp.setDomainGridlinesVisible(true);
		
			subPlotMACDDiff = resultMACDDiff.getXYPlot();// new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
			subPlotMACDDiff.setDomainGridlinesVisible(true);
		
			subPlottMACDSingle = resultMACDSingle.getXYPlot();// new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
			subPlottMACDSingle.setDomainGridlinesVisible(true);
		
			subPlotStocastico = resultStocastico.getXYPlot();// new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
			subPlotStocastico.setDomainGridlinesVisible(true);
		
			
    	
	        
	        
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
		
		
	
		return  (XYDataset) dataset;
	}
	
	
	

    public void setSeries(OHLCSeries serie){
    	this.dataset.addSeries(serie);
    }
    
    public void insMediaSeplice(TimeSeries serie)
	{
		this.mediaMobilSemplice.addSeries(serie);
		
	}
	
	public void insEsp(TimeSeries serie)
	{
		this.mediaMobilEsponenziale.addSeries(serie);
		
	}
	
	public void insRsi(TimeSeries serie)
	{
		this.CalcoloRSI.addSeries(serie);		
	}
	
	public void insBolingerSup(TimeSeries serie)
	{
		this.bandaDiBoolingerSup.addSeries(serie);		
	}
	
	public void insBolingerInf(TimeSeries serie)
	{
		this.bandaDiBoolingerInf.addSeries(serie);		
	}
	
	public void insMacdDiff(TimeSeries serie)
	{
		this.mACDDIff.addSeries(serie);		
	}
	
	public void insMacdSingle(TimeSeries serie)
	{
		this.mACDSingle.addSeries(serie);		
	}	
	
	public void insStocastico(TimeSeries serie)
	{
		this.stocastico.addSeries(serie);
		
	}
	
	//seleziono il subplot da aggiungere al grafico
	public void addSubPlot(String choose){
		if(choose==this.INDICATORI[0])
			plotComb.add(this.subplot2, 2);
		if(choose==this.INDICATORI[1])
			plotComb.add(this.subPlotMEsp, 2);
		if(choose==this.INDICATORI[2])
			plotComb.add(this.subPlotMACDDiff, 2);
		if(choose==this.INDICATORI[3])
			plotComb.add(this.subPlottMACDSingle, 2);
		if(choose==this.INDICATORI[4])
			plotComb.add(this.subPlotStocastico, 2);
		if(choose==this.INDICATORI[5])
			new CalendarioEconomico().main(new String[]{"s"});;
	}
	
	
   
}  