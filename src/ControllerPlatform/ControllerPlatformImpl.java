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

	//per THREAD__________
	private Agent agent;
	//____________________
	
	ViewPlatform view = null;
	ModelPlatform model = new ModelPlatformImpl();
	
	boolean isCandleGraph=false;
	
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
			
			System.out.println("ctr-ok3");
			
			isCandleGraph=((ViewPlatformImpl)view).isCandleGraph;
			
			System.out.println("ctr-ok4");
			
			System.out.println("---------->"+view.getButtonUp().getText());
			view.getButtonUp().addActionListener(e->{
				System.out.println("prova bottone");
			});
			
		//while(true){
			

			//System.out.println(((ViewPlatformImpl)view).isUP);
			//__________controllo se viene premuto il pulsante up u down________________
			
			if(((ViewPlatformImpl)view).isUP==true){
				//System.out.println("okkkkkkkkkkkkkklalalalalalala");
				
				
				((ModelPlatformImpl)model).setIsUp(true);;
				
				//asset=model.dataFeed(((ViewPlatformImpl)view).isCandleGraph);
				//System.out.println("okkkkkkkkkkkkkklalalalalalala");
				((ModelPlatformImpl)model).setIsUp(true);;
				
				((ViewPlatformImpl)view).isUP=false;
				
			}
			else{
				
				//((ModelPlatformImpl)model).setIsUp(false);;
				
			}
			//______________________________________________________
			
			/*_______controllo indicatore tecnico selezionato dalla view ___________*/
			
			if(((ViewPlatformImpl)view).isIndicatoreReady==true){
				System.out.println("lalalalalalala");
			}
			
			
			//controllo ce il model sia pronto
			if(((ViewPlatformImpl)view).isUpDateCtr==true){
				//System.out.println("bene2-");
				((ModelPlatformImpl)model).isUpDateModel=true;
				
				asset=model.dataFeed(((ViewPlatformImpl)view).isCandleGraph);
				
			}
			else{
				((ModelPlatformImpl)model).isUpDateModel=false;
			}
			
		//}
			
			
		
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

	
	
	//________materiale thread prof___________________________
	public void start() {
        if (agent != null) {
            throw new IllegalStateException();
        }
        this.agent = this.new Agent();
        new Thread(this.agent).start();
    }

    private class Agent implements Runnable {

        // stop viene modificato "da fuori": sia volatile!!
        private volatile boolean stop;

        /**
         * Behavior of thread.
         */
        public void run() {
            while (!this.stop) {
                try {
                    ((ViewPlatformImpl) ControllerPlatformImpl.this.view).updateCounter(0);
                    //ControllerPlatformImpl.this.counter.increment();
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("Counting has been interrupted " + ex);
                }
            }
        }

        /**
         * External command to stop counting.
         */
        public void stopCounting() {
            this.stop = true;
        }
    }
	
}