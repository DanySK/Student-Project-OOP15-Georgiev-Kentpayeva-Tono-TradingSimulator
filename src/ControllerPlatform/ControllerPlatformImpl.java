package ControllerPlatform;

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
		view.drawGraph();
		
	}
	
	
	public void start() {
        if (agent != null) {
            throw new IllegalStateException();
        }
        this.agent = this.new Agent();
        new Thread(this.agent).start();
        ControllerPlatformImpl.this.view.setData(ControllerPlatformImpl.this.model.getFeed());
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
	            }
		 }
	}
	
	private class Agent2 implements Runnable
	{
		 public void run() {
	            while (true) {
	                
	                	ControllerPlatformImpl.this.model.getCandle();//calcolo dei punti del grafico con tempo
	                    
	                	if(ControllerPlatformImpl.this.view.getIsUp()){
	                		System.out.println("---- down --------------");
	                	}
	                	
	                	try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            }
		 }
	}
}