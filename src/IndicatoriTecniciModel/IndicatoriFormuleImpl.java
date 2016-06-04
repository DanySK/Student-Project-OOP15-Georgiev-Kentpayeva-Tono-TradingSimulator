package IndicatoriTecniciModel;


import java.util.ArrayList;
import java.util.List;



import org.jfree.data.time.Millisecond;

import org.jfree.data.time.TimeSeries;


public class IndicatoriFormuleImpl implements Indicatori {

	//i valori devono andare da val[0] il valore piu recente a val[n] (n>0) il piu vecchio
	List<Double> valori=null;
	double result=0;
	int ris;
	TimeSeries media;
	TimeSeries mediaMobilSemplice;
    TimeSeries  mediaMobilEsponenziale;
		
		
		
		
		TimeSeries  CalcoloRSI;
		
		//Bande Di Boolinger
		TimeSeries  bandaDiBoolingerSup;
		TimeSeries  bandaDiBoolingerInf;
		
		//MACD
		TimeSeries mACDDIff;
		TimeSeries mACDSingle;
		
		TimeSeries  stocastico;
	
	public IndicatoriFormuleImpl(){
		this.valori=new ArrayList<>();//this.invertiLista(valori);
		media=new TimeSeries("media");
		 mediaMobilSemplice=new TimeSeries("media");
	     mediaMobilEsponenziale=new TimeSeries("media");
			
			
			
			
			 CalcoloRSI=new TimeSeries("media");
			
			//Bande Di Boolinger
			 bandaDiBoolingerSup=new TimeSeries("media");
			 bandaDiBoolingerInf=new TimeSeries("media");
			
			//MACD
			mACDDIff=new TimeSeries("media");
			 mACDSingle=new TimeSeries("media");
			
			 stocastico=new TimeSeries("media");
		
		
		
	}
	
	
	

	

	
	public List<Double> invertiLista(List<Double> valori){
		
		List<Double> out= new ArrayList<>();
		
		int ind=valori.size();
		while(ind==0){
			out.add(valori.get(ind-1));
		}
		
		return out;
		
	}
	
	
	@Override
	public double CalcoloMediaMobilSemplice() {
		// TODO Auto-generated method stub
		/*Media Mobile Semplice (t) = (P(t) + P(t-1) + P(t-2) + P(t-3) +…) / n  */
		
		
		this.valori.forEach(e->result+=e);
		result=result/(this.valori.size());
		
		
		
		this.mediaMobilSemplice.add(new Millisecond(),5.0);
		
		return result;
	}
	
	//uguale all'altro metodo ma con la lista come argomento di input
	
	public double CalcoloMediaMobilSemplice2(List<Double> valori) {
		// TODO Auto-generated method stub
		/*Media Mobile Semplice (t) = (P(t) + P(t-1) + P(t-2) + P(t-3) +…) / n  */
		
		valori.forEach(e->result+=e);
		result=result/(this.valori.size());
		
		
		return result;
	}

	
	double fattore=1;
	int n=0,k=0;
	
	double lastValue=-999;
	List<Double> valRialzo=null,valRibasso=null;
	
	
	@Override
	public double CalcoloMediaMobilEsponenziale() {
		// TODO Auto-generated method stub
		/*
		Il vantaggio delle Medie Mobili Esponenziali è quello di consentire l’utilizzo di una serie storica piuttosto lunga attribuendo ai dati più recenti maggior peso, 
		ma prendendo comunque in considerazione i dati più lontani nel tempo.

		La differenza sostanziale rispetto al calcolo della Media Mobile Ponderata è che, in questo caso, è necessario definire un parametro (chiamato fattore di decadimento), 
		generalmente compreso tra 0 e 1, il quale consente di attribuire, esponenzialmente e non più linearmente, un peso maggiore ai valori più recenti, senza però annullare del 
		tutto il peso dei valori meno recenti.*/
		
		
		
		this.valori.forEach(e->{
			if(n> (this.valori.size()/2) ){
				fattore=0.5;
			}
			else{
				fattore=1;
			}
			result+=e*fattore;
			n++;
			k+=fattore;
		});
		
		result=result/k;
		
		this.mediaMobilEsponenziale.add(new Millisecond(),result);
		return result;
		
		
	}
	
	
	

	
	
	double RS=0,mRialzo=0,mRibasso=0;
	
	@Override
	public double CalcoloRSI() {
		// TODO Auto-generated method stub
		
		/*RSI = 100 - [ 100 / ( 1 + RS )]
		dove RS è il rapporto dato da:
		RS = media delle ultime n chiusure al rialzo/media delle ultime n chiusure al ribasso.
		n=12 */
		
		//il 1°valore della serie lo considero in rialzo
		this.valori.forEach(e->{
					
			//individuo se il valore è in rialzo rispetto a quello precedente o in ribasso
					
			if(this.lastValue<e){
				this.valRialzo.add(5.0);
			}
			else{
				this.valRibasso.add(5.0);
			}
					
			this.lastValue=e;
						
			this.CalcoloRSI.add(new Millisecond(),result);		
					
		});
				
		this.mRialzo=this.CalcoloMediaMobilSemplice2(this.valRialzo);
		this.mRibasso=this.CalcoloMediaMobilSemplice2(this.valRialzo);
		
		this.RS=this.mRialzo/this.mRibasso;
		
		result=100 - ( 100 / ( 1 + RS ));
		
		this.CalcoloRSI.add(new Millisecond(),result);
		
		return result;
		
	}
	
	
	//
	@Override
	public double CalcoloBandaDiBoolingerSup() {
		// TODO Auto-generated method stub
		
		/*L’applicazione delle bande di Bollinger ad un grafico richiede innanzitutto la costruzione di una media mobile. 
		Tale media viene poi traslata verso l’alto (banda superiore) e verso il basso (banda inferiore) di una distanza 
		spesso pari al doppio della deviazione standard. */
		
		result=this.CalcoloMediaMobilSemplice()+2*this.DeviazioneStandard(this.valori);
		this.bandaDiBoolingerSup.add(new Millisecond(),result);
		return result;
		
	}
	
	@Override
	public double CalcoloBandaDiBoolingerInf() {
		// TODO Auto-generated method stub
		
		
		result=this.CalcoloMediaMobilSemplice()-2*this.DeviazioneStandard(this.valori);
		this.bandaDiBoolingerInf.add(new Millisecond(),result);
		
		return result;
		
	}
	
	
	private double DeviazioneStandard(List<Double> valori){
		
		double sigma=0;

		sigma = Math.sqrt(  ( Math.pow(this.CalcoloMediaMobilSemplice(),2))  /  (this.valori.size()-1) );
		
		return sigma;
	}

	
	//MACD
	@Override
	public double CalcoloMACDDIff() {
		// TODO Auto-generated method stub
		/*Come si calcola il MACD
		E’ essenzialmente costituito da due flussi di dati, da due linee: 
		
		-->la prima corrisponde alla differenza tra due medie mobili esponenziali, 
		generalmente a 12 e a 26 giorni; 
		
		la seconda è una media mobile esponenziale a 9 giorni della prima.
		
		-->Media mobile esponenziale 12 - Media mobile esponenziale 26 = MACD
		Media mobile esponenziale (MACD) 9 = signal line
		Come si usa il MACD*/
		
		result=this.CalcoloMediaMobilEsponenziale(12)-this.CalcoloMediaMobilEsponenziale(26);
		this.mACDDIff.add(new Millisecond(),result);
		
		return result;
		
	}
	
	@Override
	public double CalcoloMACDSingle() {
		// TODO Auto-generated method stub
		/*Come si calcola il MACD
		E’ essenzialmente costituito da due flussi di dati, da due linee: 
		
		la prima corrisponde alla differenza tra due medie mobili esponenziali, 
		generalmente a 12 e a 26 giorni; 
		
		--> la seconda è una media mobile esponenziale a 9 giorni della prima.
		
		Media mobile esponenziale 12 - Media mobile esponenziale 26 = MACD
		-->Media mobile esponenziale (MACD) 9 = signal line
		Come si usa il MACD*/
		
		result=this.CalcoloMediaMobilEsponenziale(9);
		this.mACDSingle.add(new Millisecond(),result);
		
		return result;
		
	}
	
	//per calcolare il MACD
	
	public double CalcoloMediaMobilEsponenziale(int Deltagg) {
		// TODO Auto-generated method stub
		/*
		Il vantaggio delle Medie Mobili Esponenziali è quello di consentire l’utilizzo di una serie storica piuttosto lunga attribuendo ai dati più recenti maggior peso, 
		ma prendendo comunque in considerazione i dati più lontani nel tempo.

		La differenza sostanziale rispetto al calcolo della Media Mobile Ponderata è che, in questo caso, è necessario definire un parametro (chiamato fattore di decadimento), 
		generalmente compreso tra 0 e 1, il quale consente di attribuire, esponenzialmente e non più linearmente, un peso maggiore ai valori più recenti, senza però annullare del 
		tutto il peso dei valori meno recenti.*/
		
		n=0;
		
		this.valori.forEach(e->{
			if(n<Deltagg){
				if(n> (this.valori.size()/2) ){
					fattore=0.5;
				}
				else{
					fattore=1;
				}
			}
			result+=e*fattore;
			n++;
			k+=fattore;
		});
		
		result=result/k;
		
		
		return result;
		
		
	}

	
	int ngg=12;
	double chiusura;

	@Override
	public double CalcoloStocastico() {
		// TODO Auto-generated method stub
		
		/*% K = 100 * [(CHIUSURA - MINn) / (MAXn - MINn)]
		dove:
		MINn = minimo degli ultimi n giorni
		MAXn = massimo degli ultimi n giorni
		CHIUSURA = prezzo di chiusura odierno
		n=12	*/
		
		double minimo,massimo;
		//DA GUARDARE
		//chiusura=this.valori.get(this.valori.size()-1);
		//cerco il minimo
		
		this.lastValue=-999;
		
		this.valori.forEach(e->{
			this.lastValue=min(this.lastValue,e);
		});
		minimo=this.lastValue;
		
		//cerco il minimo
		
		this.lastValue=999;
			
		this.valori.forEach(e->{
			this.lastValue=max(this.lastValue,e);
		});
		massimo=this.lastValue;
		
		//Stocastico
		result=100 * (   (chiusura - minimo) / (massimo - minimo)   );
		this.stocastico.add(new Millisecond(),result);
				
		
		
		return result;
		
	}
	
	private double min(double a, double b){
		return (a<b) ? a : b;
	}

	private double max(double a, double b){
		return (a>b) ? a : b;
	}

	

	/*public void insertValori(double value){
		this.valori.add(value);
	}*/
	public TimeSeries getMediaSemplice()
	{
		return this.mediaMobilSemplice;
		
	}
	
	public TimeSeries getEsp()
	{
		return this.mediaMobilEsponenziale;
		
	}
	
	public TimeSeries getRsi()
	{
		return this.CalcoloRSI;
		
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
	

	@Override	
	public void insertValue(double value) {
		// TODO Auto-generated method stub
		
		valori.add(value);
		//md=MovingAverage.createMovingAverage(this.media, "prova", 8, 8);
	}

	
	
}
