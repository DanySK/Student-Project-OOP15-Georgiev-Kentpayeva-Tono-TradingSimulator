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
	public boolean isUpDateCtr=false;
	public boolean isIndicatoreReady=false;
	public boolean isUP=false;
	boolean isCandleGraph=false;
	TimeSeriesCollection dataset=null;
	OHLCSeriesCollection datasetCandle=null;
	/*buy*/
	/*per le combo box*/
	private static final String[] ASSET = {"EUR/USD"};
	private static final String[] DURATE = {"1 minuto","2 minuti","5 minuti"};
	private static final String[] GRAFICI = {"candele", "normale"};
	private static final String[] INDICATORI = {"Medie Mobili","Medie Mobili Esponenziali","MACD Diff","MACD Single","Stocastico", "Calendario Economico","RSI","Bande di Bollinger"};
	String tipoOp="",tipoGrafico="",tipoIndicatore="";		
	//------------------------------
	/*user interface*/
	public boolean isUp=false;
	public boolean isDown=false;
	
	private final Set<Observer> observers;
	
	//ValuesAsset asset=null;
	private int conto=2000;
	private int guadagno=88;
	//elementi grafici
	private final List<JComboBox<String>> questions=new ArrayList<>();
	
	JButton up= new JButton("UP");
	JButton down= new JButton("DOWN");
	JLabel punto;
	//-----------------------------
	JLabel lContoDemoVal=null;
	JLabel puntata=null;
	
	public GraficiCombinati graficoALinee= new GraficiCombinati("MSFT");
	public CandleStick graficoACandele=new CandleStick("MSFT");	
		
	public ViewPlatformImpl(){
		
		super("Trading Platoform");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //elementi grafici
        JPanel canvas =new JPanel();
    	JPanel canvasUI =new JPanel();
    	JPanel canvasBuy =new JPanel();
    	JPanel canvas2 =new JPanel();
    	   
        this.dataset=new TimeSeriesCollection();
        
        JPanel canvasTot =new JPanel();		
		graficoALinee.pack();
		graficoACandele.pack();		
		
		/*buy-------------------*/
		JPanel nordb= new JPanel();
		JPanel nord2b= new JPanel();
		JPanel nord3b= new JPanel();
		JPanel southb= new JPanel();
		JPanel south2b= new JPanel();
		
		JTextArea name=new JTextArea(1,7);
		JTextArea surname=new JTextArea(1,7);
		JLabel lName=new JLabel("name: ");
		JLabel lSurname=new JLabel("surname: ");		
		
		lName.setSize(1,7);
		lSurname.setSize(1,7);
		name.setSize(5,5);
		
		/*elementi per combobox*/
		final List<String> tipologie = new ArrayList();
		final List<String> tipiGrafi = new ArrayList();
		
		tipologie.add("Tipologia");
		tipiGrafi.add("grafi");
		
        JPanel panel1 = new JPanel(new GridLayout(tipologie.size(),2));
        /*tipi di gioco: binario,tradizionale*/
        panel1.add(new JLabel("Tipologia: "));
        this.questions.add(new JComboBox<String>(ASSET));
        panel1.add(this.questions.get(0));
        
        JPanel panel2 = new JPanel(new GridLayout(tipologie.size(),2));
        /*tipi di grafo: candele,normale*/
        panel2.add(new JLabel("Grafico: "));
        this.questions.add(new JComboBox<String>(GRAFICI));
        panel2.add(this.questions.get(1));

        JPanel panel3 = new JPanel(new GridLayout(tipologie.size(),2));
        /*tipi di grafo: candele,normale*/
        panel3.add(new JLabel("Indicatore Tecnico: "));
        this.questions.add(new JComboBox<String>(INDICATORI));
        panel3.add(this.questions.get(2));

        nordb.setLayout(new BorderLayout()); 
        nordb.add(BorderLayout.NORTH,panel1);
        nordb.add(BorderLayout.CENTER,panel2);
		
		//aggiungo gli elementi all'user interface
		nordb.add(BorderLayout.SOUTH,lName);
		nordb.add(BorderLayout.SOUTH,name);
		nord2b.add(BorderLayout.SOUTH,lSurname);
		nord2b.add(BorderLayout.SOUTH,surname);	
		
		nord3b.setLayout(new BorderLayout());
		nord3b.add(nordb,BorderLayout.NORTH);
		nord3b.add(nord2b,BorderLayout.SOUTH);
		
		southb.add(panel3,BorderLayout.CENTER );	
		
		canvasBuy.add(nord3b,BorderLayout.NORTH);
		canvasBuy.add(southb,BorderLayout.CENTER);
		canvasBuy.add(south2b,BorderLayout.SOUTH);
		
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
		JPanel giocata=new JPanel();
		
		JTextArea name1=new JTextArea(1,7);
		JLabel lImporto=new JLabel("importo: $");
		JLabel lContoDemo=new JLabel("CONTO DEMO: ");
		lContoDemoVal=new JLabel(Integer.toString(this.conto)+" $");
		punto=new JLabel("prova");
		puntata=new JLabel("prova2");
		JLabel vincita=new JLabel("prova3");
		JLabel lGuadagno=new JLabel("Guadagno: ");
		JLabel lGuadagnoVal=new JLabel(Integer.toString(this.guadagno)+" %");
		
		up.setBackground(new Color(100, 200, 33));//verde
		down.setBackground(new Color(0).red); //rosse
		
		nord2.setLayout(new BorderLayout());
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
		nord3.setLayout(new BorderLayout());
		nord3.add(nord,BorderLayout.NORTH);
		nord3.add(nord2,BorderLayout.CENTER);
		
		giocata.add(punto,BorderLayout.NORTH);
		giocata.add(puntata,BorderLayout.CENTER);		
		giocata.add(vincita,BorderLayout.SOUTH);
		
		south.add(up,BorderLayout.CENTER );
		south2.add(down,BorderLayout.SOUTH );		
		
		giocata2.add(nord3,BorderLayout.NORTH);
		giocata2.add(south,BorderLayout.CENTER);
		
		canvasUI.add(giocata2, BorderLayout.CENTER);
		canvasUI.add(giocata,BorderLayout.NORTH);		
		canvasUI.add(south2,BorderLayout.SOUTH);
		
	 //------------------------------------        
  		this.graficoACandele.getContentPane().setVisible(false);
  		this.graficoALinee.getContentPane().setVisible(true);
  		
  		canvas.add(canvasBuy,BorderLayout.WEST);
		canvas.add(graficoALinee.getContentPane(),BorderLayout.CENTER);
		canvas2.add(graficoACandele.getContentPane(),BorderLayout.SOUTH);
		canvas2.add(canvasUI,BorderLayout.EAST);
		canvasTot.add(canvas,BorderLayout.WEST);
  		canvasTot.add(canvas2,BorderLayout.EAST);
  		this.add(canvasTot);
  		this.setVisible(true);
  		
  		//EVENTI GRAFICI
  		up.addActionListener(e->{
  			observers.forEach(observer->{  observer.call();});
    	});
        
  		down.addActionListener(e->{	
  			observers.forEach(observer->{ observer.put();});
		});
      
		this.questions.get(0).addActionListener(e->{
			tipoOp=this.questions.get(0).getSelectedItem().toString();
			System.out.println(tipoOp);
		});
		
		this.questions.get(1).addActionListener(e->{
			tipoGrafico=this.questions.get(1).getSelectedItem().toString();
			System.out.println(tipoGrafico);
			
		});
		
		this.questions.get(2).addActionListener(e->{
			tipoIndicatore=this.questions.get(2).getSelectedItem().toString();
			this.graficoALinee.addSubPlot(tipoIndicatore);
		});
		
		//imposto la dimensione dell'intefaccia grafica a quella dello schermo
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		this.setSize(screenSize.width,screenSize.height);
	}
	
	public void AvvioGiocata(){
		observers.forEach(observer->{ 
            observer.call();
            observer.put();
		});	
	}	
	//_______________________________metodi per il controller____________________________________
	@Override
	public void setValueGraph(TimeSeriesCollection dataset ) {
		// TODO Auto-generated method stub
		this.dataset=dataset;
	}
	
	public Boolean getIsUp(){
		return this.isUP;
	}	
	
	public void setData(TimeSeries serie,TimeSeries serie2,TimeSeries serie3,
						TimeSeries serie4,TimeSeries serie5,TimeSeries serie6,
						TimeSeries serie7,TimeSeries serie8,TimeSeries serie9)
	{
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
		
	public void setDataCandle(OHLCSeries serie)
	{
		this.datasetCandle.addSeries(serie);		
	}
	
	//getter e setter per scegliere il tipo di grafico
	public boolean getCandleStick(){
		return this.isCandleGraph;
	}
	
	public void setCandleStick(boolean isCandleGraph){
		 this.isCandleGraph=isCandleGraph;
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
		return this.questions.get(1);
	}
	//------------------------------------------------------------------------------------
		
	/*user interface*/
	/*per la combobox*/
	private JPanel flowBoxed(JComponent jc){
        JPanel jp = new JPanel(new FlowLayout());
        jp.add(jc);
        return jp;
    }
		
	//aggiorno l'interfaccia per stampare il punto di giocata preso
	@Override
	public void setPoint(Double val) {
		// TODO Auto-generated method stub
		try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                	ViewPlatformImpl.this.punto.setText(val.toString());
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
		
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
		return Double.parseDouble(this.puntata.getText());
	}
	
	//per visualizzare i messaggi
	public static void infoBox(boolean win)
    {
		String infoMessage,titleBar;
		titleBar="esito giocata";
		if(win){
			infoMessage="WIN!";
		}
		else{
			infoMessage="LOSE";
		}
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	//PATTERN OBSERVER
	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		this.observers.add(o);
	}
}