package primitives;
//import static primitives.Util.*;
import static primitives.Point3D.*; 
import java.lang.IllegalArgumentException;

/**
 * Class Vector is representing a vector in space
 * @author Li Edri
 * @author Adi Giladi
 */

public final class Vector {
	
	/**
     * Vector value, intentionally "package-friendly" due to performance constraints
     * Consists of Point values
     */
	protected Point3D _point;

    /**
     * Vector constructor receiving coordinates values
     * @param x coordinates value
     * @param y coordinates value
     * @param z coordinates value
     * @throws IllegalArgumentException
     */
	public Vector(Coordinate x, Coordinate y, Coordinate z) throws IllegalArgumentException
    {
		Point3D p = new Point3D(x,y,z);
		if (p.equals(ZERO))
		{
			throw new IllegalArgumentException("Vector 0");
		}
		_point = p;
    }
	
    /**
     * Vector constructor receiving double values
     * @param x double value
     * @param y double value
     * @param z double value  
     * @throws IllegalArgumentException   
     */
	public Vector(double x, double y, double z) throws IllegalArgumentException
	{
		Point3D p = new Point3D(x,y,z);
		if (p.equals(ZERO))
		{
			throw new IllegalArgumentException("Vector 0");
		}
		_point = p;
	}
		
    /**
     * Vector constructor receiving point value
     * @param point- point value
     * @throws IllegalArgumentException
     */
    public Vector(Point3D point) throws IllegalArgumentException
    {
        if (point.equals(ZERO))
        {
        	throw new IllegalArgumentException("Vector 0");
        }
        _point = point;
    }

    /**
     * Copy constructor for vector
     * @param vector- vector value
     */
    public Vector(Vector vector) {
        _point = vector._point;
    }

    /**
     * Vector values getter
     * @return Vector value
     */
	public Point3D get_point() {
		return _point;
	}

    /**
     * Vector value comparing
     * @return true/false
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = ((Vector) obj).normalized();
		Vector v = this.normalized();
		if (v._point == null) 
		{
			if (other._point != null)
				return false;
		} 
		else if (!v._point.equals(other._point))
			return false;
		return true;
	}

    /**
     * Vector value in string
     * @return string of ray value
     */
	@Override
	public String toString() {
		return _point.toString();
	}

	/**
	 * A function that adding a vector to another vector
	 * @param v - vector value
	 * @return new vector
	 */
	public Vector add(Vector v) {
		
		return new Vector(_point.add(v));
	}
	
	/**
	 * A function that subtract a vector from another vector
	 * @param v - vector value
	 * @return new vector
	 */
	public Vector subtract(Vector v) {
		
		return _point.subtract(v.get_point());
	}
	
	/**
	 * A function that Multiplier vector and scalar
	 * @param num- double value
	 * @return new vector
	 */
	public Vector scale(double num) {
		
		Point3D p = new Point3D((_point.get_x().get() * num), (_point.get_y().get() * num), (_point.get_z().get() * num));
		return new Vector(p);
	}
	
	/**
	 * A function that calculate a Scalar multiplication
	 * @param v- vector value
	 * @return double 
	 */
	public double dotProduct(Vector v) {

		double x = _point.get_x().get() * v.get_point().get_x().get();
		double y = _point.get_y().get() * v.get_point().get_y().get();
		double z = _point.get_z().get() * v.get_point().get_z().get();
		
		return (x+y+z);
	}
	
	/**
	 * A function that calculate cross product
	 * @param v- vector value
	 * @return new vector which vertical to the vectors
	 */
	public Vector crossProduct(Vector v) {
		
		double x = (_point.get_y().get() * v.get_point().get_z().get()) - (_point.get_z().get() * v.get_point().get_y().get());
		double y = (_point.get_z().get() * v.get_point().get_x().get()) - (_point.get_x().get() * v.get_point().get_z().get());
		double z = (_point.get_x().get() * v.get_point().get_y().get()) - (_point.get_y().get() * v.get_point().get_x().get());
		
		return new Vector(x,y,z);
	}
	
	/**
	 * A function that calculate the length of the vector(without square)
	 * @return double
	 */
	public double lengthSquared() {
		return _point.distanceSquared(ZERO);
	}
	
	/**
	 * A function that calculate the length of the vector(with square)
	 * @return double
	 */
	public double length() {
		
		return Math.sqrt(lengthSquared());
	}
	
	/**
	 * A function that calculate the normalized vector (and change the vector)
	 * @return the reference of this vector
	 */
	public Vector normalize() {
		
		double dis = length();
		_point = new Point3D(_point.get_x().get()/dis , _point.get_y().get()/dis, _point.get_z().get()/dis);
		return this;
	}
	
	/**
	 * A function that calculate the normalized vector (without changing the vector)
	 * @return a new vector
	 */
	public Vector normalized() {
		
		Vector v = new Vector(this);
		return v.normalize();
	}
}
