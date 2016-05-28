package viewPlatform;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tecnicalIndicatorView.CalendarioEconomico;

public class buy extends JPanel{
	
	
	private boolean isCandle=true;
	/*per le combo box*/
	   private static final String[] CHOICES = {"binario", "tradizionale"};
	   private static final String[] CHOICES2 = {"candele", "normale"};
	   private static final String[] CHOICES3 = {"Medie Mobili", "Calendario Economico","RSI","Bande di Bollinger"};
	   private static final List<Optional<Boolean>> BOOLS = Arrays.asList(Optional.empty(), Optional.of(true), Optional.of(false));
	    
	   private final List<JComboBox<String>> questions=new ArrayList<>();
	   
	    
	 /*____________________________________________*/
	
	   
	   String tipoOp="";
	   String tipoGrafico="";
	   String tipoIndicatore="";
		
	public buy(){
		this.setLayout(new BorderLayout());
		
		JPanel nord= new JPanel();
		JPanel nord2= new JPanel();
		
		JPanel nord3= new JPanel();
		
		
		JPanel south= new JPanel();
		JPanel south2= new JPanel();
		
		
		JTextArea name=new JTextArea(1,7);
		JTextArea surname=new JTextArea(1,7);
		JLabel lName=new JLabel("name: ");
		JLabel lSurname=new JLabel("surname: ");
		
		JButton esegui=new JButton("esegui Indicatore Tecnico");
		
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
        this.questions.add(new JComboBox<String>(CHOICES));
        panel1.add(this.questions.get(0));
        
        JPanel panel2 = new JPanel(new GridLayout(tipologie.size(),2));
        /*tipi di grafo: candele,normale*/
        panel2.add(new JLabel("Grafico: "));
        this.questions.add(new JComboBox<String>(CHOICES2));
        panel2.add(this.questions.get(1));
        
        

        JPanel panel3 = new JPanel(new GridLayout(tipologie.size(),2));
        /*tipi di grafo: candele,normale*/
        panel3.add(new JLabel("Indicatore Tecnico: "));
        this.questions.add(new JComboBox<String>(CHOICES3));
        panel3.add(this.questions.get(2));
        
        
        
        
        
        nord.setLayout(new BorderLayout()); 
        nord.add(BorderLayout.NORTH,panel1);
        nord.add(BorderLayout.CENTER,panel2);
       
       /*_____________*/
		
		//aggiungo gli elementi all'user interface
		nord.add(BorderLayout.SOUTH,lName);
		nord.add(BorderLayout.SOUTH,name);
		nord2.add(BorderLayout.SOUTH,lSurname);
		nord2.add(BorderLayout.SOUTH,surname);
		
		
		nord3.setLayout(new BorderLayout());
		nord3.add(nord,BorderLayout.NORTH);
		nord3.add(nord2,BorderLayout.SOUTH);
		
		south.add(panel3,BorderLayout.CENTER );
		south2.add(esegui,BorderLayout.SOUTH );
		
	
		
		
		
		
		
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
			System.out.println(tipoIndicatore);
		});
		
		this.add(nord3,BorderLayout.NORTH);
		this.add(south,BorderLayout.CENTER);
		this.add(south2,BorderLayout.SOUTH);
		
		esegui.addActionListener(e->{
			System.out.println("AVVIO: "+this.tipoIndicatore);
			if(this.tipoIndicatore=="Calendario Economico"){
				new CalendarioEconomico().main(new String[]{"s"});;
			}
		});
		
		
		
	}
	
	
	/*per la combobox*/
	private JPanel flowBoxed(JComponent jc){
        JPanel jp = new JPanel(new FlowLayout());
        jp.add(jc);
        return jp;
    }
	/*___________________________________*/
	
	public boolean getIsCandle(){
		return this.isCandle;
	}
	
	
	public JComboBox<String> getTipoGrafico(){
		return this.questions.get(1);
	}
	
}
