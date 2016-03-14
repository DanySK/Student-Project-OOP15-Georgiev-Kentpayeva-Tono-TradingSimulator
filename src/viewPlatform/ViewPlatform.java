package viewPlatform;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Piattaforma.ModelValuesAsset;

public interface ViewPlatform {
	
	public JFrame drawGraph();
	public JPanel uI();//user Interface
	public JPanel buy();//zona per puntare
	
	public void setValueGraph(ModelValuesAsset asset);
	
}
