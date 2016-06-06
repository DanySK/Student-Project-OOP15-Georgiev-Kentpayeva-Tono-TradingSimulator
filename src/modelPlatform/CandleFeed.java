package modelPlatform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.ohlc.OHLCSeries;

public class CandleFeed implements Strategy {
	
	private BufferedReader br = null;
	private List<String> list;
	private OHLCSeries cs;
	private int count=0;
	
	
	public CandleFeed()
	{
		cs=new OHLCSeries("EUR/USD");
		this.list=new ArrayList<>();
		
	}

	@Override
	public void feed() {
		// TODO Auto-generated method stub
		
		br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("cand.csv")));
			
 		
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
 				   this.list.add(input);
 			   }
 			    if(count<list.size()){
	 			    count++;
	 			    
	 			    StringTokenizer s = new StringTokenizer(list.get(count-1), ";");
	 			    
	 			    s.nextToken();
	 			   
	 			    double open=Double.parseDouble(s.nextToken());
	 			    double high=Double.parseDouble(s.nextToken());
	 			    double low=Double.parseDouble(s.nextToken());
	 			    double close=Double.parseDouble(s.nextToken());
	 			  
	 			    //System.out.println(open+" "+high+" "+low+" "+close);
	 			  
	 			    this.cs.add(new Millisecond(),open,high,low,close);
	 			    //System.out.flush();
	 			    
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

	@Override
	public TimeSeries getLineFeed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OHLCSeries getOHLCFeed() {
		// TODO Auto-generated method stub
		return this.cs;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	
	

}
