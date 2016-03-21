package ControllerPlatform;

import java.util.List;

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
			
			
			List<ValuesAssetImpl> asset;
			asset=model.dataFeed();
			
			
			
			view = new ViewPlatformImpl(asset);
			
				
			//view.setValueGraph(asset);
			//view.show();
		
		}
	}
	  
	
	
	public ControllerPlatformImpl() {
		// TODO Auto-generated constructor stub
		
	}

	
	
	
	
	
}