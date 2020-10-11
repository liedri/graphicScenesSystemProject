package elements;
import primitives.*;

/**
 * the class represent a point light - with location without direction - like lamp
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class PointLight extends Light implements LightSource{
	
	protected Point3D _position;
	protected double _kC, _kL, _kQ;
	
	
	/**
	 * Constructor
	 * 
	 * @param position - point
	 * @param kC - Exclusion constant
	 * @param kL - Exclusion constant
	 * @param kQ - Exclusion constant
	 * @param c - color = intensity
	 */
	public PointLight(Point3D position, double kC, double kL, double kQ, Color c){
		super(c);
		_position = position;
		_kC = kC;
		_kL = kL; 
		_kQ = kQ;
	}
	
	
	/**
	 * getter for the direction that the point light hit the point
	 * 
	 * @param p - Point3D
	 * @return a normalize direction - vector
	 */
	public Vector getL(Point3D p){
		return _position.subtract(p).normalize();
	}
	
	
	/**
	 * getter to the intensity (color) according to the point and the point light
	 * 
	 * @param p - Point3D
	 * @return color (the intensity in the superclass with the calculate
	 * 		   of the distance and how it impact on the intensity)
	 */
	public Color getIntensity(Point3D p){
		double d1 = _position.distance(p);
		double d2 = _position.distanceSquared(p);
		double mechane = _kC + (_kL * d1) + (_kQ * d2);
		return super.get_intensity().scale(1/mechane);
	}	
	
	
	/**
	 * @param p - Point3D
	 * @return the distance between point and the position of lightSource.
	 */
	public double getDistance(Point3D p){
		return p.distance(_position);
	}
}
