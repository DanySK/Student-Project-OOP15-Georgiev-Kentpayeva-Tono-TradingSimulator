package modelPlatform;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.ohlc.OHLCSeries;

public interface Strategy {
	
	void feed();
	TimeSeries getTimeSeries();
	OHLCSeries getOHLCSeries();

	
	

}
