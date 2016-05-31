package modelPlatform;



import org.jfree.data.ComparableObjectSeries;
import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.Series;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

public interface ModelPlatform {

	
	
	  
	  public void candleStick();
	  public TimeSeries getFeed();
	  public OHLCSeries getCandle();
	  public double getValue();
	  public void lineCalc();
	
	
	

}



