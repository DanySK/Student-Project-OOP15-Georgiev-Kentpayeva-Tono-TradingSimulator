package Test;

import modelPlatform.ValuesAsset;
import viewPlatform.Candlestick;
import viewPlatform.ViewPlatformImpl;

public class TestViewPlatform {

	public static void main(String[] args) {
		ViewPlatformImpl view= new ViewPlatformImpl();
		view.setValueGraph(new ValuesAsset(1,1,1,1,1));
    }
	
}
