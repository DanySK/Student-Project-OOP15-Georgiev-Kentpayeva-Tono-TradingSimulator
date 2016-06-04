package Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import IndicatoriTecniciModel.Indicatori;
import IndicatoriTecniciModel.IndicatoriFormuleImpl;

public class TestIndicatori {
	//Strategy modelLine;
	//Th trd;

	@org.junit.Test
	public void test() {	
		
		//creo e riempio una lista con valori di prova 
		List<Double> val=null;
		val=Arrays.asList(10.0 , 20.1 , 30.2 , 40.3 , 50.5 , 100.0);
		//instanzio la classe con le formule da calcolare
		Indicatori indicator= new IndicatoriFormuleImpl();
		
		val.forEach(e->{
			indicator.insertValue(e);
		});
		
		// le formule degli indicatori tecnici devono restituire tutti valori positivi
		assertTrue(indicator.CalcoloMACDDIff()>0);
		assertTrue(indicator.CalcoloMACDDIff()<100);
		assertTrue(indicator.CalcoloMACDSingle()>0);
		assertTrue(indicator.CalcoloMediaMobilEsponenziale()>0);
		assertTrue(indicator.CalcoloMediaMobilSemplice()>0);
		assertTrue(indicator.CalcoloStocastico()>0);
		
		
	}
	
}

