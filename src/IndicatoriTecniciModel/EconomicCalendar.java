package IndicatoriTecniciModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;



public class EconomicCalendar {
	
	public EconomicCalendar(){
		this.list=new ArrayList<>();
		
		System.out.println("cal");
		data();
	}
	
	
	BufferedReader br = null;
	List<String> list;
	int count=0;
	boolean fine;
	
	//1)Data+(Mese)	2)Ora legale	3)Importanza	4)Paese		5)Evento	
	//6)Attuale	7)Previsione	8)Precedente	
	
	public List<String> data() {
		// TODO Auto-generated method stub
		  
			try {
	 			br = new BufferedReader(new FileReader("datasrc/econimicCalendarInformation.csv"));
	 		} catch (FileNotFoundException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}
	         String input;
	         
	      		  
	         try {
	 			br.readLine();
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	         try {
	        	 
	        	 
	 			   while((input = br.readLine())!=null)
	 			   {
	 				   this.list.add(input);
	 			   }
	 			   
	 			  
	 			   
	 			    while(count<list.size()){
		 			    count++;
		 			    
		 			    StringTokenizer s = new StringTokenizer(list.get(count-1), ",");
		 			    
		 			    //s.nextToken();
		 			
		 			    //1)Data+(Mese)	2)Ora legale	3)Importanza	4)Paese		5)Evento	
		 				//6)Attuale	7)Previsione	8)Precedente	
		 				
		 			    String data=s.nextToken(),
		 			    		ora=s.nextToken(),
		 			    		importanza=s.nextToken(),
		 			    		paese=s.nextToken(),
		 			    		evento=s.nextToken(),
		 			    		attuale=s.nextToken(),
		 			    		previone=s.nextToken(),
		 			    		precedente=s.nextToken(),
		 			    		mese=s.nextToken();
				 			    		
		 			    		
		 			   System.out.println(data+
		 			    		ora+
		 			    		importanza+
		 			    		paese+
		 			    		evento+
		 			    		attuale+
		 			    		previone+
		 			    		precedente+
		 			    		mese);
		 			    
	 			    }
	 			    
	 			   
	 			
	 		} catch (NumberFormatException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	         
	        return this.list;
	         
		}
		
	

}
