package viewPlatform;
	/*import org.jfree.chart.*;
	import org.jfree.chart.axis.*;
	import org.jfree.chart.plot.XYPlot;
	import org.jfree.chart.renderer.xy.CandlestickRenderer;
	import org.jfree.data.xy.*;

import modelPlatform.*;

import javax.swing.*;
	import java.awt.*;
	import java.io.*;
	import java.net.URL;
	import java.text.*;
	import java.util.*;
	import java.util.List;

	public class Candlestick extends JFrame {
		
		ValuesAsset asset=null;
	    public Candlestick(String stockSymbol,ValuesAsset asset) {
	    */	
	    	
import java.awt.Point;

//_______________________________________________________________________________
	    	
	    	
	    	import java.util.Calendar;
	    	import java.util.Date;

	    	import org.jfree.chart.ChartFactory;
			import org.jfree.chart.ChartMouseEvent;
			import org.jfree.chart.ChartPanel;
	    	import org.jfree.chart.JFreeChart;

	    	import org.jfree.data.xy.DefaultHighLowDataset;
	    	import org.jfree.ui.ApplicationFrame;
	    	import org.jfree.ui.RefineryUtilities;

			import ControllerPlatform.ControllerPlatformImpl;
			import modelPlatform.*;

	    	public class CandleStick extends ApplicationFrame {

	    		/*_______________________________FIELDS_______________________________________________________________*/
	    		int nCandele=5;
	    		ValuesAsset asset=null;
	    		/*______________________________________________________________________________________________*/
	    		
	    	  public CandleStick(String titel, ValuesAsset asset) {
		    	  super(titel);
		    	  
		    	  this.asset=asset;
	
		    	  final DefaultHighLowDataset dataset = createDataset();
		    	  final JFreeChart chart = createChart(dataset);
		    	  final ChartPanel chartPanel = new ChartPanel(chart);
		    	  chartPanel.setPreferredSize(new java.awt.Dimension(600, 350));
		    	  setContentPane(chartPanel);
		      }

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

	    	  private JFreeChart createChart(final 
	    	  DefaultHighLowDataset dataset) {
		    	  final JFreeChart chart = ChartFactory.createCandlestickChart(
		    	  "Candlestick Demo", "Time", "Price", dataset, false);
		    	  return chart;
	    	  }

	    	  
	    	  
	    	  
	    	  
	    	}
