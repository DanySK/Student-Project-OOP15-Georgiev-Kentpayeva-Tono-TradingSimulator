package modelPlatform;

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

public class ModelPlatformImpl{
	
	boolean start=true;
	public boolean isUpDateModel=false;
	private boolean isUp=false;
	//Timer timer=new Timer(10, null);
	TimeSeries serie;
	
	private double lastValue=100.0;
	
	


	String csvFile = "datasrc/data.csv";
	BufferedReader br = null;
	BufferedReader in = null;
	private int count=0;
	List<String> lista;
	
	String line = "";
	//List<ValuesAssetImpl> value=new ArrayList<>();
	Series asset=null;
	OHLCSeries cs;
	AbstractSeriesDataset dataset=null;
	
	
	boolean ok=true;

	boolean isCandleStick=true;
	
	public ModelPlatformImpl()
	{
		 serie=new TimeSeries("random",Millisecond.class);
		 lista=new ArrayList<>();
		 cs=new OHLCSeries("rnd2");
		//timer.start();
	}

	



	
	public void calc()
	{
	   
		try {
 			in = new BufferedReader(new FileReader("C:\\Users\\georg\\Documents\\data.csv"));
 		} catch (FileNotFoundException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
         String inputLine;
         try {
 			in.readLine();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         try {
        	 
        	 
 			   while((inputLine = in.readLine())!=null&&count==0)
 			   {
 				   lista.add(inputLine);
 			   }
 			    if(count<lista.size()){
	 			    count++;
	 			    
	 			    StringTokenizer st = new StringTokenizer(lista.get(count-1), ";");
	 			    
	 			    st.nextToken();
	 			    String value=st.nextToken();
	 			    System.out.println(value);
	 			    this.serie.add(new Millisecond(),Float.parseFloat(value));
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
	
	public void candlestick()
	{
		try {
 			in = new BufferedReader(new FileReader("C:\\Users\\georg\\Documents\\cand.csv"));
 		} catch (FileNotFoundException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
         String inputLine;
         try {
 			in.readLine();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         try {
        	 
        	 
 			   while((inputLine = in.readLine())!=null&&count==0)
 			   {
 				   lista.add(inputLine);
 			   }
 			    if(count<lista.size()){
	 			    count++;
	 			    
	 			    StringTokenizer st = new StringTokenizer(lista.get(count-1), ";");
	 			    
	 			    st.nextToken();
	 			   
	 			    double open=Double.parseDouble(st.nextToken());
	 			    double high=Double.parseDouble(st.nextToken());
	 			    double low=Double.parseDouble(st.nextToken());
	 			    double close=Double.parseDouble(st.nextToken());
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
	
	

}
