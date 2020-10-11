package testing;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;
import org.junit.Test;


/**
 * Unit Test case to the class Tube
 * @author Li Edri
 * @author Adi Giladi
 *
 */

public class Tube_Testing {
	
	/**
	 * Test to the Constructor of Tube
	 */
	@Test
	public void Constructor_test() 
	  {
	      // ============ Equivalence Partitions Tests ==============

	      // TC01: Creating the Tube: { ray=(0,0,1)->(0,0,1) , radius=2} with the 
		  //       Constructor- Tube(Ray, double)
	      try 
	      {
	          new Tube(new Ray(new Point3D(0,0,1), new Vector(0,0,1)), 2);
	      } 
	      catch (IllegalArgumentException e) 
	      {
	          fail("Failed constructing a correct Plane");
	      }
	  }
	
	
	/**
	 * Test to the function getNormal(Point3D) that return the normal of the Cylinder
	 */
	@Test
	public void getNormal_test() 
	{
		Tube c=new Tube(new Ray(new Point3D(0,0,0), new Vector(0,0,1)), 2);
		assertEquals(c.getNormal(new Point3D(0,2,2)), new Vector(0,2,0).normalized());
	}

}
