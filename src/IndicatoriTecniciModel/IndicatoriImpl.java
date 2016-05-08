package IndicatoriTecniciModel;

import java.util.List;

public class IndicatoriImpl implements Indicatori {

	List<Double> valori=null;
	
	public IndicatoriImpl(List<Double> valori){
		this.valori=valori;
	}
	
	double risult=0;
	
	@Override
	public void CalcoloMediaMobilSemplice() {
		// TODO Auto-generated method stub
		/*Media Mobile Semplice (t) = (P(t) + P(t-1) + P(t-2) + P(t-3) +…) / n  */
		
		this.valori.forEach(e->risult+=e);
		risult=risult/(this.valori.size());
		
	}

	@Override
	public void CalcoloMediaMobilEsponenziale() {
		// TODO Auto-generated method stub
		/*
		Il vantaggio delle Medie Mobili Esponenziali è quello di consentire l’utilizzo di una serie storica piuttosto lunga attribuendo ai dati più recenti maggior peso, 
		ma prendendo comunque in considerazione i dati più lontani nel tempo.

		La differenza sostanziale rispetto al calcolo della Media Mobile Ponderata è che, in questo caso, è necessario definire un parametro (chiamato fattore di decadimento), 
		generalmente compreso tra 0 e 1, il quale consente di attribuire, esponenzialmente e non più linearmente, un peso maggiore ai valori più recenti, senza però annullare del 
		tutto il peso dei valori meno recenti.*/
		
		
		
	}
	
	@Override
	public void CalcoloMediaMobilePonderata() {
		// TODO Auto-generated method stub
		/*Media Mobile Ponderata (t) = (P(10)*(10) + P(9)*(9) + P(8)*(8) + P(7)*(7)…/k  */
		
	}
	
	

	@Override
	public void CalcoloRSI() {
		// TODO Auto-generated method stub
		
		/*RSI = 100 - [ 100 / ( 1 + RS )]
		dove RS è il rapporto dato da:
		RS = media delle ultime n chiusure al rialzo/media delle ultime n chiusure al ribasso.*/
		
		
		
	}

	@Override
	public void CalcoloBandeDiBoolinger() {
		// TODO Auto-generated method stub
		
		/*formula della varianza*/
		
	}

	@Override
	public void CalcoloMACD() {
		// TODO Auto-generated method stub
		/*Come si calcola il MACD
		E’ essenzialmente costituito da due flussi di dati, da due linee: la prima corrisponde alla differenza tra due medie mobili esponenziali, generalmente a 12 e a 26 giorni; la seconda è una media mobile esponenziale a 9 giorni della prima.
		Media mobile esponenziale 12 - Media mobile esponenziale 26 = MACD
		Media mobile esponenziale (MACD) 9 = signal line
		Come si usa il MACD*/

		
		
		
	}

	@Override
	public void CalcoloStocastico() {
		// TODO Auto-generated method stub
		
		/*% K = 100 * [(CHIUSURA - MINn) / (MAXn - MINn)]
		dove:
		MINn = minimo degli ultimi n giorni
		MAXn = massimo degli ultimi n giorni
		CHIUSURA = prezzo di chiusura odierno
		n=12	*/
		
		
		
	}

	@Override
	public void CalcoloSerieFibonacci() {
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

		
		
	}

	
	
}
