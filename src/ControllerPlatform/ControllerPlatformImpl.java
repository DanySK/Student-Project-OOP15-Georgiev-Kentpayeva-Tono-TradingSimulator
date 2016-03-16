package ControllerPlatform;

import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;
import modelPlatform.ValuesAssetImpl;
import viewPlatform.ViewPlatform;
import viewPlatform.ViewPlatformImpl;

public class ControllerPlatformImpl implements ControllerPlatform{

	
	ViewPlatform view = null;
	ModelPlatform model = new ModelPlatformImpl();
	
	
	
	
	public void gestioneTemp(){
		
		
		
		for(int i=0;i<1;i++){
			
			if(i==10){
				view.close();
			}
			
			
			ValuesAssetImpl asset;
			asset=model.dataFeed();
			
			System.out.println(asset.toString());
			
			view = new ViewPlatformImpl(asset);
			
			System.out.println("errore");
			
			//view.setValueGraph(asset);
			//view.show();
		
		}
	}
	  
	
	
	public ControllerPlatformImpl() {
		// TODO Auto-generated constructor stub
		
	}

	
	
	
	
	
}