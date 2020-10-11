package testing;
import static org.junit.Assert.*;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;


/**
 * Unit Test case to the class Coordinate
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class Coordinate_Testing {

	/**
	   * Test method for
	   * the constructor of the Point3D
	   */
	  @Test
	  public void Constructor_test() 
	  {
	      // ============ Equivalence Partitions Tests ==============

	      // TC01: creating a Coordinate with the constructor: Coordinate(double)
	      try 
	      {
	          new Coordinate(3);
	      } 
	      catch (IllegalArgumentException e) 
	      {
	          fail("Failed constructing a correct Plane");
	      }
	  }

}
