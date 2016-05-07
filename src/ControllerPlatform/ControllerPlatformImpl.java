package ControllerPlatform;

import java.util.List;

import org.jfree.data.general.AbstractSeriesDataset;
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
	public ControllerPlatformImpl(ViewPlatformImpl view,ModelPlatformImpl model)
	{
		this.view=view;
		this.model=model;
	}
	
	
	public void start() {
        if (agent != null) {
            throw new IllegalStateException();
        }
        this.agent = this.new Agent();
        new Thread(this.agent).start();
        ControllerPlatformImpl.this.view.setData(ControllerPlatformImpl.this.model.getFeed());
    }
	
	private class Agent implements Runnable
	{
		 public void run() {
	            while (true) {
	                
	                	ControllerPlatformImpl.this.model.calc();//calcolo dei punti del grafico con tempo
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