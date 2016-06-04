package viewPlatform;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

public class GUI extends JFrame implements Observ{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TimeSeriesCollection dataset=null;
	OHLCSeriesCollection datasetCandle=null;
	/*buy*/
	/*per le combo box*/
	private static final String[] 	ASSET = {"EUR/USD"},
									DURATE = {"10","20","30","60"},
									GRAFICI = {"normale", "candele"},
									INDICATORI = {"Medie Mobili","Medie Mobili Esponenziali","MACD Diff","MACD Single","Stocastico", 
													"Calendario Economico"},
									PUNTATE = {"10","20","30","50","100"};
			
	String tipoOp="",tipoGrafico="",tipoIndicatore="";		
	//------------------------------
	/*user interface*/
	private final Set<Observer> observers;
	
	private int conto=10000;
	//elementi grafici
	private final List<JComboBox<String>> userChoose=new ArrayList<>();	
	private final List<JComboBox<String>> playChoose=new ArrayList<>();	
	
	JButton up= new JButton("UP");
	JButton down= new JButton("DOWN");
	JLabel punto;
	JLabel lImporto=new JLabel("importo: $");
	JLabel lContoDemoVal=null;
	
	public GraficiCombinati graficoALinee= new GraficiCombinati("MSFT");
	public CandleStick graficoACandele=new CandleStick("MSFT");	
		
	
	
	public GUI(){		
		
		super("Trading Platoform");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		//imposto la dimensione dell'intefaccia grafica a quella dello schermo
      	
        
        //elementi grafici
        JPanel canvasGraphLinee =new JPanel();
    	JPanel canvasUI =new JPanel();
    	JPanel canvasBuy =new JPanel();
    	JPanel canvasGraphCandle =new JPanel();        
        JPanel canvas =new JPanel();		
        JLabel lContoDemo=new JLabel("CONTO DEMO: ");
		JLabel percBroker=new JLabel("% Broker: ");
		JLabel lGuadagnoVal=new JLabel(88+" %");
		
		
		/*buy-------------------*/
        
		/*elementi per combobox*/
		canvasBuy.setLayout(new GridLayout(0,2));
		/*asset scelto*/
		canvasBuy.add(new JLabel("ASSET: "));
        this.userChoose.add(new JComboBox<String>(ASSET));
        canvasBuy.add(this.userChoose.get(0),BorderLayout.NORTH);
        
        /*tipi di grafo: candele,normale*/
        canvasBuy.add(new JLabel("Grafico: "));
        this.userChoose.add(new JComboBox<String>(GRAFICI));
        canvasBuy.add(this.userChoose.get(1),BorderLayout.CENTER);

        /*tipi di grafo: candele,normale*/
        canvasBuy.add(new JLabel("Indicatore Tecnico: "));
        this.userChoose.add(new JComboBox<String>(INDICATORI));
        canvasBuy.add(this.userChoose.get(2),BorderLayout.SOUTH);

		
		//-----------------------------		
		/*user interface*/
        
        /*puntata scelta*/
        canvasUI.setLayout(new GridLayout(0,2));
		
        canvasUI.add(new JLabel("IMPORTO (€): "));
        this.playChoose.add(new JComboBox<String>(GUI.PUNTATE));
        canvasUI.add(this.playChoose.get(0),BorderLayout.NORTH);
        
        /*puntata scelta*/
        canvasUI.add(new JLabel("DURATA (secondi): "));
        this.playChoose.add(new JComboBox<String>(GUI.DURATE));
        canvasUI.add(this.playChoose.get(1),BorderLayout.NORTH);
		 
		this.observers = new HashSet<>();
		
		
		lContoDemoVal=new JLabel(Integer.toString(this.conto)+" $");
		punto=new JLabel("");
		new Color(0);
		//CAMBIO I COLORI DEI BOTTONI		
		up.setBackground(Color.green);
		new Color(0);
		down.setBackground(Color.red);
		
		lImporto.setSize(1,7);
		lContoDemoVal.setSize(1,7);
		
		//aggiungo gli elementi all'user interface
		canvasUI.add(lContoDemo);
		canvasUI.add(lContoDemoVal);
		canvasUI.add(percBroker);
		canvasUI.add(lGuadagnoVal);
		canvasUI.add(up,BorderLayout.CENTER );
		canvasUI.add(down,BorderLayout.SOUTH );		
		canvasUI.add(punto,BorderLayout.SOUTH);		
		
		//------------------------------------  
		canvasGraphLinee.add(canvasBuy);
		canvasGraphLinee.add(graficoALinee.getContentPane());
		canvasGraphCandle.add(graficoACandele.getContentPane());
		canvasGraphCandle.add(canvasUI);
		canvas.add(canvasGraphLinee);
  		canvas.add(canvasGraphCandle);
  		//modifico il colore di sfondo
  		canvasUI.setBackground(Color.pink);
  		canvasBuy.setBackground(Color.pink);
  		canvasGraphCandle.setBackground(Color.pink);
  		canvasGraphLinee.setBackground(Color.pink);  		
  		canvas.setBackground(Color.pink);
  		this.add(canvas);
  		
  		//EVENTI GRAFICI
  		up.addActionListener(e->{
  			observers.forEach(observer->{  observer.call();});
    	});
        
  		down.addActionListener(e->{	
  			observers.forEach(observer->{ observer.put();});
		});
      
		this.userChoose.get(0).addActionListener(e->{
			tipoOp=this.userChoose.get(0).getSelectedItem().toString();
		});
		
		this.userChoose.get(1).addActionListener(e->{
			tipoGrafico=this.userChoose.get(1).getSelectedItem().toString();			
		});
		
		this.userChoose.get(2).addActionListener(e->{
			tipoIndicatore=this.userChoose.get(2).getSelectedItem().toString();
			this.graficoALinee.addSubPlot(tipoIndicatore);
			this.graficoACandele.addSubPlot(tipoIndicatore);			
		});		
	}
	
	public void AvvioGiocata(){
		observers.forEach(observer->{ 
            observer.call();
            observer.put();
		});	
	}	
	

		
	public void setData(TimeSeries serie,TimeSeries serie2,TimeSeries serie3,
						TimeSeries serie4,TimeSeries serie5,TimeSeries serie6,
						TimeSeries serie7,TimeSeries serie8,TimeSeries serie9){
		graficoALinee.setData(serie);
		graficoALinee.insMediaSeplice(serie2);
		graficoALinee.insEsp(serie3);
		graficoALinee.insBolingerSup(serie4);
		graficoALinee.insBolingerInf(serie5);
		graficoALinee.insMacdDiff(serie6);
		graficoALinee.insMacdSingle(serie7);
		graficoALinee.insStocastico(serie8);
		graficoALinee.insRsi(serie9);		
	}
		

	
	//cambio grafico
	public void changeGraph(boolean changeToLine){
		if(changeToLine){
			this.graficoACandele.getContentPane().setVisible(true);
			this.graficoALinee.getContentPane().setVisible(false);
		}
		else{
			this.graficoACandele.getContentPane().setVisible(false);
			this.graficoALinee.getContentPane().setVisible(true);			
		}	
	}
		
	//setto il dataset
	public void setDataSet(OHLCSeries dataset,TimeSeries serie2,TimeSeries serie3,
			TimeSeries serie4,TimeSeries serie5,TimeSeries serie6,
			TimeSeries serie7,TimeSeries serie8,TimeSeries serie9){		
		this.graficoACandele.setSeries(dataset);
		graficoACandele.insMediaSeplice(serie2);
		graficoACandele.insEsp(serie3);
		graficoACandele.insBolingerSup(serie4);
		graficoACandele.insBolingerInf(serie5);
		graficoACandele.insMacdDiff(serie6);
		graficoACandele.insMacdSingle(serie7);
		graficoACandele.insStocastico(serie8);
		graficoACandele.insRsi(serie9);
	}
	
	public JComboBox<String> getTipoGrafico(){
		return this.userChoose.get(1);
	}
	//------------------------------------------------------------------------------------		
	
	/*user interface*/		
	//aggiorno l'interfaccia per stampare il punto di giocata preso
	@Override
	public void setPoint(Double val) {
		// TODO Auto-generated method stub
		this.punto.setText("RIFERIMENTO: "+ val.toString());                
	}

	public void disabilitaBottone() {
		// TODO Auto-generated method stub
		this.up.setEnabled(false);
		this.down.setEnabled(false);
	}
	
	public void abilitaBottone() {
		// TODO Auto-generated method stub
		this.up.setEnabled(true);
		this.down.setEnabled(true);
	}
	
	public void aggiornaConto(String text){		
		lContoDemoVal.setText(text);
	}
	
	public double getPuntata(){
		return Double.parseDouble(this.playChoose.get(0).getSelectedItem().toString());
	}
	
	public double getDurata(){
		return Double.parseDouble(this.playChoose.get(1).getSelectedItem().toString());
	}
	
	//per visualizzare i messaggi
	public static void infoBox(boolean win)
    {
		if(win){
			JOptionPane.showMessageDialog(null, "WIN!", "InfoBox: " + "esito giocata", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null, "LOSE", "InfoBox: " + "esito giocata", JOptionPane.INFORMATION_MESSAGE);
		}        
    }
	
	//PATTERN OBSERVER
	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		this.observers.add(o);
	}
}