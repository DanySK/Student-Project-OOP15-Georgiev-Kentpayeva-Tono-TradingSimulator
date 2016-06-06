package viewPlatform;

import javax.swing.JComboBox;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.ohlc.OHLCSeries;

public interface View extends Observ{

	/**
	 * Avvia la giocata di un giocatore
	 */
	public void AvvioGiocata();
	
	/**
	 * imposta la serie a linee del ASSET selezionato da graficare
	 */
	public void setData(TimeSeries serie);
	/**
	 * imposta la serie a candele del ASSET selezionato da graficare
	 */
	public void setDataSet(OHLCSeries dataset);
	
	/**
	 * cambia il grafico della view da candele a linea e viceversa
	 */
	public void changeGraph(boolean changeToLine);
	
	/**
	 * prende la scelta dell'utente sul tipo di grafico
	 */
	public JComboBox<String> getTipoGrafico();
	
	/**
	 * imposta il valore attuale dell'ASSET all'avvio della giocata 
	 */	
	public void setPoint(Double val);
	
	
	/**
	 * avendo gia premuto il bottone per una giocata ne disabilita il funzionamento
	 */
	public void disabilitaBottone();
	
	/**
	 * riabilita il funzionamento dei bottone
	 */
	public void abilitaBottone();
	
	/**
	 * modifica il valore del conto demo da graficare
	 */
	public void aggiornaConto(String text);
	
	/**
	 * preleva il valore economico di giocata selezionato dall'utente
	 */
	public double getPuntata();

	/**
	 * preleva il valore di durata di giocata selezionato dall'utente
	 */
	public double getDurata();
	
	

	/**
	 * restituisce il grafico a linee
	 */
	public GraficiCombinati getgraficoLinea();
	
	/**
	 * restituisce il grafico a candele
	 */
	public CandleStick getgraficoCandele();
	
	/**
	 * OBSERVER
	 */
	void addObserver(Observer o);
		
	
}
