package modelPlatform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

public class ModelPlatformImpl2 implements ModelPlatform {
	
	
	

	

	@Override
	public OHLCSeriesCollection dataFeed() {
		// TODO Auto-generated method stub
		
		String csvFile = "datasrc/DAT_MT_EURUSD_M1_201602.csv";
		BufferedReader br = null;
		String line = "";
		//List<ValuesAssetImpl> value=new ArrayList<>();
		OHLCSeries asset=new OHLCSeries("EUR/USD");
		OHLCSeriesCollection dataset=new OHLCSeriesCollection();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			       
				String[] quote = line.split(",");
				//value.add(new ValuesAssetImpl(Double.parseDouble(quote[2]),Double.parseDouble(quote[3]),Double.parseDouble(quote[4]),Double.parseDouble(quote[5]),Double.parseDouble(quote[6])));
				asset.add(new Millisecond(),Double.parseDouble(quote[2]),Double.parseDouble(quote[3]),Double.parseDouble(quote[4]),Double.parseDouble(quote[5]));
				
				dataset.addSeries(asset);

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
	
	

}
