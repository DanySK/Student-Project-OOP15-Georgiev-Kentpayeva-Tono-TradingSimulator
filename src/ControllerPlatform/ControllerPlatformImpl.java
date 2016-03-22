package ControllerPlatform;

import java.util.List;

import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;
import modelPlatform.ValuesAssetImpl;
import userModel.User;
import userModel.UserImpl;
import viewPlatform.ViewPlatform;
import viewPlatform.ViewPlatformImpl;

public class ControllerPlatformImpl implements ControllerPlatform{

	
	ViewPlatform view = null;
	ModelPlatform model = new ModelPlatformImpl();
	
	

	public ControllerPlatformImpl() {
		// TODO Auto-generated constructor stub
		
	}


	
	public void gestioneTemp(){
		
			List<ValuesAssetImpl> asset;
			asset=model.dataFeed();
			view = new ViewPlatformImpl(asset.iterator().next());
		
			//scorro la lista di asset
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
				
			});
			
				
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