package modelPlatform;

import java.util.Date;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

public class OptionImpl {
	
	double val;
	double puntata;
	Date data;
	String vin;
      
	public OptionImpl(double val,double puntata,Date data)
	{
		this.val=val;
		this.puntata=puntata;
		this.data=data;
		
	}
      
      
      
     public double getVal()
     {
    	 return val;
     }
}
