package ControllerPlatform;

import java.util.Date;

import IndicatoriTecniciModel.IndicatoriFormuleImpl;
import IndicatoriTecniciModel.IndicatoriModel;
import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;
import modelPlatform.OptionImpl;
import userModel.User;
import userModel.UserImpl;
import viewPlatform.*;

public class ControllerPlatformImpl{
	
	int DurataDiGioco=20;
	int nAccessi=0;
	
	ModelPlatformImpl model;
	GUI view;
	//uI ui;
	UserImpl user=new UserImpl();
	OptionImpl optin;
	IndicatoriModel ind=new IndicatoriModel();
	GraficiCombinati graf=new GraficiCombinati("prova");
	IndicatoriFormuleImpl form=new IndicatoriFormuleImpl();
	
	
	Agent agent;
	Agent2 agente;
	Opt option;
	
	boolean isCandleStick=true;
	boolean avvio=true;
	 volatile boolean sel;;
	
	public ControllerPlatformImpl(GUI view,ModelPlatformImpl model)
	{
		
		this.view=view;
		this.model=model;
		//this.form=form;
		//this.ui=ui;
		//this.user=user;
		 this.view.addObserver(new Observer(){

			@Override
			public void call() {
				// TODO Auto-generated method stub
				ControllerPlatformImpl.this.sel=true;
				
				
				if(avvio){
					//avvio=false;
				// TODO Auto-generated method stub
				 
			       // throw new IllegalStateException();
					
					if(option != null)
						 option.stopRunning();
					 
				 
			     ControllerPlatformImpl.this.option=ControllerPlatformImpl.this.new Opt();
			     ControllerPlatformImpl.this.option.setSel(sel);
			     new Thread(ControllerPlatformImpl.this.option).start();
			     
			     
			      
			      //option.stopRunning();
			     
			     
				}
				
				
				
			}

			@Override
			public void put() {
				// TODO Auto-generated method stub
				ControllerPlatformImpl.this.sel=false;
				if(avvio){
					
					//avvio=false;
				// TODO Auto-generated method stub
				 
			       // throw new IllegalStateException();
					if(option != null)
						 option.stopRunning();
					
				 
			     ControllerPlatformImpl.this.option=ControllerPlatformImpl.this.new Opt();
			     ControllerPlatformImpl.this.option.setSel(sel);
			     new Thread(ControllerPlatformImpl.this.option).start();
			     
			        
			      //option.stopRunning();
			     
			     
				}
				
				
			}});
		
		view.setDurataDiGioco(this.DurataDiGioco);
		
	}
	 
	
	public void start() {
        if (agent != null) {
            throw new IllegalStateException();
        }
        
        this.agent = this.new Agent();
        new Thread(this.agent).start();
        
        ControllerPlatformImpl.this.view.setData(
        		ControllerPlatformImpl.this.model.getFeed(),
        		ControllerPlatformImpl.this.form.getMediaSemplice(),
        		ControllerPlatformImpl.this.form.getEsp(),
        		ControllerPlatformImpl.this.form.getBolingerSup(),
        		ControllerPlatformImpl.this.form.getBolingerInf(),
        		ControllerPlatformImpl.this.form.getMacdDiff(),
        		ControllerPlatformImpl.this.form.getMacdSingle(),
        		ControllerPlatformImpl.this.form.getStocastico(),
        		ControllerPlatformImpl.this.form.getRsi());
        
        
        
    
        view.setDurataDiGioco(this.DurataDiGioco);
        
       
	}
	
	public void start2()
	{
		if(agente!=null)
		{
			throw new IllegalStateException();
		}
		this.agente=this.new Agent2();
		new Thread(this.agente).start();
		ControllerPlatformImpl.this.view.setDataSet(ControllerPlatformImpl.this.model.getCandle());
	}
	
	private class Agent implements Runnable
	{
		 public void run() {
	            while (true) {
	                
	                	ControllerPlatformImpl.this.model.lineCalc();//calcolo dei punti del grafico con tempo
	                	ControllerPlatformImpl.this.form.insertValori(ControllerPlatformImpl.this.model.getValue());
	                	ControllerPlatformImpl.this.form.CalcoloMediaMobilSemplice();
	                	ControllerPlatformImpl.this.form.CalcoloMediaMobilEsponenziale();
	                	//ControllerPlatformImpl.this.form.CalcoloRSI();
	                	//ControllerPlatformImpl.this.form.CalcoloBandaDiBoolingerSup();
	                	//ControllerPlatformImpl.this.form.CalcoloBandaDiBoolingerInf();
	                	ControllerPlatformImpl.this.form.CalcoloMACDDIff();
	                	ControllerPlatformImpl.this.form.CalcoloMACDSingle();
	                	ControllerPlatformImpl.this.form.CalcoloStocastico();
	                	
	                	
	                	
	                	
	                	try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	
	                	//cambio il grafico da  linea a candele 
	                	 
	                     if(view.getTipoGrafico().getSelectedItem().toString()=="candele"){
	                     	System.out.println("cambio");
	                     	view.changeGraph(true);
	                     }
	                	
	            }
		 }
	}
	
	private class Agent2 implements Runnable
	{
		 public void run() {
	            while (true) {
	                	
	            		nAccessi++;
	            	
	                	ControllerPlatformImpl.this.model.candleStick();//calcolo dei punti del grafico con tempo
	                  
	                	
	                	try {
	                		//accellero le prime 50 candele in modo che la stampa grafica delle candele sia più uniforme
	                		//(altrimenti le prime si sovrapponerebbero)
	                		if(nAccessi<50){
								Thread.sleep(10);
	                		}
	                		else{
	                			Thread.sleep(1000);
	                		}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	
	                	//cambio il grafico da candele a linea
	                	 
	                     if(view.getTipoGrafico().getSelectedItem().toString()=="normale"){
	                     	System.out.println("cambio");
	                     	view.changeGraph(false);
	                     }
	            }
		 }
	}
	
	private class Opt implements Runnable
	{
		private volatile boolean running = true;
		private  boolean sel,win;
		@Override
		
		
		public void run() {
			// TODO Auto-generated method stub
			
			
			
			
			
			
			//System.out.println(Controller.this.view.getStato());
			double val=ControllerPlatformImpl.this.model.getValue();
			
			
			//Controller.this.op=new Option(val,100,new Date());
			ControllerPlatformImpl.this.optin=new OptionImpl(val,ControllerPlatformImpl.this.view.getPuntata(),new Date());//------------------------------------------
			ControllerPlatformImpl.this.view.setPoint(val);
			ControllerPlatformImpl.this.view.disabilitaBottone();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			val=ControllerPlatformImpl.this.model.getValue();
			ControllerPlatformImpl.this.optin.setAttuale(val);
			
			
			if(sel)
			{
				    
				win=ControllerPlatformImpl.this.optin.callCalc();
				ControllerPlatformImpl.this.view.aggiornaConto(Double.toString(ControllerPlatformImpl.this.optin.getAccount()));
					
			}
			else
			{
				win=ControllerPlatformImpl.this.optin.putCalc();
				ControllerPlatformImpl.this.view.aggiornaConto(Double.toString(ControllerPlatformImpl.this.optin.getAccount()));
			}
			
			ControllerPlatformImpl.this.view.abilitaBottone();
			
			ControllerPlatformImpl.this.view.infoBox(win);
			
			//ControllerPlatformImpl.this.view.aggiornaConto(Double.toString(ControllerPlatformImpl.this.optin.getAccount()));
			
			//ciclo per tenere in sospeso il thread finchè non termina
			while(running){
			
			
			}
			
			
			
		}
		public void setSel(boolean sel)
		{
			this.sel=sel;
		}
		
		public void stopRunning()
		{
		    running = false;
		}
		
	}
}