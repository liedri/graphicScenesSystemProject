package primitives;
//import static primitives.Util.*;
//import static primitives.Point3D.*;
//import static primitives.Vector.*;
import java.lang.IllegalArgumentException;

/**
 * Class Ray is representing a ray in space
 * @author Li Edri
 * @author Adi Giladi
 */

public class Ray {
	
	/**
     * Ray value, intentionally "package-friendly" due to performance constraints
     * Consists of Point and Vector values
     */
	protected Point3D _point;
	protected Vector _vector;
	private static final double DELTA = 0.1;

    /**
     * Ray constructor receiving point and vector values
     * @param point - the start of the ray
     * @throws IllegalArgumentException
     * @param vector - the direction of the ray
     */
    public Ray(Point3D point, Vector vector) throws IllegalArgumentException {
    	
       	_point = point;
       	_vector = vector.normalized();
    }
    
    /**
     * Copy constructor for ray
     * @param ray- ray value
     */
    public Ray(Ray ray) {
    	
        _point = ray._point;
        _vector = ray._vector;
    }
    
    
    /**
     * constructor
     * 
     * @param point - the start of the ray
     * @param direction - vector
     * @param normal - to tell in which direction to move the start point
     */
    public Ray(Point3D point, Vector direction, Vector normal) {
    	
        _vector = new Vector(direction).normalized();
        double nv = normal.dotProduct(direction);
        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        _point = point.add(normalDelta);

    }



    /**
     * Ray values getter
     * @param t - double value
     * @return Ray value
     */
	public Point3D get_point(double t) {
		return new Point3D(_point.add(_vector.scale(t)));
	}
	
	/**
     * Ray values getter
     * @return point value
     */
	public Point3D get_point() {
		return _point;
	}

	/**
     * Ray values getter
     * @return vector value
     */
	public Vector get_vector() {
		return _vector;
	}

    /**
     * Ray value comparing
     * @return true/false
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (_point == null) {
			if (other._point != null)
				return false;
		} else if (!_point.equals(other._point))
			return false;
		if (_vector == null) {
			if (other._vector != null)
				return false;
		} else if (!_vector.equals(other._vector))
			return false;
		return true;
	}

    /**
     * Ray value in string
     * @return string of ray value
     */
	@Override
	public String toString() {
		return _point.toString() + ", " + _vector.toString();
	}		
}