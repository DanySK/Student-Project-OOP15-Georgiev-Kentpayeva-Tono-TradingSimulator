package viewPlatform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;

public class ViewPlatformImpl extends JFrame implements ViewPlatform,Observ{
	
	private int durataDiGioco;
	TimeSeriesCollection dataset=null;
	OHLCSeriesCollection datasetCandle=null;
	/*buy*/
	/*per le combo box*/
	private static final String[] 	ASSET = {"EUR/USD"},
									DURATE = {"1 minuto","2 minuti","5 minuti"},
									GRAFICI = {"candele", "normale"},
									INDICATORI = {"Medie Mobili","Medie Mobili Esponenziali","MACD Diff","MACD Single","Stocastico", 
													"Calendario Economico","RSI","Bande di Bollinger"};
	String tipoOp="",tipoGrafico="",tipoIndicatore="";		
	//------------------------------
	/*user interface*/
	private final Set<Observer> observers;
	
	private int conto=2000, guadagno=88;
	//elementi grafici
	private final List<JComboBox<String>> userChoose=new ArrayList<>();	
	JButton up= new JButton("UP");
	JButton down= new JButton("DOWN");
	JLabel punto;
	JLabel lImporto=new JLabel("importo: $");
	JLabel lContoDemoVal=null;
	
	public GraficiCombinati graficoALinee= new GraficiCombinati("MSFT");
	public CandleStick graficoACandele=new CandleStick("MSFT");	
		
	public ViewPlatformImpl(){
		
		super("Trading Platoform");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //imposto la dimensione dell'intefaccia grafica a quella dello schermo
      	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width,screenSize.height);
        
        //elementi grafici
        JPanel canvasGraph =new JPanel();
    	JPanel canvasUI =new JPanel();
    	JPanel canvasBuy =new JPanel();
    	JPanel canvas2 =new JPanel();        
        JPanel canvasTot =new JPanel();		
		
		/*buy-------------------*/		
		/*elementi per combobox*/
		JPanel panel1 = new JPanel();
        /*tipi di gioco: binario,tradizionale*/
        panel1.add(new JLabel("Tipologia: "));
        this.userChoose.add(new JComboBox<String>(ASSET));
        panel1.add(this.userChoose.get(0),BorderLayout.NORTH);
        
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        /*tipi di grafo: candele,normale*/
        panel1.add(new JLabel("Grafico: "));
        this.userChoose.add(new JComboBox<String>(GRAFICI));
        panel1.add(this.userChoose.get(1),BorderLayout.CENTER);

        JPanel panel3 = new JPanel(new GridLayout(1,2));
        /*tipi di grafo: candele,normale*/
        panel1.add(new JLabel("Indicatore Tecnico: "));
        this.userChoose.add(new JComboBox<String>(INDICATORI));
        panel1.add(this.userChoose.get(2),BorderLayout.SOUTH);

		canvasBuy.add(panel1,BorderLayout.WEST);
		
		//-----------------------------		
		/*user interface*/
		 
	 	this.setLayout(new BorderLayout());
		this.observers = new HashSet<>();
		JPanel nord= new JPanel();
		JPanel nord2= new JPanel();
		JPanel nord3= new JPanel();
		JPanel south= new JPanel();
		JPanel south2= new JPanel();
		JPanel giocata2=new JPanel();
		
		JTextArea name1=new JTextArea(1,7);
		JLabel lContoDemo=new JLabel("CONTO DEMO: ");
		JLabel lGuadagno=new JLabel("Guadagno: ");
		JLabel lGuadagnoVal=new JLabel(Integer.toString(this.guadagno)+" %");
		
		lContoDemoVal=new JLabel(Integer.toString(this.conto)+" $");
		punto=new JLabel("prova");
		
		//CAMBIO I COLORI DEI BOTTONI		
		up.setBackground(new Color(0).green);
		down.setBackground(new Color(0).red);
		
		lImporto.setSize(1,7);
		lContoDemoVal.setSize(1,7);
		name1.setSize(5,5);
		
		//aggiungo gli elementi all'user interface
		nord.add(lImporto);
		nord.add(name1);
		nord2.add(lContoDemo,BorderLayout.NORTH);
		nord2.add(lContoDemoVal,BorderLayout.NORTH);
		nord2.add(lGuadagno,BorderLayout.SOUTH);
		nord2.add(lGuadagnoVal,BorderLayout.SOUTH);
		nord3.add(nord,BorderLayout.NORTH);
		nord3.add(nord2,BorderLayout.CENTER);		
		
		south.add(up,BorderLayout.CENTER );
		south2.add(down,BorderLayout.SOUTH );		
		
		giocata2.add(nord3,BorderLayout.NORTH);
		giocata2.add(south,BorderLayout.CENTER);
		
		giocata2.add(punto,BorderLayout.SOUTH);		
		
		canvasUI.add(giocata2, BorderLayout.CENTER);
		canvasUI.add(south2,BorderLayout.SOUTH);		
		//------------------------------------        
  		
  		canvasGraph.add(canvasBuy);
		canvasGraph.add(graficoALinee.getContentPane());
		canvas2.add(graficoACandele.getContentPane());
		canvas2.add(canvasUI);
		canvasTot.add(canvasGraph);
  		canvasTot.add(canvas2);
  		this.add(canvasTot);
  		
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
		});
	}
	
	public void AvvioGiocata(){
		observers.forEach(observer->{ 
            observer.call();
            observer.put();
		});	
	}	
	
	@Override
	public void setValueGraph(TimeSeriesCollection dataset ) {
		// TODO Auto-generated method stub
		this.dataset=dataset;
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
		
	public void setDataCandle(OHLCSeries serie){
		this.datasetCandle.addSeries(serie);		
	}
	
	//cambio grafico
	public void changeGraph(boolean changeToCandle){
		if(changeToCandle){
			this.graficoACandele.getContentPane().setVisible(true);
			this.graficoALinee.getContentPane().setVisible(false);			
		}
		else{
			this.graficoACandele.getContentPane().setVisible(false);
			this.graficoALinee.getContentPane().setVisible(true);
		}	
	}
		
	//setto il dataset
	public void setDataSet(OHLCSeries dataset){
		this.graficoACandele.setSeries(dataset);
	}
	public void setDurataDiGioco(int durataDiGioco){
		this.durataDiGioco=durataDiGioco;
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
		ViewPlatformImpl.this.punto.setText(val.toString());                
	}

	@Override
	public void disabilitaBottone() {
		// TODO Auto-generated method stub
		this.up.setEnabled(false);
		this.down.setEnabled(false);
	}
	
	@Override
	public void abilitaBottone() {
		// TODO Auto-generated method stub
		this.up.setEnabled(true);
		this.down.setEnabled(true);
	}
	
	public void aggiornaConto(String text){		
		lContoDemoVal.setText(text);
	}
	
	public double getPuntata(){
		return Double.parseDouble(this.lImporto.getText());
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