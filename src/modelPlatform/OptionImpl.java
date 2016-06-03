package modelPlatform;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

import ControllerPlatform.ControllerPlatformImpl;
import userModel.UserImpl;

public class OptionImpl implements Option {
	
	double val;
	double valattuale;
	boolean win;
	double puntata;
	Date data;
	String vin;
	List<OptionImpl> list;
	UserImpl us=UserImpl.getUs();
	int i=0;
      
	public OptionImpl(double val,double puntata,Date data)
	{
		this.val=val;
		this.puntata=puntata;
		this.data=data;
		
		
		list=new ArrayList<>();
		
		
	}
	
      
      
      
     public double getVal()
     {
    	 return val;
     }
     
     public boolean callCalc()
     {
    	 list.add(new OptionImpl(this.val,this.puntata,this.data));
    	 
    	 if(list.get(i).getVal()<valattuale)
			{
				us.setAccountWin(this.puntata);
				System.out.println(us.getAccount()+"-----------");
				
			
				win=true;
			}
			else
			{
				us.setAccountLose(this.puntata);
				System.out.println(us.getAccount()+"-----------");
			
				win=false;
			}
    	 i++;
    	 System.out.println(i);
    	 
    	 return win;
    	 
     }
     
     
     public boolean putCalc()
     {
    	 list.add(new OptionImpl(this.val,this.puntata,this.data));
    	 if(list.get(i).getVal()>valattuale)
			{
				us.setAccountWin(this.puntata);
				//System.out.println(us.getAccount()+"-----------");
			
				win=true;
			}
			else
			{
				us.setAccountLose(this.puntata);
				//System.out.println(us.getAccount()+"-----------");
			
				win=false;
			}
    	 i++;
    	 return win;
     }



	@Override
	public List<OptionImpl> getHist() {
		// TODO Auto-generated method stub
		
		return list;
	}



	@Override
	public void setHist(OptionImpl op) {
		// TODO Auto-generated method stub
		list.add(op);
		
	}




	@Override
	public void setAttuale(double val) {
		// TODO Auto-generated method stub
		this.valattuale=val;
		
	}




	@Override
	public double getAccount() {
		// TODO Auto-generated method stub
		return us.getAccount();
	}
     
     
}
