package userModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserImpl implements User {

	private String nome;
	private String cognome;
	private int data_di_nascita;
	private String mail;
	private int telefono;
	private boolean isWin;
	private boolean isUp;
	private int totaleConto;
	
	List<String> history;
	private int puntata;
	
	
	public UserImpl(String nome,String cognome,int data_di_nascita,String mail,int telefono) {
		// TODO Auto-generated constructor stub
		
		this.nome=nome;
		this.cognome=cognome;
		this.data_di_nascita= data_di_nascita;
		this.mail=mail;
		this.telefono=telefono;
		history= new ArrayList<>();
		
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.nome;
	}

	@Override
	public String getSurname() {
		// TODO Auto-generated method stub
		return this.cognome;
	}

	@Override
	public int getDate() {
		// TODO Auto-generated method stub
		return this.data_di_nascita;
	}

	@Override
	public String getMail() {
		// TODO Auto-generated method stub
		return this.mail;
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
	public void setDate(int date) {
		// TODO Auto-generated method stub
		this.data_di_nascita=date;
	}

	@Override
	public void setMail(String mail) {
		// TODO Auto-generated method stub
		this.mail=mail;
	}

	@Override
	public void setTelefono(int telefono) {
		// TODO Auto-generated method stub
		this.telefono=telefono;
	}

	@Override
	public boolean upDown(boolean isUp) {
		// TODO Auto-generated method stub
		
		
		this.isUp=isUp;
		this.aggiornamentoConto();
		
		return this.isWin;
	}

	
	
	void aggiornamentoConto(){
		
			
			if(isWin){
				this.totaleConto+=puntata;
			}
			else{
				this.totaleConto-=puntata;
			}
			
		
	}

	
	
	

}
