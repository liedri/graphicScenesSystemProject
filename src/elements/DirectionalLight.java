package elements;
import primitives.*;

/**
 * this class  represent a light with direction without location - like the sun
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class DirectionalLight extends Light implements LightSource{

	private Vector _direction;
	
	/**
	 * Constructor
	 * 
	 * @param direction - vector
	 * @param c - color = intensity
	 */
	public DirectionalLight(Vector direction, Color c){
		super(c);
		_direction = direction.normalized();
	}
	
	
	/**
	 * getter for the direction
	 * 
	 * @param p - Point3D
	 * @return a normalize direction - vector
	 */
	public Vector getL(Point3D p){
		return _direction;
	}
	
	
	/**
	 * getter to the intensity (color) according to the point
	 * 
	 * @param p - Point3D
	 * @return color (the intensity in the superclass)
	 */
	public Color getIntensity(Point3D p){
		return super.get_intensity();
	}
	
	
	/**
	 * @param p - Point3D
	 * @return the distance between point and the position of lightSource.
	 */
	public double getDistance(Point3D p){
		return Double.POSITIVE_INFINITY;
	}
}
