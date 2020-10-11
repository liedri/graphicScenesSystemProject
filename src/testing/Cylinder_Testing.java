package testing;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;
import org.junit.Test;


/**
 * Unit Test case to the class Cylinder
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class Cylinder_Testing {
	
	/**
	 * Test to the Constructor of Cylinder
	 */
	@Test
	public void Constructor_test() 
	  {
	      // ============ Equivalence Partitions Tests ==============

	      // TC01: Creating the Cylinder height=5, radius=3 with the 
		  //       Constructor- Cylinder(double, double)
	      try 
	      {
	          new Cylinder(new Ray(new Point3D(0,0,0), new Vector(0,0,1)), 5,3);
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
		Cylinder c= new Cylinder(new Ray(new Point3D(0,0,0), new Vector(0,0,1)), 5,3);
		assertEquals(c.getNormal(new Point3D(1,1,1)), null);
	}
}
