package userModel;




public class UserImpl implements User{

	//SINGLETON
	private static UserImpl us=null;
	
	private String nome="demo",cognome="",email="";
	private double account=10000;
	private int telefono;
	
	
	//SINGLETON
	protected UserImpl(){
		
	}
	
	public static UserImpl getUs(){
		if(us==null){
			us=new UserImpl();
		}
			return us;
	}
	
	@Override
	public double getAccount()
	{
		return this.account;
	}
	@Override
	public void setAccountWin(double win)
	{
		this.account=this.account+win;
	}
	@Override
	public void setAccountLose(double lose)
	{
		this.account=this.account-lose;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getSurname() {
		// TODO Auto-generated method stub
		return this.cognome;
	}
	
	@Override
	public String getMail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public int getTelefono() {
		// TODO Auto-generated method stub
		return this.telefono;
	}
	@Override
	public void setName(String nome) {
		// TODO Auto-generated method stub
		this.nome=nome;
	}
	@Override
	public void setSurname(String cognome) {
		// TODO Auto-generated method stub
		this.cognome=cognome;
	}
	
	@Override
	public void setMail(String email) {
		// TODO Auto-generated method stub
		this.email=email;
	}
	@Override
	public void setTelefono(int telefono) {
		// TODO Auto-generated method stub
		this.telefono=telefono;
	}
	
	

}
