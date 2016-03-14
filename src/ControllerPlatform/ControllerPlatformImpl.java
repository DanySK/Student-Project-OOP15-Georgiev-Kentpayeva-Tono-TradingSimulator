package ControllerPlatform;

import Piattaforma.ModelPlatform;
import Piattaforma.ModelPlatformImpl;
import Piattaforma.ModelValuesAsset;
import viewPlatform.ViewPlatform;
import viewPlatform.ViewPlatformImpl;

public class ControllerPlatformImpl {

	ViewPlatform view = new ViewPlatformImpl();
	ModelPlatform model = new ModelPlatformImpl();
	
	
	
	public void gestioneTemp(){
		ModelValuesAsset asset;
		asset=model.dataFeed();
		
	}
	
	
	
	public ControllerPlatformImpl() {
		// TODO Auto-generated constructor stub
		
	}

	
	
	
	
	
}
