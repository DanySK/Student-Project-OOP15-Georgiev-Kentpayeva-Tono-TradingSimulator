

package applicazione;

import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;

import ControllerPlatform.*;
import IndicatoriTecniciModel.IndicatoriFormuleImpl;
import modelPlatform.CandleFeed;
import modelPlatform.LineFeed;

import modelPlatform.Strategy;
import userModel.UserImpl;
import viewPlatform.GraficiCombinati;
import viewPlatform.GUI;
//import viewPlatform.uI;

public class Platform {

	public static void main(String[] args) {
		/*ControllerPlatformImpl ctr=new ControllerPlatformImpl();
		ctr.gestioneTemp();*/
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
               
                
                GUI view = new GUI();
                Strategy modelLine=new LineFeed();
                Strategy modelCandle=new CandleFeed();
               
                
               // IndicatoriFormuleImpl form=new IndicatoriFormuleImpl();
                //UserImpl user=new UserImpl();
                //uI ui=new uI();
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
}

