package modelPlatform;

public class ValuesAssetImpl {
	
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
	
	

}
