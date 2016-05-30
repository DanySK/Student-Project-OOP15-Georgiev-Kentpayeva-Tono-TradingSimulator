package ControllerPlatform;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;


import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;
import modelPlatform.OptionImpl;

import userModel.User;
import userModel.UserImpl;
import viewPlatform.CandleStick;
import viewPlatform.GraficiCombinati;
import viewPlatform.ViewPlatform;
import viewPlatform.ViewPlatformImpl;
//import viewPlatform.uI;
import viewPlatform.*;

public class ControllerPlatformImpl{
	
	int DurataDiGioco=20;
	int nAccessi=0;
	
	ModelPlatformImpl model;
	ViewPlatformImpl view;
	//uI ui;
	UserImpl user;
	
	
	Agent agent;
	Agent2 agente;
	Opt option;
	
	boolean isCandleStick=true;
	boolean avvio=true;
	
	public ControllerPlatformImpl(ViewPlatformImpl view,ModelPlatformImpl model)
	{
		this.view=view;
		this.model=model;
		//this.ui=ui;
		//this.user=user;
		 this.view.addObserver(new Observer(){

			@Override
			public void update() {
				// TODO Auto-generated method stub
				if(avvio){
					//avvio=false;
				// TODO Auto-generated method stub
				 if(option != null)
			        throw new IllegalStateException();
				 
			     ControllerPlatformImpl.this.option=ControllerPlatformImpl.this.new Opt();
			     new Thread(ControllerPlatformImpl.this.option).start();
				}
				
				
				
			}});
		
		
		
		
		view.setCandleStick(isCandleStick);
		//view.drawGraph();
		
		view.setDurataDiGioco(this.DurataDiGioco);
		
		
	}
	 
	
	public void start() {
        if (agent != null) {
            throw new IllegalStateException();
        }
        this.agent = this.new Agent();
        new Thread(this.agent).start();
        ControllerPlatformImpl.this.view.setData(ControllerPlatformImpl.this.model.getFeed());
    
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
	                
	                	ControllerPlatformImpl.this.model.calc();//calcolo dei punti del grafico con tempo
	                    
	                	if(ControllerPlatformImpl.this.view.getIsUp()){
	                		System.out.println("---- down --------------");
	                	}
	                	
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
	                    
	                	if(ControllerPlatformImpl.this.view.getIsUp()){
	                		System.out.println("---- down --------------");
	                	}
	                	
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

		@Override
		public void run() {
			// TODO Auto-generated method stub
			List<OptionImpl> list=new ArrayList<>();
			
			
			
			//System.out.println(Controller.this.view.getStato());
			double val=ControllerPlatformImpl.this.model.getValue();
			
			
			//Controller.this.op=new Option(val,100,new Date());
			list.add(new OptionImpl(val,100,new Date()));
			ControllerPlatformImpl.this.view.set(val);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			val=ControllerPlatformImpl.this.model.getValue();
			if(list.get(0).getVal()<val)
			{
				ControllerPlatformImpl.this.user.setAccountWin(100);
				System.out.println(ControllerPlatformImpl.this.user.getAccount()+"-----------");
			}
			else
			{
				ControllerPlatformImpl.this.user.setAccountWin(100);
				System.out.println(ControllerPlatformImpl.this.user.getAccount()+"-----------");
			}
			
			
			
			
			
		}
		
	}
}