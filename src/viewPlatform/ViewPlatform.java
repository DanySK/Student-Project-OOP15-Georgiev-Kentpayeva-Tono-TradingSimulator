package viewPlatform;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelPlatform.ValuesAsset;
import modelPlatform.ValuesAssetImpl;

public interface ViewPlatform {
	
	public JFrame drawGraph(boolean isCandleStick);
	public JFrame uI();//user Interface
	public JPanel buy();//zona per puntare
	
	
	public void setValueGraph(List<ValuesAsset> asset);
	
	public void refreshGraph(ValuesAsset ass);
	public void close();
	
}
