package modelPlatform;

import java.util.List;

public interface Option {
	 public double getVal();
	 public List<OptionImpl> getHist();
	 public void setHist(OptionImpl op);
	 public boolean callCalc();
	 public boolean putCalc();
	 public void setAttuale(double val);
	 public double getAccount();
	

}
