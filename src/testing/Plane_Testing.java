package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import geometries.*;
import primitives.*;
import org.junit.Test;
import geometries.Intersectable.GeoPoint;

/**
 * Unit Test case to the class Plane
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class Plane_Testing {

  /**
   * Test method for
   * the constructor of the Plane
   */
  @Test
  public void Constructor_test() 
  {
      // ============ Equivalence Partitions Tests ==============

      // TC01: creating the Plane: x + y + z = 1 with the constructor
	  //       that get 3 Point3D that contain in the Plane
      try 
      {
          new Plane(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(0,0,1));
      } 
      catch (IllegalArgumentException e) 
      {
          fail("Failed constructing a correct Plane");
      }
      
      // TC02: creating the Plane: x + y + z = 1 with the constructor
   	  //       that get Point3D that contain in the Plane and Vector- the normal of the Plane.
         try 
         {
             new Plane(new Point3D(1,0,0), new Vector(1,1,1));
         } 
         catch (IllegalArgumentException e) 
         {
             fail("Failed constructing a correct Plane");
         }
         
      // =============== Boundary Values Tests ==================
         
      // TC03: creating the Plane: z = 0 with the constructor
   	  //       that get 3 Point3D that contain in the Plane
         try 
         {
             new Plane(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(2,3,0));
         } 
         catch (IllegalArgumentException e) 
         {
             fail("Failed constructing a correct Plane");
         }
         
         // TC04: creating the Plane: x + y + z = 1 with the constructor
      	 //       that get Point3D that contain in the Plane and Vector- the normal of the Plane.
         try 
         {
             new Plane(new Point3D(1,0,0), new Vector(0,0,1));
         } 
         catch (IllegalArgumentException e) 
         {
             fail("Failed constructing a correct Plane");
         }
  }
  /**
   * Test to check the function lengthSquare
   */
  @Test
  public void getNormal_test() 
  {
	  // ============ Equivalence Partitions Tests ==============
	  // TC01
	  Plane pln= new Plane(new Point3D(1,0,0), new Vector(1,1,1));
	  assertEquals(pln.getNormal(new Point3D(0,1,0)), new Vector(0.5773502691896258, 0.5773502691896258, 0.5773502691896258));
  }
  
  /**
   * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
   */
  @Test
  public void testFindIntersections() {
      Plane plane = new Plane(new Point3D(1,0,0), new Vector(0,1,0));

      // ============ Equivalence Partitions Tests ==============

      // TC01: Ray's line is intersects the plane (1 points)
		List<Point3D> lst = new ArrayList<Point3D>();
		lst.add(new Point3D(1,0,0));
		List<GeoPoint> result = plane.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, -1, 0)));
		 assertEquals("Wrong number of points", 1, result.size());
      assertEquals("Ray's line not intersects the plane", lst, result);

      // TC02: Ray does not intersect the plane (0 points)
      assertEquals("Ray's line not intersects the plane", null, plane.findIntersections(new Ray(new Point3D(1,1,0), new Vector(0,0,1))));

      // =============== Boundary Values Tests ==================

      // **** Group: Ray's line  parallel to the plane (all 0 points)
      // TC03: the ray included in the plane
      assertEquals("Ray's line not intersects the plane", null, plane.findIntersections(new Ray(new Point3D(1,0,1), new Vector(0,0,1))));
      
      // TC04: the ray not included in the plane
      assertEquals("Ray's line not intersects the plane", null, plane.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0,0,1))));

      // **** Group: Ray is orthogonal to the plane 
      // TC05: p0 before the plane(1 points)
      lst = new ArrayList<Point3D>();
	  lst.add(new Point3D(1,0,0));
	  result =  plane.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, -1, 0)));
	  assertEquals("Wrong number of points", 1, result.size());
      assertEquals("Wrong point", lst, result );
      
      // TC06: p0 in the plane (0 points)
	  result =  plane.findIntersections(new Ray(new Point3D(1, 0, 1), new Vector(0, -1, 0)));
      assertEquals("Ray's line not intersects the plane", null, result );
      
      // TC07: p0 after the plane (0 points)
   	  result =  plane.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, -1, 0)));
      assertEquals("Ray's line not intersects the plane", null, result );
         
      
      // **** Group:Ray is neither orthogonal nor parallel to and begins at the plane (all 0 points)
      // TC08: p0 is in the plane
   	  result =  plane.findIntersections(new Ray(new Point3D(1, 0, 1), new Vector(0, -1, 1)));
      assertEquals("Ray's line not intersects the plane", null, result );
      
      // **** Group:Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q)
      // TC09: p0 is in the plane
   	  result =  plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, -1, 1)));
      assertEquals("Ray's line not intersects the plane", null, result );
      }
}
