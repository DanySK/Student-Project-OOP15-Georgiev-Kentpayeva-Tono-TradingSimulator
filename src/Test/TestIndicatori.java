package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;

import ControllerPlatform.ControllerPlatformImpl;
import modelPlatform.CandleFeed;
import modelPlatform.LineFeed;
import modelPlatform.Strategy;
import viewPlatform.GUI;

public class TestIndicatori {
	//Strategy modelLine;
	//Th trd;

	@org.junit.Test
	public void test() {		
		
		// i valori della serie devono essere positivi
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
               
                
                GUI view = new GUI();
                Strategy modelLine=new LineFeed();
                Strategy modelCandle=new CandleFeed();
               
                
                ControllerPlatformImpl controller = new ControllerPlatformImpl(view,modelLine,modelCandle);
                controller.start();
                controller.start2();
                
                
                view.pack();
                //ui.pack();
                RefineryUtilities.centerFrameOnScreen(view);
                //RefineryUtilities.centerFrameOnScreen(ui);
                view.setVisible(true);
                //ui.setVisible(true);
            }
        });  
	}
	
	/*private class Th implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				
				
				           TestSeriesLine.this.modelLine.feed();
				           
				
				
		}
			
			
		}
		
	}*/
}

