package IndicatoriTecniciModel;

import java.util.List;

public class IndicatoriImpl implements Indicatori {

	List<Double> valori=null;
	
	public IndicatoriImpl(List<Double> valori){
		this.valori=valori;
	}
	
	double risult=0;
	
	@Override
	public void CalcoloMediaMobilSemplice() {
		// TODO Auto-generated method stub
		this.valori.forEach(e->risult+=e);
		risult=risult/(this.valori.size());
		
	}

	@Override
	public void CalcoloMediaMobilEsponenziale() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CalcoloRSI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CalcoloBandeDiBoolinger() {
		// TODO Auto-generated method stub
		
	}
	
}
