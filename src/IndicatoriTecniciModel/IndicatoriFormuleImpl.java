package IndicatoriTecniciModel;

import java.util.ArrayList;
import java.util.List;



import org.jfree.data.time.Millisecond;

import org.jfree.data.time.TimeSeries;

public class IndicatoriFormuleImpl implements Indicatori {
	
	//valori della serie da calcolare (i valori devono andare da val[0] il valore piu recente a val[n] (n>0) il piu vecchio)
	List<Double> valori=null;
	//parametro che indicherà il risultato finale
	double resultmSemplice,  resultmediaMobilEsponenziale,resultRSI,
		resultbandaDiBoolingerSup,	resultbandaDiBoolingerInf, resultmACDDIff,
		resultmACDSingle,resultstocastico;
	//serie da graficare
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
	int ind;
	//parametri per il calcolo degli indicatori tecnici
	double RS=0,mRialzo=0,mRibasso=0,chiusura;
	int ngg=12;
	double fattore=1;
	int n=0,k=0;	
	double lastValue=-999, minimo,massimo;
	List<Double> valRialzo=null,valRibasso=null;
	

	public IndicatoriFormuleImpl(){
		this.valori=new ArrayList<>();//this.invertiLista(valori);
		media=new TimeSeries("media");
		mediaMobilSemplice=new TimeSeries("");
	    mediaMobilEsponenziale=new TimeSeries("");			
	    CalcoloRSI=new TimeSeries("");
		//Bande Di Boolinger
	    bandaDiBoolingerSup=new TimeSeries("");
		bandaDiBoolingerInf=new TimeSeries("");
		//MACD
		mACDDIff=new TimeSeries("");
		mACDSingle=new TimeSeries("");
		stocastico=new TimeSeries("");
		
		valRialzo=new ArrayList<>();
		valRibasso=new ArrayList<>();
		
	}
	// mipermette di invertire i valori più recenti con i più vecchi
	public List<Double> invertiLista(List<Double> valori){		
		List<Double> out= new ArrayList<>();		
		ind=valori.size();
		while(ind==0){
			out.add(valori.get(ind-1));
		}		
		return out;		
	}
	
	
	@Override
	public double CalcoloMediaMobilSemplice() {
		// TODO Auto-generated method stub
		/*Media Mobile Semplice (t) = (P(t) + P(t-1) + P(t-2) + P(t-3) +…) / n  */			
		resultmSemplice=0;
		this.valori.forEach(e->resultmSemplice+=e);
		resultmSemplice=resultmSemplice/(this.valori.size());		
		//this.mediaMobilSemplice.add(new Millisecond(),resultmSemplice);		
		return resultmSemplice;
	}
	
	//override del metodo per calcolarlo su una lista specifica	
	public double CalcoloMediaMobilSemplice(List<Double> valori) {
		// TODO Auto-generated method stub	
		resultmSemplice=0;
		valori.forEach(e->resultmSemplice+=e);
		resultmSemplice=resultmSemplice/(this.valori.size());		
		return resultmSemplice;
	}	
		
	@Override
	public double CalcoloMediaMobilEsponenziale() {
		// TODO Auto-generated method stub
		/*
		Il vantaggio delle Medie Mobili Esponenziali è quello di consentire l’utilizzo di una serie storica piuttosto lunga attribuendo ai dati più recenti maggior peso, 
		ma prendendo comunque in considerazione i dati più lontani nel tempo.

		La differenza sostanziale rispetto al calcolo della Media Mobile Ponderata è che, in questo caso, è necessario definire un parametro (chiamato fattore di decadimento), 
		generalmente compreso tra 0 e 1, il quale consente di attribuire, esponenzialmente e non più linearmente, un peso maggiore ai valori più recenti, senza però annullare del 
		tutto il peso dei valori meno recenti.*/
		resultmediaMobilEsponenziale=0;
		
		this.valori.forEach(e->{
			if(n> (this.valori.size()/2) ){
				fattore=0.5;
			}
			else{
				fattore=1;
			}
			resultmediaMobilEsponenziale+=e*fattore;
			n++;
			k+=fattore;
		});		
		resultmediaMobilEsponenziale=resultmediaMobilEsponenziale/k;		
		//this.mediaMobilEsponenziale.add(new Millisecond(),resultmediaMobilEsponenziale);
		return resultmediaMobilEsponenziale;		
	}	
	
	@Override
	public double CalcoloRSI() {
		// TODO Auto-generated method stub
		
		/*RSI = 100 - [ 100 / ( 1 + RS )]
		dove RS è il rapporto dato da:
		RS = media delle ultime n chiusure al rialzo/media delle ultime n chiusure al ribasso.
		n=12 */		
		//il 1°valore della serie lo considero in rialzo
		resultRSI=0;
		this.lastValue=this.valori.get(0);
		this.valori.forEach(e->{					
			//individuo se il valore è in rialzo rispetto a quello precedente o in ribasso						
			if(this.lastValue<e){
				this.valRialzo.add(this.lastValue);
			}
			else{
				this.valRibasso.add(this.lastValue);
			}					
			this.lastValue=e;				
		});			
		this.mRialzo=this.CalcoloMediaMobilSemplice(this.valRialzo);
		this.mRibasso=this.CalcoloMediaMobilSemplice(this.valRialzo);		
		this.RS=this.mRialzo/this.mRibasso;		
		resultRSI=100 - ( 100 / ( 1 + RS ));		
		//this.CalcoloRSI.add(new Millisecond(),resultRSI);		
		return resultRSI;		
	}
	
	
	//
	@Override
	public double CalcoloBandaDiBoolingerSup() {
		// TODO Auto-generated method stub
		
		/*L’applicazione delle bande di Bollinger ad un grafico richiede innanzitutto la costruzione di una media mobile. 
		Tale media viene poi traslata verso l’alto (banda superiore) e verso il basso (banda inferiore) di una distanza 
		spesso pari al doppio della deviazione standard. */		
		resultbandaDiBoolingerSup=this.CalcoloMediaMobilSemplice(this.valori)+2*this.DeviazioneStandard(this.valori);
		//this.bandaDiBoolingerSup.add(new Millisecond(),resultbandaDiBoolingerSup);
		//this.bandaDiBoolingerInf.addOrUpdate(new Millisecond(),this.CalcoloBandaDiBoolingerInf());		
		
		return resultbandaDiBoolingerSup;		
	}
	
	@Override
	public double CalcoloBandaDiBoolingerInf() {
		// TODO Auto-generated method stub		
		resultbandaDiBoolingerInf=0;
		resultbandaDiBoolingerInf=this.CalcoloMediaMobilSemplice(this.valori)-2*this.DeviazioneStandard(this.valori);
		//this.bandaDiBoolingerInf.add(new Millisecond(),resultbandaDiBoolingerInf);		
		//this.bandaDiBoolingerSup.addOrUpdate(new Millisecond(),this.CalcoloBandaDiBoolingerSup());
		return resultbandaDiBoolingerInf;		
	}
	
	
	private double DeviazioneStandard(List<Double> valori){		
		double sigma=0;
		sigma = Math.sqrt(  ( Math.pow(this.CalcoloMediaMobilSemplice(this.valori),2))  /  (this.valori.size()-1) );		
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
		resultmACDDIff=0;
		
		resultmACDDIff=this.CalcoloMediaMobilEsponenziale(12)-this.CalcoloMediaMobilEsponenziale(26);
		//this.mACDDIff.add(new Millisecond(),resultmACDDIff);		
		//this.mACDSingle.add(new Millisecond(),this.CalcoloMACDSingle());		
		return resultmACDDIff;		
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
		resultmACDSingle=0;
		
		resultmACDSingle=this.CalcoloMediaMobilEsponenziale(9);
		//this.mACDSingle.add(new Millisecond(),resultmACDSingle);		
		//this.mACDDIff.addOrUpdate(new Millisecond(),resultmACDSingle);		
		return resultmACDSingle;
		
	}
	
	//per calcolare il MACD
	
	public double CalcoloMediaMobilEsponenziale(int Deltagg) {
		// TODO Auto-generated method stub
		/*Il vantaggio delle Medie Mobili Esponenziali è quello di consentire l’utilizzo di una serie storica piuttosto lunga attribuendo ai dati più recenti maggior peso, 
		ma prendendo comunque in considerazione i dati più lontani nel tempo.
		La differenza sostanziale rispetto al calcolo della Media Mobile Ponderata è che, in questo caso, è necessario definire un parametro (chiamato fattore di decadimento), 
		generalmente compreso tra 0 e 1, il quale consente di attribuire, esponenzialmente e non più linearmente, un peso maggiore ai valori più recenti, senza però annullare del 
		tutto il peso dei valori meno recenti.*/
		resultmediaMobilEsponenziale=0;
		
		n=0;
		k=0;		
		this.valori.forEach(e->{
			if(n<Deltagg){
				if(n> (this.valori.size()/2) ){
					fattore=0.5;
				}
				else{
					fattore=1;
				}
			}
			resultmediaMobilEsponenziale+=e*fattore;
			n++;
			k+=fattore;
		});		
		resultmediaMobilEsponenziale=resultmediaMobilEsponenziale/k;		
		return resultmediaMobilEsponenziale;		
	}

	
	@Override
	public double CalcoloStocastico(){
		// TODO Auto-generated method stub
		
		/*% K = 100 * [(CHIUSURA - MINn) / (MAXn - MINn)]
		dove:
		MINn = minimo degli ultimi n giorni
		MAXn = massimo degli ultimi n giorni
		CHIUSURA = prezzo di chiusura odierno
		n=12	*/		
		//DA GUARDARE
		//chiusura=this.valori.get(this.valori.size()-1);
		//cerco il minimo
		resultstocastico=0;
		
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
		resultstocastico=100 * (   (chiusura - minimo) / (massimo - minimo)   );
		this.stocastico.add(new Millisecond(),resultstocastico);	
		return resultstocastico;
		
	}
	
	private double min(double a, double b){
		return (a<b) ? a : b;
	}

	private double max(double a, double b){
		return (a>b) ? a : b;
	}
	
	//getter per rilasciare i valori da graficare
	public TimeSeries getMediaSemplice(){
		return this.mediaMobilSemplice;
	}
	
	public TimeSeries getEsp(){
		return this.mediaMobilEsponenziale;
	}
	
	public TimeSeries getRsi(){
		return this.CalcoloRSI;
	}
	
	public TimeSeries getBolingerSup(){
		return this.bandaDiBoolingerSup;
	}
	
	public TimeSeries getBolingerInf(){
		return this.bandaDiBoolingerInf;
	}	
	
	public TimeSeries getMacdDiff()	{
		return this.mACDDIff;		
	}	
	public TimeSeries getMacdSingle(){
		return this.mACDSingle;	
	}
	
	public TimeSeries getStocastico(){
		return this.stocastico;
	}
	//metodo per l'inserimento del nuovo valore
	@Override	
	public void insertValue(double value){
		// TODO Auto-generated method stub		
		valori.add(value);
		
	}
}