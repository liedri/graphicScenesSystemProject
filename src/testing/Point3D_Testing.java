package testing;
import primitives.*;
import static org.junit.Assert.*;
import org.junit.Test;

import geometries.Plane;

/**
 * Unit Test case to the class Point3D
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class Point3D_Testing {

	/**
	   * Test method for
	   * the constructor of the Point3D
	   */
	  @Test
	  public void Constructor_test() 
	  {
	      // ============ Equivalence Partitions Tests ==============

	      // TC01: creating a Point3D with the constructor: Point3D(double, double, double)
	      try 
	      {
	          new Point3D(1,2,3);
	      } 
	      catch (IllegalArgumentException e) 
	      {
	          fail("Failed constructing a correct Plane");
	      }
	      
	      // TC02: creating a Point3D with the constructor: Point3D(coordinate, coordinate, coordinate)
	      try 
	      {
	          new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
	      } 
	      catch (IllegalArgumentException e) 
	      {
	          fail("Failed constructing a correct Plane");
	      }
	      
	      // TC03: creating a Point3D with the constructor: Point3D(Point3D)
	      try 
	      {
	          new Point3D(new Point3D(1,2,3));
	      } 
	      catch (IllegalArgumentException e) 
	      {
	          fail("Failed constructing a correct Plane");
	      }
	  }

	/**
	 * Test to equals function in Point3D class
	 * equal between point to itself
	 */
	@Test
	public void equals_Test() 
	{
		Point3D p = new Point3D(1,1,1);
		assertEquals(p.equals(p), true);
	}
	
	/**
	 * Test to equals function in Point3D class
	 * equal between point to the same value point
	 */
	@Test
	public void equals_Test2() 
	{
		Point3D p1 = new Point3D(1,1,1);
		Point3D p2 = new Point3D(1,1,1);
		assertEquals(p1.equals(p2), true);
	}
	
	/**
	 * Test to add function in Point3D class
	 * add point to ZERO (1,1,1)+(0,0,0)=(1,1,1)
	 */
	@Test
	public void add_Test1() 
	{
		Vector v = new Vector(1, 1, 1);
		assertEquals(Point3D.ZERO.add(v), new Point3D(1, 1, 1));
	}
	
	/**
	 * Test to add function in Point3D class
	 * add point to the negative point (1,1,1)+(-1,-1,-1)=(0,0,0)
	 */
	@Test
	public void add_Test2() 
	{
		Vector v = new Vector(1, 1, 1);
		Point3D p = new Point3D(-1, -1, -1);
		assertEquals(p.add(v), Point3D.ZERO);
	}
	
	/**
	 * Test to subtract function in Point3D class
	 * subtract ZERO point from point and create a vector (1,1,1)-(0,0,0)=(1,1,1)
	 */
	@Test
	public void subtract_Test1() 
	{
		Point3D p = new Point3D(1, 1, 1);
		assertEquals(Point3D.ZERO.subtract(p), new Vector(1, 1, 1));
	}
	
	/**
	 * Test to distanceSquared function in Point3D class
	 * calculate the distance between two points
	 */
	@Test
	public void distanceSquared_Test1()
	{
		Point3D p = new Point3D(1, 1, 1);
		assertTrue(Point3D.ZERO.distanceSquared(p) == 3.0);
	}	
	
	/**
	 * Test to distanceSquared function in Point3D class
	 * calculate the distance between point to itself
	 */
	@Test
	public void distanceSquared_Test2()
	{
		Point3D p = new Point3D(1, 1, 1);
		assertTrue(p.distanceSquared(p) == 0.0);
	}
	
	/**
	 * Test to distanceSquared function in Point3D class
	 * calculate the distance between two points
	 */
	@Test
	public void distanceSquared_Test3()
	{
		Point3D p1 = new Point3D(1, 1, 1);
		Point3D p2 = new Point3D(3, 3, 3);
		assertTrue(p1.distanceSquared(p2) == 12.0);
	}
	
	/**
	 * Test to distance function in Point3D class
	 * calculate the distance between two points
	 */
	@Test
	public void distance_Test1()
	{
		Point3D p = new Point3D(1, 1, 1);
		assertTrue(Point3D.ZERO.distance(p) == Math.sqrt(3.0));
	}
	
	/**
	 * Test to distance function in Point3D class
	 * calculate the distance between point to itself
	 */
	@Test
	public void distance_Test2()
	{
		Point3D p = new Point3D(1, 1, 1);
		assertTrue(p.distance(p) == 0.0);
	}

}
