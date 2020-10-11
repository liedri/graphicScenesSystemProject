package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import geometries.*;
import primitives.*;
import org.junit.Test;
import geometries.Intersectable.GeoPoint;

/**
 * Unit Test case to the class Triangle
 * @author Li Edri
 * @author Adi Giladi
 *
 */

public class Triangle_Testing {
	
	/**
	 * Test to the Constructor of Triangle
	 */
	@Test
	public void Constructor_test() 
	  {
	      // ============ Equivalence Partitions Tests ==============

	      // TC01: Creating the Triangle- {(1,0,0), (0,1,0), (0,0,1) }
		  //       with the Constructor: Triangle(Point3D, Point3D, Point3D)
	      try 
	      {
	          new Triangle(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(0,0,1));
	      } 
	      catch (IllegalArgumentException e) 
	      {
	          fail("Failed constructing a correct Triangle");
	      }
	  }
	
//	/**
//     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
//     */
//    @Test
//    public void testGetNormal() {
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: There is a simple single test here
//        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
//                new Point3D(-1, 1, 1));
//        double sqrt3 = Math.sqrt(1d / 3);
//        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
//    }

	
	/**
	 * Test to the function getNormal(Point3D) that return the normal of the Triangle
	 */
	@Test
	public void getNormal_test() 
	{
		Triangle t=new Triangle(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(0,0,1));
		assertEquals(t.getNormal(new Point3D(1,0,0)), new Vector(0.5773502691896258, 0.5773502691896258, 0.5773502691896258));
	}
	
	  /**
	   * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	   */
	  @Test
	  public void testFindIntersections() {
		  Triangle triangle = new Triangle(new Point3D(1,0,0), new Point3D(3,0,0), new Point3D(1,0,2));

	      // ============ Equivalence Partitions Tests ==============

	      // TC01: Ray's line is intersects the triangle (1 points)
			List<Point3D> lst = new ArrayList<Point3D>();
			lst.add(new Point3D(2,0,0.5));
			List<GeoPoint> result = triangle.findIntersections(new Ray(new Point3D(2, 1, 0.5), new Vector(0, -1, 0)));
			 assertEquals("Wrong number of points", 1, result.size());
	      assertEquals("Ray's line not intersects the triangle", lst, result);

	      // TC02: Ray is outside against edge (0 points)
	      assertEquals("Ray's line not intersects the triangle", null, triangle.findIntersections(new Ray(new Point3D(2,1,-1), new Vector(0,-1,0))));
	      
	      // TC03: Ray is outside against vertex (0 points)
	      assertEquals("Ray's line not intersects the triangle", null, triangle.findIntersections(new Ray(new Point3D(8,1,-1), new Vector(0,-1,0))));

	      // =============== Boundary Values Tests ==================

	   // TC04: Ray is on edge (0 points)
	      assertEquals("Ray's line not intersects the triangle", null, triangle.findIntersections(new Ray(new Point3D(2,1,0), new Vector(0,-1,0))));
	      
	   // TC05: Ray is on vertex (0 points)
	      assertEquals("Ray's line not intersects the triangle", null, triangle.findIntersections(new Ray(new Point3D(1,1,0), new Vector(0,-1,0))));
	      
	   // TC06: Ray is on edge's continuation (0 points)
	      assertEquals("Ray's line not intersects the triangle", null, triangle.findIntersections(new Ray(new Point3D(4,1,0), new Vector(0,-1,0))));
	      
	    
	  }
}
