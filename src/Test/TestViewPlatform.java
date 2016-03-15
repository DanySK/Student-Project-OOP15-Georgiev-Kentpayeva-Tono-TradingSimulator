package Test;

import Piattaforma.ModelValuesAsset;
import viewPlatform.Candlestick;
import viewPlatform.ViewPlatformImpl;

public class TestViewPlatform {

	public static void main(String[] args) {
		ViewPlatformImpl view= new ViewPlatformImpl();
		view.setValueGraph(new ModelValuesAsset(1,1,1,1,1));
    }
	
}
