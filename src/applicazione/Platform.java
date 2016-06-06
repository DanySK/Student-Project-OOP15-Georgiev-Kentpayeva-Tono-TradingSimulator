package applicazione;

import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;

import ControllerPlatform.*;

import modelPlatform.CandleFeed;
import modelPlatform.LineFeed;

import modelPlatform.Strategy;

import viewPlatform.GUI;
import viewPlatform.View;
//import viewPlatform.uI;

public class Platform {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
               
                
                View view = new GUI();
                Strategy modelLine=new LineFeed();
                Strategy modelCandle=new CandleFeed();

                
                ControllerPlatformImpl controller = new ControllerPlatformImpl(view,modelLine,modelCandle);
                controller.start();
                controller.start2();
                
                
                
            }
        });  
		
	}
}

