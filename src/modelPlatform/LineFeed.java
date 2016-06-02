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
	
	
	
	
	public LineFeed()
	{
		
		 
	}

	@Override
	public void feed() {
		// TODO Auto-generated method stub
		List<String> lista=new ArrayList<>();
		ModelPlatformImpl model=new ModelPlatformImpl();
		
		 int count=0;
		 String value;
		BufferedReader in=null;
		
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
	 			    //model.setSer(series);serie.add(new Millisecond(),Double.parseDouble(value));
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
	
	

	@Override
	public TimeSeries getTimeSeries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OHLCSeries getOHLCSeries() {
		// TODO Auto-generated method stub
		return null;
	}

}
