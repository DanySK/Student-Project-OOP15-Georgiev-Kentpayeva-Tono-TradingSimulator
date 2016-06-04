package viewPlatform;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

import IndicatoriTecniciModel.EconomicCalendar;
import tecnicalIndicatorView.CalendarioEconomico;



/**
 * An example to show how we can create a dynamic chart.
*/
public class CandleStick extends ApplicationFrame {
	
	
	private static final long serialVersionUID = 1L;


	/*_______________________________FIELDS _______________________________________________________________*/
	
	private static final String[] INDICATORI = {"Medie Mobili","Medie Mobili Esponenziali","MACD Diff","MACD Single","Stocastico", "Calendario Economico","RSI","Bande di Bollinger"};
	
	CalendarioEconomico cal=null;
	
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
    XYPlot subplotMedia,subPlotMEsp,	subPlotMACDDiff,	subPlottMACDSingle,	subPlotStocastico;
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
	    	  
	    		 mediaMobilSemplice=new TimeSeriesCollection();
	    		  mediaMobilEsponenziale=new TimeSeriesCollection();	
	    			
	    			
	    		  CalcoloRSI=new TimeSeriesCollection();
	    			
	    		//Bande Di Boolinger
	    		  bandaDiBoolingerSup=new TimeSeriesCollection();
	    		 bandaDiBoolingerInf=new TimeSeriesCollection();
	    			
	    		//MACD
	    		 mACDDIff=new TimeSeriesCollection();
	    		 mACDSingle=new TimeSeriesCollection();	
	    		 stocastico=new TimeSeriesCollection();
		    
    		 
	    	  final JFreeChart chart = createChart(dataset);
	    	  final ChartPanel chartPanel = new ChartPanel(chart);
	    	  chartPanel.setPreferredSize(new java.awt.Dimension(600, 350));
	    	  setContentPane(chartPanel);

    }

     
    
    
    
    private JFreeChart createChart(final OHLCDataset dataset) {
        
    	
    	


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
	        subplotMedia = resultMedia.getXYPlot();// new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
			subplotMedia.setDomainGridlinesVisible(true);
		
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
	        
        return resultF;
    }
    /**
     * Generates an random entry for a particular call made by time for every 1/4th of a second.
     *
     * @param e  the action event.
     */
    
    int n=0;
    
    private TimeSeries series2;

    
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
		if(choose==CandleStick.INDICATORI[0])
			plotComb.add(this.subplotMedia, 2);
		if(choose==CandleStick.INDICATORI[1])
			plotComb.add(this.subPlotMEsp, 2);
		if(choose==CandleStick.INDICATORI[2])
			plotComb.add(this.subPlotMACDDiff, 2);
		if(choose==CandleStick.INDICATORI[3])
			plotComb.add(this.subPlottMACDSingle, 2);
		if(choose==CandleStick.INDICATORI[4])
			plotComb.add(this.subPlotStocastico, 2);
		
	}
	
	
   
}  