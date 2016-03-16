package userModel;

public interface User {

	String getName();
	String getSurname();
	int getDate();
	String getMail();
	int getTelefono();
	
	void setName(String nome);
	void setSurname(String cognome);
	void setDate(int date);
	void setMail(String mail);
	void setTelefono(int telefono);
	
	boolean upDown(boolean isUp); //restituisce un boolean 0 se ha vinto, 1 -perso; invece come argomento se la scomessa è verso alto oppure verso basso 
	
	
	
	
}
