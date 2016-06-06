package viewPlatform;

import java.awt.BorderLayout;
import java.awt.Color;
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
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

import IndicatoriTecniciModel.EconomicCalendar;
import tecnicalIndicatorView.CalendarioEconomico;

public class GraficiCombinati extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	//scelte per gli indicatori tecnici
	private static final String[] INDICATORI = {"Medie Mobili","Medie Mobili Esponenziali",
						"MACD","Bande di Boolinger","Stocastico", "Calendario Economico","nessuno"};
	//per rappresentare il calendario economico       
	private CalendarioEconomico cal=null;
	private TimeSeriesCollection dataset;
    
    //dataset degli indicatori
	private TimeSeriesCollection mediaMobilSemplice,  mediaMobilEsponenziale,  rsi, 
    						bandeDiBoolinger,macd,stocastico;	    

    //elementi grafici
	private XYPlot subplotMedia, subPlotMEsp,	subPlotMACDDiff,	subPlottBoolinger,	
    		subPlotStocastico;
	private CombinedDomainXYPlot plot;
   
	private TimeSeries serieMedia, serieMEsp, serieMACDDiff,
		 serieMACDSingle, serieBoolingerInf,
		 serieBoolingerSup, serieStocastico,serieRSI;
    
    public GraficiCombinati(final String title) {
    	
    	super(title);
    	dataset = new TimeSeriesCollection();
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
    	
        final JFreeChart chart = createChart( this.dataset);
        final JFreeChart chart2 = createChart((XYDataset) this.dataset);
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
	    
    }

    private JFreeChart createChart(final XYDataset dataset) {
    	
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
		
       		//ASSET
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
    		
    		
    		
    		/*___________________________________________________________COMBINO DUE GRAFICI__________________________________________________________________________*/
			
			NumberAxis rangeAxis2 = new NumberAxis("Value");
			rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			
			XYPlot subplot1 = result1.getXYPlot();//new CategoryPlot(dataset2, null, rangeAxis2, renderer2);
			subplot1.setDomainGridlinesVisible(true);
			ValueAxis valori=subplot1.getDomainAxis();
			valori.setAutoRange(true);
			valori.setFixedAutoRange(60000.0);  // 60 seconds
		    valori= subplot1.getRangeAxis();
		    NumberAxis rangeAxis1 = new NumberAxis("EUR/USD");
			rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
			renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
			
			//indicatori tecnici
			subplotMedia = resultMedia.getXYPlot();
			subplotMedia.setDomainGridlinesVisible(true);
			subplotMedia.setDomainGridlinesVisible(true);
			ValueAxis valoriM=subplot1.getDomainAxis();
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
			
			subPlottBoolinger = resultBoolinger.getXYPlot();
			subPlottBoolinger.setDomainGridlinesVisible(true);
			subPlottBoolinger.setDomainGridlinesVisible(true);
			ValueAxis valoriB=subplot1.getDomainAxis();
			valoriB.setAutoRange(true);
			valoriB.setFixedAutoRange(60000.0);  // 60 seconds
		    valoriB= subPlottBoolinger.getRangeAxis();
		    NumberAxis rangeAxisB = new NumberAxis("BOOLINGER");
			rangeAxisB.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			LineAndShapeRenderer rendererB = new LineAndShapeRenderer();
			rendererB.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
			
		    
			subPlotStocastico = resultStocastico.getXYPlot();
			subPlotStocastico.setDomainGridlinesVisible(true);	
			
			//PLOT FINALE			
			plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
			//regolo asse y			
			ValueAxis axis = plot.getDomainAxis();
	        axis.setAutoRange(true);
	        axis.setFixedAutoRange(60000.0);  // 60 seconds
	        axis = plot.getRangeAxis();
			plot.setGap(10.0);			
			plot.add(subplot1, 2);			
			JFreeChart result = new JFreeChart("EUR/USD",plot);
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
	        	        
	        return result;
    }    	
		
	 // aggiungo serie ala dataset
	public void setData(TimeSeries serie){
		this.dataset.addSeries(serie);		
	}
	
	//___________________________________________________________________________
	
	public void addSubPlot(String choose){
		if(choose==GraficiCombinati.INDICATORI[0])
			plot.add(this.subplotMedia, 2);
		if(choose==GraficiCombinati.INDICATORI[1])
			plot.add(this.subPlotMEsp, 2);
		if(choose==GraficiCombinati.INDICATORI[2])
			plot.add(this.subPlotMACDDiff, 2);
		if(choose==GraficiCombinati.INDICATORI[3])
			plot.add(this.subPlottBoolinger, 2);
		if(choose==GraficiCombinati.INDICATORI[4])
			plot.add(this.subPlotStocastico, 2);
		if(choose==GraficiCombinati.INDICATORI[5]) {
			cal=new CalendarioEconomico();
			cal.show();
			cal.setData( new EconomicCalendar().data());
		};
		if(choose==GraficiCombinati.INDICATORI[6]){
			this.removeSubPlot();
		}			
	}
	
	public void removeSubPlot(){		
		plot.remove(this.subplotMedia);
		plot.remove(this.subPlotMEsp);
		plot.remove(this.subPlotMACDDiff);
		plot.remove(this.subPlottBoolinger);
		plot.remove(this.subPlotStocastico);
	}	
	
	//--------------------------------------------------------------------------------------------------------------------
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
}  