package IndicatoriTecniciModel;



import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.Timer;

import org.jfree.data.ComparableObjectSeries;
import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.Series;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

import viewPlatform.ViewPlatformImpl;


public class IndicatoriModel {
	
	List<Double> valori=null;//new ArrayList<>();
	
	List<Double> mediaMobilSemplice=null;
	List<Double>  mediaMobilEsponenziale=null;
	
	List<Double>  mediaMobilePonderata=null;
	
	
	List<Double>  CalcoloRSI=null;
	
	//Bande Di Boolinger
	List<Double>  bandaDiBoolingerSup=null;
	List<Double>  bandaDiBoolingerInf=null;
	
	//MACD
	List<Double> mACDDIff=null;
	List<Double> mACDSingle=null;
	
	List<Double>  stocastico=null;
	
	List<Double>  serieFibonacci=null;
	

	Indicatori indicatoriFormule=new IndicatoriFormuleImpl(valori);
	
	boolean start=true;
	public boolean isUpDateModel=false;
	private boolean isUp=false;
	//Timer timer=new Timer(10, null);
	TimeSeries serie;
	
	private double lastValue1=100.0;
	
	double result=0;
	
	

	String csvFile = "datasrc/data.csv";
	BufferedReader br = null;
	BufferedReader in = null;
	private int count=0;
	List<String> lista;
	List<String> list;
	
	String line = "";
	//List<ValuesAssetImpl> value=new ArrayList<>();
	Series asset=null;
	OHLCSeries cs;
	AbstractSeriesDataset dataset=null;
	
	
	boolean ok=true;

	boolean isCandleStick=true;
	
	public IndicatoriModel()
	{
		
		
		 serie=new TimeSeries("random",Millisecond.class);
		 lista=new ArrayList<>();
		 list=new ArrayList<>();
		 cs=new OHLCSeries("rnd2");
		 valori=new ArrayList();
		//timer.start();
		 

			List<Double> mediaMobilSemplice=new ArrayList();
			List<Double>  mediaMobilEsponenziale=new ArrayList();
			
			List<Double>  mediaMobilePonderata=new ArrayList();
			
			
			List<Double>  CalcoloRSI=new ArrayList();
			
			//Bande Di Boolinger
			List<Double>  bandaDiBoolingerSup=new ArrayList();
			List<Double>  bandaDiBoolingerInf=new ArrayList();
			
			//MACD
			List<Double> mACDDIff=new ArrayList();
			List<Double> mACDSingle=new ArrayList();
			
			List<Double>  stocastico=new ArrayList();
			List<Double>  serieFibonacci=new ArrayList();
			
	}

	



	
	public void calc()
	{
	   
		
	 	this.serie.add(new Millisecond(),this.indicatoriFormule.CalcoloRSI());
	 			   
    	 
		
	}
	
	public void candleStick()
	{
		try {
 			br = new BufferedReader(new FileReader("datasrc/cand.csv"));
 		} catch (FileNotFoundException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
         String input;
         try {
 			br.readLine();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         try {
        	 
        	 
 			   while((input = br.readLine())!=null&&count==0)
 			   {
 				   list.add(input);
 			   }
 			    if(count<list.size()){
	 			    count++;
	 			    
	 			    StringTokenizer s = new StringTokenizer(list.get(count-1), ";");
	 			    
	 			    s.nextToken();
	 			   
	 			    double open=Double.parseDouble(s.nextToken());
	 			    double high=Double.parseDouble(s.nextToken());
	 			    double low=Double.parseDouble(s.nextToken());
	 			    double close=Double.parseDouble(s.nextToken());
	 			    //String value=st.nextToken();
	 			    System.out.println(open+" "+high+" "+low+" "+close);
	 			    //this.serie.add(new Millisecond(),Float.parseFloat(value));
	 			    this.cs.add(new Millisecond(),open,high,low,close);
	 			    System.out.flush();
	 			    //System.out.close();
 			    }
 			    else{
 			    	//lancio errori
 			    }
 			   
 			
 		} catch (NumberFormatException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}
	
	public TimeSeries getFeed()
	{
		return this.serie;
	}
	
	public OHLCSeries getCandle()
	{
		return this.cs;
	}
	
	public void insertValue(double value){
		this.valori.add(value);
		
		//aggiorno le formule
		mediaMobilSemplice.add(this.indicatoriFormule.CalcoloMediaMobilSemplice());
		mediaMobilEsponenziale.add(this.indicatoriFormule.CalcoloMediaMobilEsponenziale());
		
		mediaMobilePonderata.add(this.indicatoriFormule.CalcoloMediaMobilePonderata());
		
		
		CalcoloRSI.add(this.indicatoriFormule.CalcoloRSI());
		
		//Bande Di Boolinger
		bandaDiBoolingerSup.add(this.indicatoriFormule.CalcoloBandaDiBoolingerSup());
		bandaDiBoolingerInf.add(this.indicatoriFormule.CalcoloBandaDiBoolingerInf());
		
		//MACD
		mACDDIff.add(this.indicatoriFormule.CalcoloMACDDIff());
		mACDSingle.add(this.indicatoriFormule.CalcoloMACDSingle());
		
		stocastico.add(this.indicatoriFormule.CalcoloStocastico());
		
		serieFibonacci.add(this.indicatoriFormule.CalcoloSerieFibonacci());
		
	}
}