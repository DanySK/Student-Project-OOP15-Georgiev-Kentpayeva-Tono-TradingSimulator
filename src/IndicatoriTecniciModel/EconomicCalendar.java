package IndicatoriTecniciModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


//fonte: http://www.iforex.it/economic-calendar

public class EconomicCalendar {
	
	public EconomicCalendar(){
		this.list=new ArrayList<>();
		
		System.out.println("cal");
		data();
	}
	
	
	BufferedReader br = null;
	List<String> list;
	int count=0;
	
	//1)Data+(Mese)	2)Ora legale	3)Importanza	4)Paese		5)Evento	
	//6)Attuale	7)Previsione	8)Precedente	
	
	public void data() {
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
        	 
        	 
 			   while((input = br.readLine())!=null&&count==0)
 			   {
 				   this.list.add(input);
 			   }
 			    if(count<list.size()){
	 			    count++;
	 			    
	 			    StringTokenizer s = new StringTokenizer(list.get(count-1), ";");
	 			    
	 			    s.nextToken();
	 			   
	 			    double open=Double.parseDouble(s.nextToken());
	 			    double high=Double.parseDouble(s.nextToken());
	 			    double low=Double.parseDouble(s.nextToken());
	 			    double close=Double.parseDouble(s.nextToken());
	 			  
	 			    System.out.println("ec" +open+" "+high+" "+low+" "+close);
	 			    //System.out.flush();
	 			    
 			    }
 			    else{
 			    	//lancio errori
 			    }
 			   
 			
 		} catch (NumberFormatException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
		
	}

}
