package Piattaforma;

import javax.swing.JFrame;

public class ViewPlatformImpl extends JFrame implements ViewPlatform{

			

		
			@Override
			public void GeneraGrafo() {
				// TODO Auto-generated method stub
				
				new CandlestickDemo("MSFT").setVisible(true);
			    
				
			}
			@Override
			public void DatiUser() {
				// TODO Auto-generated method stub
				
			}
		}
		
		
		

