package IndicatoriTecniciModel;

public interface Indicatori {
	
	public double CalcoloMediaMobilSemplice();
	public double CalcoloMediaMobilEsponenziale();
	
	public double CalcoloRSI();
	
	//Bande Di Boolinger
	public double CalcoloBandaDiBoolingerSup();
	public double CalcoloBandaDiBoolingerInf();
	
	//MACD
	public double CalcoloMACDDIff();
	public double CalcoloMACDSingle();
	
	public double CalcoloStocastico();
	
	
}
