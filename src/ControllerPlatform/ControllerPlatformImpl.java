package ControllerPlatform;

import java.util.List;

import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;
import userModel.User;
import userModel.UserImpl;
import viewPlatform.CandleStick;
import viewPlatform.ViewPlatform;
import viewPlatform.ViewPlatformImpl;

public class ControllerPlatformImpl implements ControllerPlatform{

	
	ViewPlatform view = null;
	ModelPlatform model = new ModelPlatformImpl();
	
	boolean isCandleGraph=true;
	
	//public boolean isUpDateModel=false;

	public ControllerPlatformImpl() {
		// TODO Auto-generated constructor stub
		
	}


	
	public void gestioneTemp(){
		
			System.out.println("ctr-ok");
		
		
			AbstractSeriesDataset asset;
			asset=model.dataFeed(isCandleGraph);
			
			System.out.println("ctr-ok2");
			
			view = new ViewPlatformImpl(asset,isCandleGraph);
			
			isCandleGraph=((ViewPlatformImpl)view).isCandleGraph;
			
			if(((ViewPlatformImpl)view).isUpDateCtr==true){
				System.out.println("bene2-");
				((ModelPlatformImpl)model).isUpDateModel=true;
				
				asset=model.dataFeed(((ViewPlatformImpl)view).isCandleGraph);
				
			}
			else{
				((ModelPlatformImpl)model).isUpDateModel=false;
			}
			
		
			/*scorro la lista di asset
			asset.forEach(ass->{
				//view = new ViewPlatformImpl(ass);
				
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//grafico le modifiche degli asset nel tempo
				view.refreshGraph(ass);
				
				//view.close();
				
			});*/
			
				
			//view.setValueGraph(asset);
			//view.show();
		
		
	}
	  
	
	

	@Override
	public void gestioneUser() {
		// TODO Auto-generated method stub
		
		
		//registro gli utenti
		User user = new UserImpl("Andrea","Rossi",1994,"andr@gmail.com",329123456);
		
	}

	
	
	
	
	
}