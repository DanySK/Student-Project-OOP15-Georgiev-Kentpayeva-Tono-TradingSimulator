package viewPlatform;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.data.time.ohlc.OHLCSeriesCollection;

public interface ViewPlatform {
	
	public JFrame drawGraph(boolean isCandleStick);
	public JFrame uI();//user Interface
	public JPanel buy();//zona per puntare
	
	
	public void setValueGraph(OHLCSeriesCollection asset);
	
	public void refreshGraph(OHLCSeriesCollection ass);
	public void close();
	
}
