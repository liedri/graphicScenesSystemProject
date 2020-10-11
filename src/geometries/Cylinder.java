package geometries;

import java.util.List;
import primitives.*;


/**
 * Cylinder class represents a Cylinder in 3D Cartesian coordinate system
 * 
 * @author Li Edri
 * @author Adi Giladi
 */
public class Cylinder extends Tube{

	protected double _height;
	
	
	/**
	 * Basic Constructor
	 * 
	 * @param ray - the cylinder
	 * @param h - height
	 * @param r - radius
	 */
	public Cylinder(Ray ray, double h, double r) {
		this(new Color(java.awt.Color.BLACK), ray, h, r,new Material(0,0,0));
	}
	
	
	/**
	 * Constructor that get in addition color
	 * 
	 * @param c - color
	 * @param ray - the cylinder
	 * @param h - height
	 * @param r - radius
	 */
	public Cylinder(Color c, Ray ray, double h, double r) {
		this(c, ray, h, r, new Material(0,0,0));
	}
	
	
	/**
	 * Constructor that get in addition color and material
	 * 
	 * @param c - color
	 * @param ray - the cylinder
	 * @param h - height
	 * @param r - radius
	 * @param material of the geometry
	 */
	public Cylinder(Color c, Ray ray, double h, double r, Material material) {
		super(c, ray, r, material);
		_height = h;
	}
	
	
	/**
	 * getter to the height
	 * @return double height
	 */
	public double get_height() {
		return _height;
	}


	@Override
	public String toString() {
		return "Cylinder = " + _height + ", " + get_radius();
	}
	
	/**
	 * this function return the normal of the geometry - bonus
	 * @param p - to find the normal
	 * @return vector
	 */
	public Vector getNormal(Point3D p) {
		return null;
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
