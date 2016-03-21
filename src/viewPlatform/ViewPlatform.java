package viewPlatform;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelPlatform.ValuesAsset;
import modelPlatform.ValuesAssetImpl;

public interface ViewPlatform {
	
	public JFrame drawGraph();
	public JFrame uI();//user Interface
	public JPanel buy();//zona per puntare
	
	
	public void setValueGraph(ValuesAsset asset);
	
	public void refreshGraph(ValuesAssetImpl ass);
	public void close();
	
}
