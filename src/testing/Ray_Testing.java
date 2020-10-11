package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;

/**
 * Unit Test case to the class Ray
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class Ray_Testing {

	/**
	   * Test method for
	   * the constructor of the Ray
	   */
	  @Test
	  public void Constructor_test() 
	  {
	      // ============ Equivalence Partitions Tests ==============

	      // TC01: creating a Ray with the constructor Ray(Point3D, Vector)
	      try 
	      {
	          new Ray(new Point3D(1,1,1), new Vector(1,2,3));
	      } 
	      catch (IllegalArgumentException e) 
	      {
	          fail("Failed constructing a correct Plane");
	      }
	      
	      // TC02: creating a Ray with the copy constructor Ray(Ray)
	      try 
	      {
	          new Ray(new Ray(new Point3D(1,0,0), new Vector(0,1,0)));
	      } 
	      catch (IllegalArgumentException e) 
	      {
	          fail("Failed constructing a correct Plane");
	      }
	  }
	  
	  
	  /**
	   * Test method for
	   * the method: equal- Ray.equals(Ray)
	   */
	  @Test
	  public void equals_test()
	  {
		  // ============ Equivalence Partitions Tests ==============
		  
		  //TC01: the rays are equals
		  Ray ray1= new Ray(new Point3D(1,1,1), new Vector(1,2,3));
		  Ray ray2= new Ray(new Point3D(1,1,1), new Vector(1,2,3));
		  assertTrue(ray1.equals(ray2));
		  
		  //TC02: the vectors are equals and the points are not equals.
		  ray1= new Ray(new Point3D(1,0,1), new Vector(1,2,3));
		  ray2= new Ray(new Point3D(1,1,1), new Vector(1,2,3));
		  assertFalse(ray1.equals(ray2));
		  
		  //TC03:  the vectors are not equals and the points are equals.
		  ray1= new Ray(new Point3D(1,1,1), new Vector(1,2,3));
		  ray2= new Ray(new Point3D(1,1,1), new Vector(1,2,2));
		  assertFalse(ray1.equals(ray2));
		
		  //TC04:  the vectors are not equals and also the points are not equals.
		  ray1= new Ray(new Point3D(1,1,1), new Vector(1,2,3));
		  ray2= new Ray(new Point3D(5,6,7), new Vector(-1,6,3));
		  assertFalse(ray1.equals(ray2));
	  }

}
