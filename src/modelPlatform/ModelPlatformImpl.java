package modelPlatform;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.Series;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;



public class ModelPlatformImpl implements ModelPlatform{
	

	TimeSeries serie;
	BufferedReader br = null;
	BufferedReader in = null;
	private int count=0;
	List<String> lista;
	List<String> list;
	
	
	
	
	OHLCSeries cs;
	
	
	


	
	String value;
	
	public ModelPlatformImpl()
	{
		 serie=new TimeSeries("random");
		 lista=new ArrayList<>();
		 list=new ArrayList<>();
		 cs=new OHLCSeries("rnd2");
		
	}

	



	
	public void lineCalc()
	{
	   
		try {
 			in = new BufferedReader(new FileReader("datasrc/data.csv"));
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
	 			    value=st.nextToken();
	 			    System.out.println(value);
	 			    this.serie.add(new Millisecond(),Double.parseDouble(value));
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
	 			  
	 			    System.out.println(open+" "+high+" "+low+" "+close);
	 			  
	 			    this.cs.add(new Millisecond(),open,high,low,close);
	 			    System.out.flush();
	 			    
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
	
	public double getValue()
	{
		return Double.parseDouble(value);
	}













	

}
