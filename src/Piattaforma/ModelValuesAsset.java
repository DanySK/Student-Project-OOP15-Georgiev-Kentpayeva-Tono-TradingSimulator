package Piattaforma;

public class ModelValuesAsset {
	
	double open;
	double high;
	double low;
	double close;
	double volume;
	
	public ModelValuesAsset(double open,double high,double low,double close,double volume)
	{
		this.open=open;
		this.high=high;
		this.low=low;
		this.close=close;
		this.volume=volume;
	}
	
	public void tostring()
	{
		System.out.print(open+" "+high+" "+low+" "+close+" "+volume+"\t");
	}

}
