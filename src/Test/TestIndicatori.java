package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import IndicatoriTecniciModel.Indicatori;
import IndicatoriTecniciModel.IndicatoriFormuleImpl;

public class TestIndicatori {
	//Strategy modelLine;
	//Th trd;

	@org.junit.Test
	public void test() {	
		
		
		List<Double> val=null;
		val=Arrays.asList(10.0 , 20.1 , 30.2 , 40.3 , 50.5 , 100.0);
		Indicatori indicator= new IndicatoriFormuleImpl();
		
		val.forEach(e->{
			indicator.insertValue(e);
		});
		
		//indicator.CalcoloBandaDiBoolingerInf();
		//indicator.CalcoloBandaDiBoolingerSup();
		//System.out.println(indicator.CalcoloMACDDIff());
		assertTrue(indicator.CalcoloMACDDIff()==16.4859375);
		//assertTrue(indicator.CalcoloMACDDIff()==16.4859375);
		
		assertTrue(indicator.CalcoloMACDSingle()==16.027994791666668);
		assertTrue(indicator.CalcoloMediaMobilEsponenziale()==11.79816623263889);
		assertTrue(indicator.CalcoloMediaMobilSemplice()==43.81636103877315);
		//indicator.CalcoloRSI();
		assertTrue(indicator.CalcoloStocastico()==50.0);
		//indicator.CalcoloStocastico();
		
		
	}
	
}

