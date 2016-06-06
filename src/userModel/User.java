package userModel;

public interface User {

	String getName();
	String getSurname();
	String getMail();
	int getTelefono();
	public double getAccount();
	
	
	void setName(String nome);
	void setSurname(String cognome);
	void setMail(String mail);
	void setTelefono(int telefono);
	
	
	public void setAccountWin(double win);
	public void setAccountLose(double lose);
	
	
	
}
