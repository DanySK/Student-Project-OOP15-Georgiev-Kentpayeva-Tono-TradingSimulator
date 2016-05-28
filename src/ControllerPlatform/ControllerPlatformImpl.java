package ControllerPlatform;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;


import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;

import userModel.User;
import userModel.UserImpl;
import viewPlatform.CandleStick;
import viewPlatform.GraficiCombinati;
import viewPlatform.ViewPlatform;
import viewPlatform.ViewPlatformImpl;

public class ControllerPlatformImpl{
	
	int DurataDiGioco=20;
	int nAccessi=0;
	
	ModelPlatformImpl model;
	ViewPlatformImpl view;
	Agent agent;
	Agent2 agente;
	
	boolean isCandleStick=true;
	
	public ControllerPlatformImpl(ViewPlatformImpl view,ModelPlatformImpl model)
	{
		this.view=view;
		this.model=model;
		
		
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
	                	 
	                     if(view.getBuy().getTipoGrafico().getSelectedItem().toString()=="candele"){
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
	                		//accellero le prime 50 candele in modo che la stampa grafica delle candele sia pi� uniforme
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
	                	 
	                     if(view.getBuy().getTipoGrafico().getSelectedItem().toString()=="normale"){
	                     	System.out.println("cambio");
	                     	view.changeGraph(false);
	                     }
	            }
		 }
	}
}