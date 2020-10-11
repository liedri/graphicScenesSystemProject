package testing;

import static org.junit.Assert.*;
import geometries.*;
import primitives.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;
import geometries.Intersectable.GeoPoint;

/**
 * Unit Test case to the class Sphere
 * @author Li Edri
 * @author Adi Giladi
 *
 */

public class Sphere_Testing {
	
	/**
	 * Test to the Constructor of Sphere
	 */
	@Test
	public void Constructor_test() 
	  {
	      // ============ Equivalence Partitions Tests ==============

	      // TC01: Creating the Sphere radius=3, Point3D=(1,0,0) with the 
		  //       Constructor- Sphere(Point3D,double)
	      try 
	      {
	          new Sphere(new Point3D(1,0,0), 3);
	      } 
	      catch (IllegalArgumentException e) 
	      {
	          fail("Failed constructing a correct Plane");
	      }
	  }
	
	
	/**
	 * Test to the function getNormal(Point3D) that return the normal of the Sphere
	 */
	@Test
	public void getNormal_test() 
	{
		Sphere s=new Sphere(new Point3D(1,0,0), 3);
		assertEquals(s.getNormal(new Point3D(4,1,1)), new Vector(3,1,1).normalized());
	}



/**
 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
 */
@Test
public void testFindIntersections() {
    Sphere sphere = new Sphere(new Point3D(1, 0, 0),1d);

    // ============ Equivalence Partitions Tests ==============

    // TC01: Ray's line is outside the sphere (0 points)
    assertEquals("Ray's line out of sphere", null,
                    sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

    // TC02: Ray starts before and crosses the sphere (2 points)
    Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
    Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
    List<GeoPoint> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
    assertEquals("Wrong number of points", 2, result.size());
    if (result.get(0)._point.get_x().get() > result.get(1)._point.get_x().get())
    {
    	Point3D p3 = result.get(1)._point;
    	Point3D p4 = result.get(0)._point;
    	result.clear();
        result.add(new GeoPoint(sphere, p3));
        result.add(new GeoPoint(sphere, p4));
    }

    List<Point3D> temp = new ArrayList<Point3D>();
    temp.add(p1);
    temp.add(p2);
    assertEquals("Ray crosses sphere", temp, result);

    // TC03: Ray starts inside the sphere (1 point)
    p1=new Point3D(2,0,0);
    result.clear();
    result=sphere.findIntersections(new Ray(new Point3D(0.25,0,0), new Vector(1,0,0)));
    assertEquals("Wrong number of points", 1, result.size());
    assertEquals("Wrong point", p1, result.get(0));
    

    // TC04: Ray starts after the sphere (0 points)
    result.clear();
    result=sphere.findIntersections(new Ray(new Point3D(3,0,0), new Vector(1,0,0)));
    assertEquals("Wrong point", null, result);
    

    // =============== Boundary Values Tests ==================

    // **** Group: Ray's line crosses the sphere (but not the center)
    // TC11: Ray starts at sphere and goes inside (1 points)
    p1=new Point3D(1,0,-1);
    result=sphere.findIntersections(new Ray(new Point3D(1,0,1), new Vector(0,0,-2)));
    assertEquals("Wrong number of points", 1, result.size());
    assertEquals("Wrong point", p1, result.get(0));
    
    // TC12: Ray starts at sphere and goes outside (0 points)
    result.clear();
    result=sphere.findIntersections(new Ray(new Point3D(1,0,1), new Vector(0,0,1)));
    assertEquals("Wrong point", null, result);

    // **** Group: Ray's line goes through the center
    // TC13: Ray starts before the sphere (2 points)
    p1=new Point3D(1,1,0);
    p2=new Point3D(1,-1,0);
    result=sphere.findIntersections(new Ray(new Point3D(1,2,0), new Vector(0,-1,0)));
    temp = new ArrayList<Point3D>(); 
    temp.add(p2);
    temp.add(p1);
    assertEquals("Wrong number of points", 2, result.size());
    assertEquals("Wrong point", temp, result);
    
    // TC14: Ray starts at sphere and goes inside (1 points)
    result.clear();
    p1=new Point3D(1,-1,0);
    result=sphere.findIntersections(new Ray(new Point3D(1,1,0), new Vector(0,-2,0)));
    assertEquals("Wrong number of points", 1, result.size());
    assertEquals("Wrong point", p1, result.get(0));
    
    // TC15: Ray starts inside (1 points)
    result.clear();
    p1=new Point3D(1,-1,0);
    result=sphere.findIntersections(new Ray(new Point3D(1,0.5,0), new Vector(0,-2,0)));
    assertEquals("Wrong number of points", 1, result.size());
    assertEquals("Wrong point", p1, result.get(0));
    
    // TC16: Ray starts at the center (1 points)
    result.clear();
    p1=new Point3D(1,-1,0);
    result=sphere.findIntersections(new Ray(new Point3D(1,0,0), new Vector(0,-2,0)));
    assertEquals("Wrong number of points", 1, result.size());
    assertEquals("Wrong point", p1, result.get(0));
    
    // TC17: Ray starts at sphere and goes outside (0 points)
    result.clear();
    result=sphere.findIntersections(new Ray(new Point3D(1,0,-1), new Vector(0,0,-1)));
    assertEquals("Wrong point", null, result);
    
    // TC18: Ray starts after sphere (0 points)
    result=sphere.findIntersections(new Ray(new Point3D(3,0,0), new Vector(1,0,0)));
    assertEquals("Wrong point", null, result);

    // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
    // TC19: Ray starts before the tangent point
    result=sphere.findIntersections(new Ray(new Point3D(0,0,1), new Vector(1,0,0)));
    assertEquals("Wrong point", null, result);
    
    // TC20: Ray starts at the tangent point
    result=sphere.findIntersections(new Ray(new Point3D(1,0,1), new Vector(1,0,0)));
    assertEquals("Wrong point", null, result);
    
    // TC21: Ray starts after the tangent point
    result=sphere.findIntersections(new Ray(new Point3D(2,0,1), new Vector(1,0,0)));
    assertEquals("Wrong point", null, result);
    
    // **** Group: Special cases
    // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
    result=sphere.findIntersections(new Ray(new Point3D(3,0,0), new Vector(0,0,3)));
    assertEquals("Wrong point", null, result);
    }
}

	
