package primitives;
//import static primitives.Coordinate.*;
//import static primitives.Util.*
//import static primitives.Point3D.*;
//import static primitives.Vector.*;
import java.lang.IllegalArgumentException;

/**
 * Class Point is representing a Point in space
 * @author Li Edri
 * @author Adi Giladi
 */

public final class Point3D {
	
	/**
     * Point value, intentionally "package-friendly" due to performance constraints
     * Consists of Coordinate values
     */
	protected Coordinate _x;
	protected Coordinate _y;
	protected Coordinate _z;
	
	/**
	 * ZERO point- represent (0,0,0) point
	 */
	public static Point3D ZERO = new Point3D(0,0,0);

    /**
     * Point constructor receiving coordinates values
     * @param x - Coordinate value
     * @param y - Coordinate value
     * @param z - Coordinate value
     *      */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) 
    {
        _x = x;
        _y = y;
        _z = z;
    }
    
    /**
     * Point constructor receiving double values
     * @param x - double value
     * @param y - double value
     * @param z - double value
     */
    public Point3D(double x, double y, double z) 
    {
    	_x = new Coordinate(x);
    	_y = new Coordinate(y);
    	_z = new Coordinate(z);
    }
    
    /**
     * Copy constructor for point
     * @param point- point value
     */
    public Point3D(Point3D point) 
    {
        _x = point._x;
        _y = point._y;
        _z = point._z;
    }

    /**
     * Point3D values getter
     * @return Coordinate value
     */
	public Coordinate get_x() {
		return _x;
	}

	/**
	 * Point3D values getter
	 * @return Coordinate value
	 */
	public Coordinate get_y() {
		return _y;
	}

	/**
	 * Point3D values getter
	 * @return Coordinate value
	 */
	public Coordinate get_z() {
		return _z;
	}

    /**
     * Point value comparing
     * @return true/false
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (_x == null) {
			if (other._x != null)
				return false;
		} else if (!_x.equals(other._x))
			return false;
		if (_y == null) {
			if (other._y != null)
				return false;
		} else if (!_y.equals(other._y))
			return false;
		if (_z == null) {
			if (other._z != null)
				return false;
		} else if (!_z.equals(other._z))
			return false;
		return true;
	}

    /**
     * Point value in string
     * @return string of point value
     */
	@Override
	public String toString() {
		return "(" + _x + ", " + _y + ", " + _z + ")";
	}
	
	/**
	 * A function that connects 2 points
	 * @param point - point value
	 * @throws IllegalArgumentException
	 * @return new vector
	 */
	public Vector subtract(Point3D point) throws IllegalArgumentException {
		
		Coordinate x1 = new Coordinate(point.get_x().get() - get_x().get());
		Coordinate y1 = new Coordinate(point.get_y().get() - get_y().get());
		Coordinate z1 = new Coordinate(point.get_z().get() - get_z().get());
		
		return new Vector(new Point3D(x1, y1, z1));
	}
	
	/**
	 * A function that adding vector to point
	 * @param v- vector value
	 * @return new point
	 */
	public Point3D add(Vector v) {
		
		Coordinate x1 = new Coordinate(v.get_point().get_x().get() + get_x().get());
		Coordinate y1 = new Coordinate(v.get_point().get_y().get() + get_y().get());
		Coordinate z1 = new Coordinate(v.get_point().get_z().get() + get_z().get());

		return new Point3D(x1, y1, z1);
	}
	
	/**
	 * A function that Calculating the distance between 2 points (without squared)
	 * @param p- point value
	 * @return double that presents the distance
	 */
	public double distanceSquared(Point3D p) {
		
		double disX = (_x.get() - p.get_x().get()) * (_x.get() - p.get_x().get());
		double disY = (_y.get() - p.get_y().get()) * (_y.get() - p.get_y().get());
		double disZ = (_z.get() - p.get_z().get()) * (_z.get() - p.get_z().get());

		double dis = disX + disY + disZ;
		return dis;
	}
	
	/**
	 * A function that Calculating the distance between 2 points (with squared)
	 * @param p- point value
	 * @return double that presents the distance
	 */
	public double distance(Point3D p) {
		
		return Math.sqrt(distanceSquared(p));
	}
}