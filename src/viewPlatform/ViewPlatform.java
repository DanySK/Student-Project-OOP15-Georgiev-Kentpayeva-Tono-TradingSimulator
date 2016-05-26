package viewPlatform;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

public interface ViewPlatform {
	
	public JFrame drawGraph();
	public JPanel uI();//user Interface
	public JPanel buy();//zona per puntare
	
	
	public void setValueGraph(TimeSeriesCollection dataset);
	
	public void refreshGraph(AbstractSeriesDataset ass);
	public void close();
	
	
	
	public JButton getButtonUp();
	
	
	public void setCandleStick(boolean isCandleGraph);
	public boolean getCandleStick();
	
}
