

package applicazione;

import java.awt.Color;

import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;

import ControllerPlatform.*;

import modelPlatform.CandleFeed;
import modelPlatform.LineFeed;

import modelPlatform.Strategy;

import viewPlatform.GUI;
//import viewPlatform.uI;

public class Platform {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
               
                
                GUI view = new GUI();
                Strategy modelLine=new LineFeed();
                Strategy modelCandle=new CandleFeed();

                view.setBackground(new Color(0).ORANGE);

                ControllerPlatformImpl controller = new ControllerPlatformImpl(view,modelLine,modelCandle);
                controller.start();
                controller.start2();
                
                view.pack();

        		
                RefineryUtilities.centerFrameOnScreen(view);
                view.setVisible(true);
                
                
            }
        });  
		
	}
}

