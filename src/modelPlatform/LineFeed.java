package modelPlatform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.ohlc.OHLCSeries;

public class LineFeed implements Strategy {
	
	
	TimeSeries ser; 
	BufferedReader in=null;
	List<String> lista;
	String value;
	
	int count=0;
	
	public LineFeed()
	{
		ser=new TimeSeries("eur");
		lista=new ArrayList<>();
		 
	}

	@Override
	public void feed() {
		// TODO Auto-generated method stub
		
		
		
		 
		
		
		
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
	 			    //System.out.println(value);
	 			    this.ser.add(new Millisecond(),Double.parseDouble(value));
	 			    //System.out.flush();
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
	  
	 public TimeSeries getSer(){return this.ser;}

	@Override
	public TimeSeries getLineFeed() {
		// TODO Auto-generated method stub
		return this.ser;
	}

	@Override
	public OHLCSeries getOHLCFeed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return Double.parseDouble(value);
	};
	



}
