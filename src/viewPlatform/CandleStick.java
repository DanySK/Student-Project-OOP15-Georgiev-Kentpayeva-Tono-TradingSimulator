package viewPlatform;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

import IndicatoriTecniciModel.EconomicCalendar;
import tecnicalIndicatorView.CalendarioEconomico;

public class CandleStick extends ApplicationFrame {	
	
	private static final long serialVersionUID = 1L;
	/*_______________________________FIELDS _______________________________________________________________*/
	
	private static final String[] INDICATORI = {"Medie Mobili","Medie Mobili Esponenziali",
			"MACD","Bande di Boolinger","Stocastico", "Calendario Economico","nessuno"};
	//per rappresentare il calendario economico       
	CalendarioEconomico cal=null;
	OHLCSeriesCollection dataset;
	
	//dataset degli indicatori
	TimeSeriesCollection mediaMobilSemplice,  mediaMobilEsponenziale,  rsi, 
					bandeDiBoolinger,macd,stocastico;	    
	
	//elementi grafici
	
	XYPlot subplotMedia, subPlotMEsp,	subPlotMACDDiff,	subPlotBoolinger,	
				subPlotStocastico;
	
	int n=0,intervalloDiGiocata=0;
	TimeSeries serieMedia, serieMEsp, serieMACDDiff,serieMACDSingle,
	 serieStocastico,serieRSI,serieBoolingerSup,serieBoolingerInf,mACDDIff,mACDSingle;
	
	TimeSeriesCollection dataset1=null;
    TimeSeriesCollection dataset2=null;
    

    //elementi grafici
    CombinedDomainXYPlot plotComb;
    
    public CandleStick(final String title) {
    		
    	super(title);     	
		//candele  
	    dataset=new OHLCSeriesCollection();
	    //serie indicatori
	  	mediaMobilSemplice=  new TimeSeriesCollection();
	  	mediaMobilEsponenziale=  new TimeSeriesCollection();   	
	  	rsi=  new TimeSeriesCollection();
	  	//Bande Di Boolinger
	  	bandeDiBoolinger=  new TimeSeriesCollection();
	  	//bandaDiBoolingerInf=  new TimeSeriesCollection();   	
	    //MACD
	    macd=  new TimeSeriesCollection();
	    //mACDSingle=  new TimeSeriesCollection();
	    stocastico=  new TimeSeriesCollection();

	    final JFreeChart chart = createChart(dataset);
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
	    setContentPane(chartPanel);
    }

    private JFreeChart createChart(final OHLCDataset dataset) {
    	
    	//creo le serie degli indicatori tecnici da graficare
    	this.serieMedia=new TimeSeries("");//serie;
		this.serieMEsp=new TimeSeries("");//=serie;
		this.serieMACDDiff=new TimeSeries("");
		this.serieMACDSingle=new TimeSeries("");
		this.serieBoolingerInf=new TimeSeries("");
		this.serieBoolingerSup=new TimeSeries("");
		this.serieStocastico=new TimeSeries("");
		this.serieMedia=new TimeSeries("");
		this.serieMEsp=new TimeSeries("");
		this.serieRSI=new TimeSeries("");
		
    	//riempio i dataset con le serie
		this.mediaMobilSemplice.addSeries(this.serieMedia);     
		this.mediaMobilEsponenziale.addSeries(this.serieMEsp);   
		this.rsi.addSeries(this.serieRSI);    
		this.bandeDiBoolinger.addSeries(this.serieBoolingerSup);   
		this.bandeDiBoolinger.addSeries(this.serieBoolingerInf);   
		this.macd.addSeries(this.serieMACDDiff);   		
		this.macd.addSeries(this.serieMACDSingle);
		this.stocastico.addSeries(this.serieStocastico);   
		
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
            (XYDataset) this.macd,
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
		
		final JFreeChart resultBoolinger;
		resultBoolinger = ChartFactory.createTimeSeriesChart(
            "Dynamic Line And TimeSeries Chart",
            "Time",
            "Value",
            (XYDataset) this.bandeDiBoolinger,
            true,
            true,
            false
        );
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
	    subplotMedia = resultMedia.getXYPlot();
		subplotMedia.setDomainGridlinesVisible(true);
		subplotMedia.setDomainGridlinesVisible(true);
		ValueAxis valoriM=subplotMedia.getDomainAxis();
		valoriM.setAutoRange(true);
		valoriM.setFixedAutoRange(60000.0);  // 60 seconds
	    valoriM= subplotMedia.getRangeAxis();
	    NumberAxis rangeAxisM = new NumberAxis("media");
		rangeAxisM.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		//LineAndShapeRenderer rendererM = new LineAndShapeRenderer();
		//rendererM.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		subPlotMEsp = resultMEsp.getXYPlot();
		subPlotMEsp.setDomainGridlinesVisible(true);
		subPlotMACDDiff = resultMACDDiff.getXYPlot();
		subPlotMACDDiff.setDomainGridlinesVisible(true);
		
		subPlotBoolinger = resultBoolinger.getXYPlot();
		subPlotBoolinger.setDomainGridlinesVisible(true);
		subPlotBoolinger.setDomainGridlinesVisible(true);
		ValueAxis valoriB=plotCandle.getDomainAxis();
		valoriB.setAutoRange(true);
		valoriB.setFixedAutoRange(60000.0);  // 60 seconds
	    valoriB= subPlotBoolinger.getRangeAxis();
	    NumberAxis rangeAxisB = new NumberAxis("BOOLINGER");
		rangeAxisB.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		LineAndShapeRenderer rendererB = new LineAndShapeRenderer();
		rendererB.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		
	    
		subPlotStocastico = resultStocastico.getXYPlot();
		subPlotStocastico.setDomainGridlinesVisible(true);	
		
		plotComb = new CombinedDomainXYPlot(new NumberAxis("Domain"));			
	    ValueAxis axis1 = plotComb.getDomainAxis();
	    axis1.setAutoRange(true);
	    axis1.setFixedAutoRange(60000.0);  // 60 seconds
	    axis1 = plotComb.getRangeAxis();
	    plotComb.setGap(10.0);			
	    JFreeChart resultF = new JFreeChart("EUR/USD",plotComb);			
	    //aggiungo il grafico a candele al contenitore di grafici
	    plotComb.add(plotCandle,2);	        
	    return resultF;
    }
    
    
    public void setSeries(OHLCSeries serie){
    	this.dataset.addSeries(serie);
    }
    
  //inserisco le serie per graficare gli indicatori tecnici
  	int i1=0,i2=0,i3=0,i4=0,iM;  	
  	
  	public void insMediaSeplice(double val){
  		this.serieMedia.add(new Millisecond(),val);
  	}
  	
  	public void insEsp(double val){
  		//this.mediaMobilEsponenziale.addSeries(serie);	
  		this.serieMEsp.add(new Millisecond(),val);
  		
  	}
  	
  	public void insRsi(double val){//TimeSeries serie){
  		//this.rsi.addSeries(serie);
  		this.serieRSI.add(new Millisecond(),val);
  	}
  		
  	public void insBolingerSup(double val){//TimeSeries serie){
  		//this.bandeDiBoolinger.addSeries(serie);	
  		
  		i1++;
  		if(i1<5){
  			this.serieBoolingerSup.add(new Millisecond(),2.3);
  			
  		}
  		else
  		this.serieBoolingerSup.add(new Millisecond(),val);
  		
  	}
  	
  	public void insBolingerInf(double val){//TimeSeries serie){
  		//this.bandeDiBoolinger.addSeries(serie);	
  		i1++;
  		if(i1<5){
  			this.serieBoolingerInf.add(new Millisecond(),2.3);
  			
  		}
  		else
  		this.serieBoolingerInf.add(new Millisecond(),val);
  		
  	}
  	
  	public void insMacdDiff(double val){//TimeSeries serie){
  		//this.mACDDIff.addSeries(serie);	
  		//this.serie2.add(new Millisecond(),105.0);
  		//this.serie.add(new Millisecond(),58.0);
  		this.serieMACDDiff.add(new Millisecond(),val);
  		
  	}
  	
  	public void insMacdSingle(double val){//TimeSeries serie){
  		//this.mACDSingle.addSeries(serie);	
  		//this.bandeDiBoolinger.addSeries(serie);
  		this.serieMACDSingle.add(new Millisecond(),val);
  		
  		
  	}	
  	public void insStocastico(double val){//TimeSeries serie){
  		//this.stocastico.addSeries(serie);	
  		this.serieStocastico.add(new Millisecond(),val);			
  		
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
			plotComb.add(this.subPlotBoolinger, 2);
		if(choose==CandleStick.INDICATORI[4])
			plotComb.add(this.subPlotStocastico, 2);
		if(choose==CandleStick.INDICATORI[5]) {
			cal=new CalendarioEconomico();
			cal.show();
			cal.setData( new EconomicCalendar().data());
		};
		if(choose==CandleStick.INDICATORI[6]){
			this.removeSubPlot();
		}			
	}
		
	
	
	public void removeSubPlot(){		
		plotComb.remove(this.subplotMedia);
		plotComb.remove(this.subPlotMEsp);
		plotComb.remove(this.subPlotMACDDiff);
		plotComb.remove(this.subPlotBoolinger);
		plotComb.remove(this.subPlotStocastico);
	}	
	
}  