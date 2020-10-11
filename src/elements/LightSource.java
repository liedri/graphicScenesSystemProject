package elements;
import primitives.Color;
import primitives.*;

/**
 * this interface is for all of the kink of the light that have a source -
 * for example ambient light is not a light source
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public interface LightSource {

	/**
	 * getter to the intensity according to the point -
	 * every kind of light calculate it different
	 * @param p
	 * @return color
	 */
	public Color getIntensity(Point3D p);
	
	
	/**
	 * getter to the vector of the direction that the light hit the
	 * point according to the kind of the light and the point 
	 * @param p
	 * @return
	 */
	public Vector getL(Point3D p);
	
	
	/**
	 * @param p - Point3D
	 * @return the distance between point and the position of lightSource.
	 */
	public double getDistance(Point3D p);

}
