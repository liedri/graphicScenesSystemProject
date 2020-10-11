package elements;
import primitives.*;

/**
 * the class represent a spot light - with location and direction - like lantern
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class SpotLight extends PointLight{

	Vector _direction;
	double _Sc;
	
	
	/**
	 * Constructor
	 * 
	 * @param v - the vector of the direction
	 * @param position - Point3D
	 * @param kC - Exclusion constant
	 * @param kL - Exclusion constant
	 * @param kQ - Exclusion constant
	 * @param c - color = intensity
	 */
	public SpotLight(Vector v, Point3D position, double kC, double kL, double kQ, Color c){
		this(v, position, kC, kL, kQ, 1, c);
	}
	
	
	/**
	 * Constructor
	 * 
	 * @param v - the vector of the direction
	 * @param position - Point3D
	 * @param kC - Exclusion constant
	 * @param kL - Exclusion constant
	 * @param kQ - Exclusion constant
	 * @param c - color = intensity
	 * @param Sc - constant of the Specular component
	 */
	public SpotLight(Vector v, Point3D position, double kC, double kL, double kQ, double Sc, Color c){
		super(position, kC, kL, kQ, c);
		_direction = v.normalize();
		_Sc = Sc;
	}
	
	
	/**
	 * getter to the intensity (color) according to the point and the spot light
	 * 
	 * @param p - Point3D
	 * @return color (the intensity in the superclass with the calculate
	 * of the distance, angle and how it impact on the intensity)
	 */
	public Color getIntensity(Point3D p){
		double d1 = _position.distance(p);
		double d2 = _position.distanceSquared(p);
		double mechane = _kC + (_kL * d1) + (_kQ * d2);
		
		double cos_a = getL(p).dotProduct(_direction);
		if (cos_a > 0 )
		{
			
			double a2 = Math.toDegrees((Math.acos(cos_a)))*(1/_Sc);
			if (a2 > -90 && a2 < 90 )
			{
				double dir_l = Math.cos(Math.toRadians(a2));
				return super.get_intensity().scale(dir_l / mechane);
			}
		}
		
		return super.get_intensity().scale(0);	
	}
}

