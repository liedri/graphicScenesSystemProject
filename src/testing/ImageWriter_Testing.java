package testing;

import static org.junit.Assert.*;
import renderer.*;
import elements.*;
import geometries.*;
import primitives.*;


import org.junit.Test;

/**
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class ImageWriter_Testing {

	@Test
	public void test() {
		Color c1 = new Color(0.0,0.0,255.0);
		Color c2 = new Color(255.0, 255.0, 255.0);
		//TC1:
		ImageWriter Test1 = new ImageWriter("Test1Image", 1600, 1000, 800, 500);
		for (int i = 0; i < 800; i++){
			for (int j = 0; j < 500; j++){
				if (i % 50 == 0 || j % 50 == 0)
					Test1.writePixel(i,  j, c2.getColor());
				else
					Test1.writePixel(i,  j, c1.getColor());
			
			}
		}
		
		Test1.writeToImage();
	}
}
