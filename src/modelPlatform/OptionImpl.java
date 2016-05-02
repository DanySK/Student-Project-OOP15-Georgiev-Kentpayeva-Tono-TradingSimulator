package modelPlatform;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

public class OptionImpl {
	
      int type;//1 opzione forex 2)opzione binaria
      int time;
      TimeSeries option=new TimeSeries("Option");
      
      public OptionImpl(int type,Double value,Millisecond inittime)
      {
    	  this.type=type;
    	  this.option.add(inittime,value);
      }
      
      
      
      if(type==1)
      {
    	  
    	  
      }
      else if(type==2)
      {
    	  
      }
  }

}
