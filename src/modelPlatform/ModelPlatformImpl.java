package modelPlatform;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import org.jfree.data.ComparableObjectSeries;
import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.Series;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

import viewPlatform.ViewPlatformImpl;

public class ModelPlatformImpl implements ModelPlatform {
	
	
	public boolean isUpDateModel=false;
	private boolean isUp=false;
	Timer timer=new Timer(0, null);


	String csvFile = "datasrc/DAT_MT_EURUSD_M1_201602.csv";
	BufferedReader br = null;
	String line = "";
	//List<ValuesAssetImpl> value=new ArrayList<>();
	Series asset=null;
	AbstractSeriesDataset dataset=null;
	
	
	boolean ok=true;

	boolean isCandleStick=true;

	@Override
	public AbstractSeriesDataset dataFeed(boolean isCandleStick) {
		// TODO Auto-generated method stub
		timer.start();
		
		//System.out.println("model-ok");
		
		
		if(isCandleStick){
			asset= new OHLCSeries("EUR/USD");
			dataset=new OHLCSeriesCollection();
		}
		else{
			asset= new TimeSeries("EUR/USD");
			dataset=new TimeSeriesCollection();
		}

		
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			       
				String[] quote = line.split(",");
				//value.add(new ValuesAssetImpl(Double.parseDouble(quote[2]),Double.parseDouble(quote[3]),Double.parseDouble(quote[4]),Double.parseDouble(quote[5]),Double.parseDouble(quote[6])));
				//asset.add(new Millisecond(),Double.parseDouble(quote[2]),Double.parseDouble(quote[3]),Double.parseDouble(quote[4]),Double.parseDouble(quote[5]));
				
				//asset.add(new Millisecond(),20,6,8,10);
				
				if(isUpDateModel==true && ok){
					//System.out.println("bene3");
					//asset.add(new Millisecond(),Double.parseDouble(quote[2]),Double.parseDouble(quote[3]),Double.parseDouble(quote[4]),Double.parseDouble(quote[5]));
					
					if(isCandleStick && ok){
						((OHLCSeries) asset).add(new Millisecond(),Double.parseDouble(quote[2]),Double.parseDouble(quote[3]),Double.parseDouble(quote[4]),Double.parseDouble(quote[5]));
						//System.out.println("bene4");
						ok=false;
						
					}
					else{
						((TimeSeries) asset).add(new Millisecond(),300);
						
					}
					
				}
				else{
					//System.out.println("male3");
					
					//ok=true;
					
				}
				
				
				
				if(isCandleStick){
					//System.out.println("errore?");
					((OHLCSeriesCollection) dataset).addSeries((OHLCSeries) asset);

				}
				else{
					//System.out.println("????");
					
					((TimeSeriesCollection) dataset).addSeries((TimeSeries) asset);

				}
				//dataset.addSeries(asset);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		return dataset;
	}

	@Override
	public void setIsUp(boolean IsUp) {
		// TODO Auto-generated method stub
		System.out.println("--------------------------------------------"+isUp);
		
		this.isUp=IsUp;
		
		
		
	}

	@Override
	public boolean getIsUp() {
		// TODO Auto-generated method stub
		return this.isUp;
	}
	
	

}
