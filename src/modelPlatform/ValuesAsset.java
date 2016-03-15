package modelPlatform;

public class ValuesAsset {
	
	double open;
	double high;
	double low;
	double close;
	double volume;
	
	public ValuesAsset(double open,double high,double low,double close,double volume)
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
