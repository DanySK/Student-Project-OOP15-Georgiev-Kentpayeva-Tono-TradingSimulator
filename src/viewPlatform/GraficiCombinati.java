package viewPlatform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



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
						"MACD","Bande di Boolinger","Stocastico", "Calendario Economico"};
	//per rappresentare il calendario economico       
	CalendarioEconomico cal=null;
    TimeSeriesCollection dataset;
    //serie indicatori
    TimeSeriesCollection mediaMobilSemplice;
	TimeSeriesCollection  mediaMobilEsponenziale;	
	TimeSeriesCollection  CalcoloRSI;
	//Bande Di Boolinger
	TimeSeriesCollection  bandaDiBoolingerSup;
	TimeSeriesCollection  bandaDiBoolingerInf;	
	//MACD
	TimeSeriesCollection mACDDIff;
	TimeSeriesCollection mACDSingle;	
	TimeSeriesCollection stocastico;	    

    //elementi grafici
    XYPlot subplot2,subPlotMEsp,	subPlotMACDDiff,	subPlottBoolinger,	subPlotStocastico;
    CombinedDomainXYPlot plot;
   
    int n=0,intervalloDiGiocata=0;

    public GraficiCombinati(final String title) {
    	
    	super(title);
    	dataset = new TimeSeriesCollection();
        //serie indicatori
		mediaMobilSemplice=  new TimeSeriesCollection();
		mediaMobilEsponenziale=  new TimeSeriesCollection();   	
		CalcoloRSI=  new TimeSeriesCollection();
		//Bande Di Boolinger
		bandaDiBoolingerSup=  new TimeSeriesCollection();
		bandaDiBoolingerInf=  new TimeSeriesCollection();   	
    	//MACD
    	mACDDIff=  new TimeSeriesCollection();
        mACDSingle=  new TimeSeriesCollection();
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
    		
    		final JFreeChart resultBoolinger;
    		resultBoolinger = ChartFactory.createTimeSeriesChart(
	            "Dynamic Line And TimeSeries Chart",
	            "Time",
	            "Value",
	            (XYDataset) this.bandaDiBoolingerInf,
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
		    NumberAxis rangeAxis1 = new NumberAxis("Value");
			rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
			renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
			
			//indicatori tecnici
			subplot2 = resultMedia.getXYPlot();
			subplot2.setDomainGridlinesVisible(true);
			subPlotMEsp = resultMEsp.getXYPlot();
			subPlotMEsp.setDomainGridlinesVisible(true);
			subPlotMACDDiff = resultMACDDiff.getXYPlot();
			subPlotMACDDiff.setDomainGridlinesVisible(true);
			subPlottBoolinger = resultMACDSingle.getXYPlot();
			subPlottBoolinger.setDomainGridlinesVisible(true);
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
			plot.add(this.subplot2, 2);
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
	}
	
	public void removeSubPlot(){		
		plot.remove(this.subplot2);
	}	
	
	//--------------------------------------------------------------------------------------------------------------------
	//inserisco le serie per graficare gli indicatori tecnici
	public void insMediaSeplice(TimeSeries serie){
		this.mediaMobilSemplice.addSeries(serie);		
	}
	
	public void insEsp(TimeSeries serie){
		this.mediaMobilEsponenziale.addSeries(serie);		
	}
	
	public void insRsi(TimeSeries serie){
		this.CalcoloRSI.addSeries(serie);		
	}
		
	public void insBolingerSup(TimeSeries serie){
		this.bandaDiBoolingerSup.addSeries(serie);		
	}
	
	public void insBolingerInf(TimeSeries serie){
		this.bandaDiBoolingerInf.addSeries(serie);		
	}
	
	public void insMacdDiff(TimeSeries serie){
		this.mACDDIff.addSeries(serie);		
	}
	
	public void insMacdSingle(TimeSeries serie){
		this.mACDSingle.addSeries(serie);	
	}	
	
	public void insStocastico(TimeSeries serie){
		this.stocastico.addSeries(serie);		
	}
}  