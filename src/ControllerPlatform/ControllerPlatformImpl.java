package ControllerPlatform;

import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;
import modelPlatform.ValuesAssetImpl;
import viewPlatform.ViewPlatform;
import viewPlatform.ViewPlatformImpl;

public class ControllerPlatformImpl {

	
	
	ViewPlatform view = new ViewPlatformImpl();
	ModelPlatform model = new ModelPlatformImpl();
	
	
	
	public void gestioneTemp(){
		ValuesAssetImpl asset;
		asset=model.dataFeed();
		view.setValueGraph(asset);
		
	}
	  
	
	
	public ControllerPlatformImpl() {
		// TODO Auto-generated constructor stub
		
	}

	
	
	
	
	
}
