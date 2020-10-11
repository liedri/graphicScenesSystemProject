package geometries;
import java.util.List;

import primitives.*;

/**
 * Tube class represents a Tube in 3D Cartesian coordinate system
 * @author Li Edri
 * @author Adi Giladi
 */
public class Tube extends RadialGeometry{

	protected Ray _ray;
	
	/**
	 * Constructor
	 * 
	 * @param ray
	 * @param r - radius
	 */
	public Tube(Ray ray, double r) {
		this(new Color(java.awt.Color.BLACK), ray, r, new Material(0,0,0));
	}

	
	/**
	 * Constructor that get in addition the color of the geometry
	 * 
	 * @param c - the color of the tube
	 * @param ray - the cylinder of the tube
	 * @param r - the radius of the tube
	 */
	public Tube(Color c, Ray ray, double r) {
		this(c, ray, r, new Material(0,0,0));
	}

	
	/**
	 * Constructor that get in addition the color and the material of the geometry
	 * @param c - the color of the tube
	 * @param ray - the cylinder of the tube
	 * @param r - the radius of the tube
	 * @param material of the tube
	 */
	public Tube(Color c, Ray ray, double r, Material material) {
		super(c, r, material);
		_ray = ray;
	}
	
	
	/**
	 * getter to the ray
	 * @return ray
	 */
	public Ray get_ray() {
		return _ray;
	}

	@Override
	public String toString() {
		return "Tube = " + _ray.toString() + ", " + get_radius();
	}

	/**
	 * @param p- Point value
	 * @return the normal (Vector value) of the Tube 
	 */
	public Vector getNormal(Point3D p) {
		double t = _ray.get_vector().dotProduct(_ray.get_point().subtract(p));
		Point3D o = _ray.get_point().add(_ray.get_vector().scale(t));
		return o.subtract(p).normalized();

		
	}
	
	
	/**
	 * this function find the intersections points with the geometry
	 * @param ray - to find the intersection with her
	 * @return list of Point3D
	 */
	public List<GeoPoint> findIntersections(Ray ray){
		return null;
	}
	

}
