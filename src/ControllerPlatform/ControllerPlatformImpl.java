package ControllerPlatform;


import java.util.Date;

import IndicatoriTecniciModel.IndicatoriFormuleImpl;
import modelPlatform.OptionImpl;
import modelPlatform.Strategy;
import userModel.UserImpl;
import viewPlatform.*;

public class ControllerPlatformImpl implements ControllerPlatform{
	
	private int nAccessi=0;	
	private View view;
	protected UserImpl user=UserImpl.getUs();
	private OptionImpl optin;
	private IndicatoriFormuleImpl form=new IndicatoriFormuleImpl();
	private Strategy modelLine;
	private Strategy modelCandle;	
	
	private Agent agent;
	private Agent2 agente;
	private Opt option;
	
	private boolean avvio=true;
	private volatile boolean sel;;
	
	public ControllerPlatformImpl(View view,Strategy modelLine,Strategy modelCandle){
		
		this.view=view;		
		this.modelLine=modelLine;
		this.modelCandle=modelCandle;	
		this.user.setName("demo");
		
		this.view.addObserver(new Observer(){

			@Override
			public void call() {
				// TODO Auto-generated method stub
				ControllerPlatformImpl.this.sel=true;	
				
				if(avvio){
					//avvio=false;
				// TODO Auto-generated method stub				 
			    if(option != null)
					option.stopRunning();
					 
				ControllerPlatformImpl.this.option=ControllerPlatformImpl.this.new Opt();
			    ControllerPlatformImpl.this.option.setSel(sel);
			    new Thread(ControllerPlatformImpl.this.option).start();
				}
			}

			@Override
			public void put() {
				// TODO Auto-generated method stub
				ControllerPlatformImpl.this.sel=false;
				if(avvio){					
					// TODO Auto-generated method stub				 
					// throw new IllegalStateException();
					if(option != null)
						 option.stopRunning();				
				 
					ControllerPlatformImpl.this.option=ControllerPlatformImpl.this.new Opt();
					ControllerPlatformImpl.this.option.setSel(sel);
					new Thread(ControllerPlatformImpl.this.option).start();			     
				}
			}
		});	
	}
	
	@Override	
	public void start() {
        if (agent != null) {
            throw new IllegalStateException();
        }
        
        this.agent = this.new Agent();
        new Thread(this.agent).start();
    	ControllerPlatformImpl.this.view.setData(
    			ControllerPlatformImpl.this.modelLine.getLineFeed());  	
	}
	
	@Override
	public void start2()
	{
		if(agente!=null)
		{
			throw new IllegalStateException();
		}
		this.agente=this.new Agent2();
		new Thread(this.agente).start();
		ControllerPlatformImpl.this.view.setDataSet(
				ControllerPlatformImpl.this.modelCandle.getOHLCFeed());
	}
	
	private class Agent implements Runnable
	{
		 public void run() {
	            while (true) {                
	            	
	                	ControllerPlatformImpl.this.modelLine.feed();
	                	//System.out.println("--"+ControllerPlatformImpl.this.modelLine.getValue());
	                	ControllerPlatformImpl.this.form.insertValue(ControllerPlatformImpl.this.modelLine.getValue());
	                	ControllerPlatformImpl.this.view.getgraficoCandele().insStocastico(ControllerPlatformImpl.this.form.calcoloStocastico());
	                	ControllerPlatformImpl.this.view.getgraficoCandele().insBolingerInf(ControllerPlatformImpl.this.form.calcoloBandaDiBoolingerInf());
	                	ControllerPlatformImpl.this.view.getgraficoCandele().insBolingerSup(ControllerPlatformImpl.this.form.calcoloBandaDiBoolingerSup());
	                	ControllerPlatformImpl.this.view.getgraficoCandele().insEsp(ControllerPlatformImpl.this.form.calcoloMediaMobilePonderata());
	                	ControllerPlatformImpl.this.view.getgraficoCandele().insMacdDiff(ControllerPlatformImpl.this.form.calcoloMACDDIff());
	                	ControllerPlatformImpl.this.view.getgraficoCandele().insMacdSingle(ControllerPlatformImpl.this.form.calcoloMACDSingle());
	                	ControllerPlatformImpl.this.view.getgraficoCandele().insMediaSeplice(ControllerPlatformImpl.this.form.calcoloMediaMobileSemplice());
	                	
	                	//ControllerPlatformImpl.this.view.graficoALinee.insStocastico(ControllerPlatformImpl.this.form.CalcoloStocastico());
	                	ControllerPlatformImpl.this.view.getgraficoLinea().insBolingerInf(ControllerPlatformImpl.this.form.calcoloBandaDiBoolingerInf());
	                	ControllerPlatformImpl.this.view.getgraficoLinea().insBolingerSup(ControllerPlatformImpl.this.form.calcoloBandaDiBoolingerSup());
	                	ControllerPlatformImpl.this.view.getgraficoLinea().insEsp(ControllerPlatformImpl.this.form.calcoloMediaMobilePonderata());
	                	ControllerPlatformImpl.this.view.getgraficoLinea().insMacdDiff(ControllerPlatformImpl.this.form.calcoloMACDDIff());
	                	ControllerPlatformImpl.this.view.getgraficoLinea().insMacdSingle(ControllerPlatformImpl.this.form.calcoloMACDSingle());
	                	ControllerPlatformImpl.this.view.getgraficoLinea().insMediaSeplice(ControllerPlatformImpl.this.form.calcoloMediaMobileSemplice());
	                	
	                	
	                	
	                	
	                	//migliora la rappresentazione dei dati
	                	try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	
	                	//cambio il grafico da  linea a candele 
	                     if(view.getTipoGrafico().getSelectedItem().toString()=="candele"){
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
	                	ControllerPlatformImpl.this.modelCandle.feed();;//calcolo dei punti del grafico con tempo
	                                  	
	                	try {
	                		//accellero le prime 50 candele in modo che la stampa grafica delle candele sia più uniforme
	                		//(altrimenti le prime si sovrapponerebbero)
	                		if(nAccessi<14){
								Thread.sleep(0,001);
	                		}
	                		else{
	                			Thread.sleep(500);
	                		}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	
	                	//cambio il grafico da candele a linea
	                	 
	                     if(view.getTipoGrafico().getSelectedItem().toString()=="normale"){
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
			double val=ControllerPlatformImpl.this.modelLine.getValue();
			ControllerPlatformImpl.this.optin=new OptionImpl(val,ControllerPlatformImpl.this.view.getPuntata(),new Date());//------------------------------------------
			ControllerPlatformImpl.this.view.setPoint(val);
			ControllerPlatformImpl.this.view.disabilitaBottone();
			try {
				Thread.sleep((long) (ControllerPlatformImpl.this.view.getDurata()*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			val=ControllerPlatformImpl.this.modelLine.getValue();
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
			
			GUI.infoBox(win);
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