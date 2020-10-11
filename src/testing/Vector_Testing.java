package testing;

import primitives.*;
import static org.junit.Assert.*;
//import java.lang.*;

import org.junit.Test;

/**
 * Unit Test case to the class Vector
 * @author Li Edri
 * @author Adi Giladi
 *
 */
	public class Vector_Testing {	
		/**
		 * Test method for
	     * the constructor of the Ray
	     */
		 @Test
		 public void Constructor_test() 
		 {
		    // ============ Equivalence Partitions Tests =============

		    // TC01: creating a Vector with the constructor Vector(Coordinate, Coordinate, Coordinate)
		      try 
		      {
		          new Vector(new Coordinate(1), new Coordinate(-2), new Coordinate(5));
		      } 
		      catch (IllegalArgumentException e) 
		      {
		          fail("Failed constructing a correct Plane");
		      }
		      
		      //TC02: creating a Vector with the constructor Vector(double, double, double)
		      try 
		      {
		          new Vector(1, -2, 5);
		      } 
		      catch (IllegalArgumentException e) 
		      {
		          fail("Failed constructing a correct Plane");
		      }
		      
		    //TC03: creating a Vector with the constructor Vector(Point3D)
		      try 
		      {
		          new Vector(new Point3D(1, -2, 5));
		      } 
		      catch (IllegalArgumentException e) 
		      {
		          fail("Failed constructing a correct Plane");
		      }
		      
		    //TC04: creating a Vector with the copy constructor Vector(Vector)
		      try 
		      {
		          new Vector(new Vector(new Point3D(1, -2, 5)));
		      } 
		      catch (IllegalArgumentException e) 
		      {
		          fail("Failed constructing a correct Plane");
		      }
		 }
		
	/**
	 * Test to check the function equals 
	 */
	@Test
	public void equals_test() {
		// ============ Equivalence Partitions Tests ==============
		//TC01
		Vector v1=new Vector(1,2,3);
		Vector v2=new Vector(2,2,3);
		assertFalse(v1.equals(v2));
		
		//TC02
		v1=new Vector(1,2,3);
		v2=new Vector(1,2,3);
		assertTrue(v1.equals(v2));
	}
	
	/**
	 * Test to check the function add 
	 */
	@Test
	public void add_test() 
	{
		// ============ Equivalence Partitions Tests ==============
		
		//TC01
		Vector v1=new Vector(1,2,3);
		Vector v2=new Vector(1,2,3);
		assertEquals(v1.add(v2), new Vector(2,4,6));
		
		//TC02
		v1=new Vector(1,2,3);
		assertEquals(v1.add(v1), new Vector(2,4,6));
		
		//TC03
		v1=new Vector(-1,-2,-3);
		v2=new Vector(-1,-1,-1);
		assertEquals(v1.add(v2), new Vector(-2,-3,-4));
		
		//TC04: acute angle
		v1=new Vector(1,2,3);
		v2=new Vector(4,5,6);
		assertEquals(v1.add(v2), new Vector(5,7,9));

		//TC05: obtuse angle
		v1=new Vector(1,2,3);
		v2=new Vector(8,-7,-2);
		assertEquals(v1.add(v2), new Vector(9,-5,1));

		// =============== Boundary Values Tests ==================
		
		//TC06: Vectors in the same direction
		v1=new Vector(1,2,3);
		v2=new Vector(2,4,6);
		assertEquals(v1.add(v2), new Vector(3,6,9));
		
		//TC07: Orthogonal
		v1=new Vector(1,0,0);
		v2=new Vector(0,1,1);
		assertEquals(v1.add(v2), new Vector(1,1,1));
	}
	
	/**
	 * Test to check the function subtract
	 */
	@Test
	public void subtract_test() 
	{
		// ============ Equivalence Partitions Tests ==============
		
		//TC01
		Vector v1=new Vector(-1,-2,-3);
		Vector v2=new Vector(-1,-1,-1);
		assertEquals(v1.subtract(v2), new Vector(0,1,2));
		
		//TC02
		v1=new Vector(-1,-2,0);
		v2=new Vector(-1,0,-1);
		assertEquals(v1.subtract(v2), new Vector(0,2,-1));

		//TC03: Vectors in the same direction
		v1=new Vector(1,1,1);
		v2=new Vector(4,4,4);
		assertEquals(v1.subtract(v2), new Vector(3,3,3));
		
		//TC04: Orthogonal
		v1=new Vector(1,0,0);
		v2=new Vector(0,1,1);
		assertEquals(v1.subtract(v2), new Vector(-1,1,1));
	}
	
	/**
	 * Test to check the function scale
	 */
	@Test
	public void scale_test() 
	{
		// ============ Equivalence Partitions Tests ==============
		
		//TC01
		Vector v1=new Vector(1,1,1);
		assertEquals(v1.scale(3), new Vector(3,3,3));

		//TC02
		v1=new Vector(1,1,0);
		assertEquals(v1.scale(-1), new Vector(-1,-1,0));

		//TC03
		v1=new Vector(2,0,4);
		assertEquals(v1.scale(1), new Vector(2,0,4));
	}
	
	/**
	 * Test to check the function dotProduct
	 */
	@Test
	public void dotProduct_test() 
	{
		// ============ Equivalence Partitions Tests ==============
		
		//TC01: acute angle
		Vector v1=new Vector(2,0,4);
		Vector v2=new Vector(1,1,3);
		assertTrue(v1.dotProduct(v2)==14);

		//TC02
		v1=new Vector(1.2,3,3);
		v2=new Vector(1,-2,2);
		Double result= new Double(v1.dotProduct(v2));
		Double expected=new Double(1.2);
		assertEquals(result, expected,0.00000001);
		
		//TC03: Vectors in the same direction
		v1=new Vector(2,3,4);
		v2=new Vector(4,6,8);
		assertTrue(v1.dotProduct(v2)==58);
		
		//TC04: obtuse angle
		v1=new Vector(1,2,3);
		v2=new Vector(8,-7,-2);
		assertTrue(v1.dotProduct(v2)==-12);
		
		//TC05: opposite direction
		v1=new Vector(1,2,3);
		v2=new Vector(-2,-4,-6);
		assertTrue(v1.dotProduct(v2)==-28);
		
		// =============== Boundary Values Tests ==================

		//TC06: Orthogonal
		v1=new Vector(1,0,0);
		v2=new Vector(0,-1,1);
		assertTrue(v1.dotProduct(v2)==0);
		
	}
	
	/**
	 * Test to check the function crossProduct
	 */
	@Test
	public void crossProduct_test() 
	{
		// ============ Equivalence Partitions Tests ==============
		
		//TC01
		Vector v1=new Vector(1,0, 1);
		Vector v2=new Vector(-1,2,0);
		assertEquals(v1.crossProduct(v2),new Vector(-2,-1,2));

		//TC02
		v1=new Vector(1, 2, 3);
		v2=new Vector(3,4,-5);
		assertEquals(v1.crossProduct(v2),new Vector(-22,14,-2));
		
		// =============== Boundary Values Tests ==================
		
		//TC03: Orthogonal
		v1=new Vector(1, 0, 0);
		v2=new Vector(0,1,0);
		assertEquals(v1.crossProduct(v2),new Vector(0,0,1));
		
	}
	
	/**
	 * Test to check the function lengthSquared
	 */
	@Test
	public void lengthSquared_test() 
	{
		// ============ Equivalence Partitions Tests ==============
		
		//TC01
		Vector v1=new Vector(1,0, -1);
		Double result=new Double(v1.lengthSquared());
		Double expected=new Double(2);
		assertEquals(result,expected,0.00000001);
		
		//TC02
		v1=new Vector(1, 2, 3);
		result=new Double(v1.lengthSquared());
		expected=new Double(14);
		assertEquals(result,expected,0.00000001);

		//TC03
		v1=new Vector(-3,-2.3, 1);
		result=new Double(v1.lengthSquared());
		expected=new Double(15.29);
		assertEquals(result,expected,0.00000001);
	}
	
	/**
	 * Test to check the function length
	 */
	@Test
	public void length_test() 
	{
		// ============ Equivalence Partitions Tests ==============
		//TC01
		Vector v1=new Vector(-3,-2.3, 1);
		Double result=new Double(v1.length());
		Double expected=new Double(3.910242959);
		assertEquals(result,expected,0.00000001);

		//TC02
		v1=new Vector(1, 2, 3);
		result=new Double(v1.length());
		expected=new Double(3.741657387);
		assertEquals(result,expected,0.00000001);

		// =============== Boundary Values Tests ==================
		//TC03
		v1=new Vector(0, 1, 0);
		result=new Double(v1.length());
		expected=new Double(1);
		assertEquals(result,expected,0.00000001);
	}
	
	/**
	 * Test to check the function normalize
	 */
	@Test
	public void normalize_test() 
	{
		// ============ Equivalence Partitions Tests ==============
		//TC01
		Vector v1=new Vector(1, 2, 3);
		assertEquals(v1.normalize(),v1);

		//TC02
		v1=new Vector(1, -1, 1.3);
		assertEquals(v1.normalize(),new Vector(0.5205792062953535, -0.5205792062953535, 0.6767529681839596));

		// =============== Boundary Values Tests ==================
		//TC03
		v1=new Vector(0,1,0);
		assertEquals(v1.normalize(),new Vector(0,1,0));
	}
	
	/**
	 * test to check the function normalized
	 */
	@Test
	public void normalized_test() 
	{
		// ============ Equivalence Partitions Tests ==============

		//TC01
		Vector v1=new Vector(1, -1, 1.3);
		assertEquals(v1.normalized(),new Vector(0.5205792062953535, -0.5205792062953535, 0.6767529681839596));
		
		// =============== Boundary Values Tests ==================
		
		//TC02
		v1=new Vector(0,1,0);
		v1.normalized();
		assertEquals(v1,new Vector(0,1,0));
	}
	
}
