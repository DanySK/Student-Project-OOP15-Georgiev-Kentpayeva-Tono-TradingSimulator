

package applicazione;

import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;

import ControllerPlatform.*;
import modelPlatform.ModelPlatformImpl;

import viewPlatform.GraficiCombinati;
import viewPlatform.ViewPlatformImpl;

public class Platform {

	public static void main(String[] args) {
		/*ControllerPlatformImpl ctr=new ControllerPlatformImpl();
		ctr.gestioneTemp();*/
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
                ModelPlatformImpl model = new ModelPlatformImpl();
                ViewPlatformImpl view = new ViewPlatformImpl(); 
                ControllerPlatformImpl controller = new ControllerPlatformImpl(view,model);
                controller.start();
                //controller.start2();
                
                
                view.pack();
                RefineryUtilities.centerFrameOnScreen(view);
                view.setVisible(true);
            }
        });  
		
	}
}

