package modelPlatform;



import org.jfree.data.ComparableObjectSeries;
import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.Series;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

public interface ModelPlatform {

	//public OHLCSeriesCollection dataFeed();
	
	public AbstractSeriesDataset dataFeed(boolean isCandleStick);
	
	
	
	

}



