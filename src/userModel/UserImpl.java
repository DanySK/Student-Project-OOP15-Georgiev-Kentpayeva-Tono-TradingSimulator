package userModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserImpl {

	String nome;
	double account=10000;
	
	
	
	
	
	
	
	public double getAccount()
	{
		return this.account;
	}
	
	public void setAccountWin(double win)
	{
		this.account=this.account+win;
	}
	
	public void setAccountLose(double lose)
	{
		this.account=this.account-lose;
	}
	

	
	
	

}
