package modelPlatform;

public class ValuesAssetImpl implements ValuesAsset {
	
	
	double open;
	double high;
	double low;
	double close;
	double volume;
	
	public ValuesAssetImpl(double open,double high,double low,double close,double volume)
	{
		this.open=open;
		this.high=high;
		this.low=low;
		this.close=close;
		this.volume=volume;
	}
	
	public String toString()
	{
		
		return open+" "+high+" "+low+" "+close+" "+volume+"\t";
	}

	@Override
	public double getOpen() {
		// TODO Auto-generated method stub
		return open;
	}

	@Override
	public double getHigh() {
		// TODO Auto-generated method stub
		return high;
	}

	@Override
	public double getLow() {
		// TODO Auto-generated method stub
		return low;
	}

	@Override
	public double getClose() {
		// TODO Auto-generated method stub
		return close;
	}

	@Override
	public double getVolume() {
		// TODO Auto-generated method stub
		return volume;
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
		int a=0;
		int b=5;
		int c=a+b;
		
	}

}
