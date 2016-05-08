package IndicatoriTecniciModel;

import java.util.ArrayList;
import java.util.List;

public class IndicatoriImpl implements Indicatori {

	//i valori devono andare da val[0] il valore piu recente a val[n] (n>0) il piu vecchio
	List<Double> valori=null;
	double result=0;
	
	
	public IndicatoriImpl(List<Double> valori){
		this.valori=this.invertiLista(valori);
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
		
		
		return result;
		
		
	}
	
	
	
	@Override
	public double CalcoloMediaMobilePonderata() {
		// TODO Auto-generated method stub
		/*Media Mobile Ponderata (t) = (P(10)*(10) + P(9)*(9) + P(8)*(8) + P(7)*(7)…/k  */
		
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
				this.valRialzo.add(e);
			}
			else{
				this.valRibasso.add(e);
			}
					
			this.lastValue=e;
						
					
					
		});
				
		this.mRialzo=this.CalcoloMediaMobilSemplice2(this.valRialzo);
		this.mRibasso=this.CalcoloMediaMobilSemplice2(this.valRialzo);
		
		this.RS=this.mRialzo/this.mRibasso;
		
		result=100 - ( 100 / ( 1 + RS ));
		
		return result;
		
	}
	
	
	//
	@Override
	public double CalcoloBandaDiBoolingerSup() {
		// TODO Auto-generated method stub
		
		/*L’applicazione delle bande di Bollinger ad un grafico richiede innanzitutto la costruzione di una media mobile. 
		Tale media viene poi traslata verso l’alto (banda superiore) e verso il basso (banda inferiore) di una distanza 
		spesso pari al doppio della deviazione standard. */
		
		result=this.CalcoloMediaMobilSemplice()+2*this.DeviazioneStandard(this.valori);;
		return result;
		
	}
	
	@Override
	public double CalcoloBandaDiBoolingerInf() {
		// TODO Auto-generated method stub
		
		
		result=this.CalcoloMediaMobilSemplice()-2*this.DeviazioneStandard(this.valori);;
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
		
		chiusura=this.valori.get(this.valori.size()-1);
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
				
		
		
		return result;
		
	}
	
	private double min(double a, double b){
		return (a<b) ? a : b;
	}

	private double max(double a, double b){
		return (a>b) ? a : b;
	}

	@Override
	public double CalcoloSerieFibonacci() {
		// TODO Auto-generated method stub
		/*“sezione aurea”: il rapporto tra due numeri tra loro consecutivi, man mano che si procede nel calcolo lungo la serie, tende ad avvicinarsi sempre di più al cosiddetto 
		 coefficiente aureo: 1,618. Di seguito viene riportata una breve dimostrazione:
		 

		2 / 1 = 2
		3 / 2 = 1,5
		5 / 3 = 1,66666
		8 / 5 = 1,6
		13 / 8 = 1,625
		21 / 13 = 1,61538

		Come usare la serie di Fibonacci in analisi tecnica

		La sezione aurea si può ritrovare anche nell’analisi tecnica; proprio a partire dal coefficiente aureo vengono infatti ricavate le “percentuali di Fibonacci”:
		61,8%
		50%
		38,2%
	
		Frequentemente accade che il ritracciamento da un massimo o da un minimo rilevante possa trovare una valida resistenza proprio al livello corrispondente ad una delle
		percentuali citate.*/

		return result;
		
	}

	
	
}
