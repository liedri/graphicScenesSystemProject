package geometries;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

/**
 * Sphere class represents a Sphere in 3D Cartesian coordinate system
 * @author Li Edri
 * @author Adi Giladi 
 */
public class Sphere extends RadialGeometry{

	protected Point3D _point;
	
	/**
	 * Constructor
	 * 
	 * @param point - the center of the sphere
	 * @param r - radius
	 */
	public Sphere(Point3D point, double r) {
		this(new Color(java.awt.Color.BLACK), point, r, new Material(0,0,0));
	}
	
	
	/**
	 * Constructor that get in addition the color of the geometry
	 * 
	 * @param c - color
	 * @param point - the center of the sphere
	 * @param r - radius
	 */
	public Sphere(Color c, Point3D point, double r) {
		this(c, point, r, new Material(0,0,0));
	}

	
	/**
	 * Constructor that get in addition the color and the material of the geometry
	 * 
	 * @param c - color
	 * @param point - the center of the sphere
	 * @param r - radius
	 * @param material of the sphere
	 */
	public Sphere(Color c, Point3D point, double r, Material material) {
		super(c, r, material);
		_point = point;
	}
	
	
	/**
	 * getter to the point - the center of the sphere
	 * @return _point - the center of the sphere
	 */
	public Point3D get_point() {
		return _point;
	}

	@Override
	public String toString() {
		return "Sphere = " + _point.toString() +", " + get_radius();
	}
	
	/**
	 * @param p- Point value
	 * @return the normal (Vector value) of the Sphere 
	 */
	public Vector getNormal(Point3D p) {
		return _point.subtract(p).normalized();
	}
	
	
	/**
	 * this function find the intersections points with the geometry
	 * @param ray - to find the intersection with her
	 * @return list of Point3D
	 */
	public List<GeoPoint> findIntersections(Ray ray){
		if(ray.get_point().equals(_point))
		{
			List<GeoPoint> lst=new ArrayList<GeoPoint>();
			lst.add(new GeoPoint (this, ray.get_point(get_radius())));
			return lst;
		}
		Vector u=ray.get_point().subtract(_point);
		double tm=ray.get_vector().dotProduct(u);
		double d=Math.sqrt(u.lengthSquared()-tm*tm);
		if(d>super.get_radius())
			return null;
		double th=Math.sqrt(super.get_radius()*super.get_radius()-d*d);
		if((tm+th<=0.0 && tm-th<=0.0) || d==super.get_radius())
			return null;
		List<GeoPoint> lst=new ArrayList<GeoPoint>();
		if(tm+th>0.0)
		{
		lst.add(new GeoPoint(this, ray.get_point(tm+th)));
		}
		if(tm-th>0.0)
		{
			lst.add(new GeoPoint(this, ray.get_point(tm-th)));
		}
		if((tm+th<=0.0 && tm-th<=0.0) || d==super.get_radius())
			return null;
		return lst;
	}
}
