
package Test;

import modelPlatform.ModelPlatform;
import modelPlatform.ModelPlatformImpl;

public class ModelTest {
	public static void main(String[] args) {
		
		ModelPlatform model = new ModelPlatformImpl();
		
		System.out.println(model.dataFeed().toString());
	}
}
