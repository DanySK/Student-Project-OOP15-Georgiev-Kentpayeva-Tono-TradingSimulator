package IndicatoriTecniciModel;

public interface Indicatori {
	
	/**
	 * calcolo della media semplice dei dati da analizzare
	 * @return
	 */
	public double calcoloMediaMobileSemplice();
	
	/**
	 * calcolo della media semplice dei dati da analizzare
	 * @return
	 */
	public double calcoloMediaMobilePonderata();
	
	/**
	 * calcolo RSI dei dati da analizzare
	 * @return
	 */
	public double CalcoloRSI();
	
	/**
	 * calcolo le bande di Boolinger Superiore dei dati da analizzare
	 * @return
	 */
	public double calcoloBandaDiBoolingerSup();
	
	/**
	 * calcolo la bande di Boolinger Inferiore dei dati da analizzare
	 * @return
	 */
	public double calcoloBandaDiBoolingerInf();
	
	/**
	 * calcolo l'indicatore MACD Differenziale dei dati da analizzare
	 * @return
	 */
	public double calcoloMACDDIff();
	
	/**
	 * calcolo  l'indicatore MACD Singolo dei dati da analizzare
	 * @return
	 */
	public double calcoloMACDSingle();
	
	/**
	 * calcolo  l'indicatore Stocastico dei dati da analizzare
	 * @return
	 */
	public double calcoloStocastico();
	
	/**
	 * inserisco il nuovo valore della serie da analizzare
	 * @return
	 */
	public void insertValue(double val);
	
}
