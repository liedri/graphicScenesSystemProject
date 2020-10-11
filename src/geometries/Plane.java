package geometries;


import java.util.ArrayList;
import java.util.List;

import primitives.*;

/**
 * Plane class represents a plane in 3D Cartesian coordinate system
 * @author Li Edri
 * @author Adi Giladi
 */
public class Plane extends Geometry {

	protected Point3D _point;
	protected Vector _vector;
	
	/**
	 * Constructor - get 3 points and create a plane by them.
	 * 
	 * @param p1 - one of the 3 points that define the plane
	 * @param p2 - one of the 3 points that define the plane
	 * @param p3 - one of the 3 points that define the plane
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this(new Color(java.awt.Color.BLACK), p1, p2, p3);
	}
	
	
	/**
	 * Constructor - get 3 points and color and create a plane by them.
	 * @param c - color
	 * @param p1 - one of the 3 points that define the plane
	 * @param p2 - one of the 3 points that define the plane
	 * @param p3 - one of the 3 points that define the plane
	 */
	public Plane(Color c, Point3D p1, Point3D p2, Point3D p3) {
		super(c);
		_point = p1;
		Vector v1 = p2.subtract(p3);
		Vector v2 = p2.subtract(p1);
		_vector = (v1.crossProduct(v2)).normalize();
	}
	
	
	/**
	 * Constructor - get point, vector and create a plane by them.
	 * @param p - point
	 * @param v - vector = the normal of the plane
	 */
	public Plane(Point3D p, Vector v) {
		this(new Color(java.awt.Color.BLACK), p, v, new Material(0,0,0));
	}
	
	
	/**
	 * Constructor - get point, vector and color and create a plane by them.
	 * @param c - color
	 * @param p - point
	 * @param v - vector = the normal of the plane
	 */
	public Plane(Color c, Point3D p, Vector v) {
		this(c, p, v, new Material(0,0,0));
	}

	
	/**
	 * Constructor - get point, vector, material and color and create a plane by them.
	 * @param c - color
	 * @param p - point
	 * @param v - vector = the normal of the plane
	 * @param material of the geometry
	 */
	public Plane(Color c, Point3D p, Vector v, Material material) {
		super(c, material);
		_point=p;
		_vector=v;
	}
	
	
	/**
	 * getter to the point
	 * @return point- Point3D
	 */
	public Point3D get_point() {
		return _point;
	}

	
	/**
	 * getter to the vector
	 * @return vector - the vector
	 */
	public Vector get_vector() {
		return _vector;
	}

	@Override
	public String toString() {
		return "Plane =" + _point.toString() + _vector.toString();
	}
	
	
	/**
	 * this function  return the normal of the plane - if the point is on the plain
	 * @param p - point
	 * @return normalized normal (vector)
	 */
	public Vector getNormal(Point3D p) {
		if(p.equals(get_point()))
			return get_vector().normalize();
		Vector v = p.subtract(get_point());
		if (_vector.dotProduct(v) != 0)
			return null;
		return get_vector().normalize();
	}

	
	/**
	 * this function find the intersections points with the geometry
	 * @param ray - to find the intersection with her
	 * @return list of Point3D
	 */
	public List<GeoPoint> findIntersections(Ray ray){
		if(!ray.get_point().equals(get_point()))
		{
			if(get_vector().dotProduct(ray.get_vector()) == 0)
				return null;
			double t = (get_vector().dotProduct(ray.get_point().subtract(get_point())))/(get_vector().dotProduct(ray.get_vector()));

			if(t<=0)
				return null;
			Point3D p = ray.get_point(t);
			List<GeoPoint> lst = new ArrayList<GeoPoint>();
			lst.add(new GeoPoint(this, p));
			return lst;
		}
		return null;
	}
}
