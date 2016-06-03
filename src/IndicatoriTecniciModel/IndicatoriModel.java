package IndicatoriTecniciModel;




import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;





import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.Series;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

import org.jfree.data.time.ohlc.OHLCSeries;





public class IndicatoriModel {
	
	List<Double> valori=null;//new ArrayList<>();
	
	TimeSeries mediaMobilSemplice=null;
	TimeSeries  mediaMobilEsponenziale=null;
	
	TimeSeries  mediaMobilePonderata=null;
	
	
	TimeSeries  CalcoloRSI=null;
	
	//Bande Di Boolinger
	TimeSeries  bandaDiBoolingerSup=null;
	TimeSeries  bandaDiBoolingerInf=null;
	
	//MACD
	TimeSeries mACDDIff=null;
	TimeSeries mACDSingle=null;
	
	TimeSeries  stocastico=null;
	
	TimeSeries  serieFibonacci=null;
	

	Indicatori indicatoriFormule=new IndicatoriFormuleImpl();
	
	boolean start=true;
	public boolean isUpDateModel=false;
	private boolean isUp=false;
	//Timer timer=new Timer(10, null);
	TimeSeries serie;
	
	private double lastValue1=100.0;
	
	double result=0;
	
	

	String csvFile = "datasrc/data.csv";
	BufferedReader br = null;
	BufferedReader in = null;
	private int count=0;
	List<String> lista;
	List<String> list;
	
	String line = "";
	//List<ValuesAssetImpl> value=new ArrayList<>();
	Series asset=null;
	OHLCSeries cs;
	AbstractSeriesDataset dataset=null;
	
	
	boolean ok=true;

	boolean isCandleStick=true;
	
	public IndicatoriModel()
	{
		
		
		 serie=new TimeSeries("random");
		 lista=new ArrayList<>();
		 list=new ArrayList<>();
		 cs=new OHLCSeries("rnd2");
		 valori=new ArrayList();
		//timer.start();
		 

			mediaMobilSemplice=new TimeSeries("random");
			mediaMobilEsponenziale=new TimeSeries("random");
		    mediaMobilePonderata=new TimeSeries("random");
			
			
			CalcoloRSI=new TimeSeries("random");;
			
			//Bande Di Boolinger
			bandaDiBoolingerSup=new TimeSeries("random");
			bandaDiBoolingerInf=new TimeSeries("random");
			
			//MACD
			mACDDIff=new TimeSeries("random");
			mACDSingle=new TimeSeries("random");
			
			stocastico=new TimeSeries("random");
			serieFibonacci=new TimeSeries("random");
			
	}
	
	

	


	public void insertValue(){//calc
		//this.valori.add(value);
		
		//aggiorno le formule
		this.mediaMobilSemplice.add(new Millisecond(),this.indicatoriFormule.CalcoloMediaMobilSemplice());
		this.mediaMobilEsponenziale.add(new Millisecond(),this.indicatoriFormule.CalcoloMediaMobilEsponenziale());
		
		//this.mediaMobilePonderata.add(new Millisecond(),this.indicatoriFormule.CalcoloMediaMobilePonderata());
		
		
		this.CalcoloRSI.add(new Millisecond(),this.indicatoriFormule.CalcoloRSI());
		
		//Bande Di Boolinger
		this.bandaDiBoolingerSup.add(new Millisecond(),this.indicatoriFormule.CalcoloBandaDiBoolingerSup());
		this.bandaDiBoolingerInf.add(new Millisecond(),this.indicatoriFormule.CalcoloBandaDiBoolingerInf());
		
		//MACD
		this.mACDDIff.add(new Millisecond(),this.indicatoriFormule.CalcoloMACDDIff());
		this.mACDSingle.add(new Millisecond(),this.indicatoriFormule.CalcoloMACDSingle());
		
		this.stocastico.add(new Millisecond(),this.indicatoriFormule.CalcoloStocastico());
		
		
	}
	
	//--------------------------------------------------------------------------------------------------------------
	public TimeSeries getSemplice()
	{
		return this.mediaMobilSemplice;
		
	}
	
	public TimeSeries getEsp()
	{
		return this.mediaMobilEsponenziale;
		
	}
	
	public TimeSeries getPonderata()
	{
		return this.mediaMobilePonderata;
		
	}
	
	public TimeSeries getBolingerSup()
	{
		return this.bandaDiBoolingerSup;
		
	}
	
	public TimeSeries getBolingerInf()
	{
		return this.bandaDiBoolingerInf;
		
	}
	
	public TimeSeries getMacdDiff()
	{
		return this.mACDDIff;
		
	}
	
	public TimeSeries getMacdSingle()
	{
		return this.mACDSingle;
		
	}
	

	
	public TimeSeries getStocastico()
	{
		return this.stocastico;
		
	}
}