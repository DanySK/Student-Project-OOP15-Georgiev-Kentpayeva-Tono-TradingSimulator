package modelPlatform;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.ohlc.OHLCSeries;

public interface Strategy {
	
	public void feed();
	public TimeSeries getLineFeed();
	public OHLCSeries getOHLCFeed();
	public double getValue();
	

}
