package geometries;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

/**
 * Triangle class represents a Triangle in 3D Cartesian coordinate system
 * @author Li Edri
 * @author Adi Giladi
 */
public class Triangle extends Polygon{

	/**
	 * Constructor that get 3 points to the triangle
	 * 
	 * @param p1 - one of the points that define the triangle
	 * @param p2 - one of the points that define the triangle
	 * @param p3 - one of the points that define the triangle
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		this(new Color(java.awt.Color.BLACK), p1, p2, p3);
	}	
	
	
	/**
	 * Constructor that get 3 points to the triangle and it color
	 * 
	 * @param c - color
	 * @param p1 - one of the points that define the triangle
	 * @param p2 - one of the points that define the triangle
	 * @param p3 - one of the points that define the triangle
	 */
	public Triangle(Color c, Point3D p1, Point3D p2, Point3D p3) {
		this(c, p1, p2, p3, new Material(0,0,0));
	}	

	
	/**
	 * Constructor that get 3 points to the triangle and it color and material
	 * 
	 * @param p1 - one of the points that define the triangle
	 * @param p2 - one of the points that define the triangle
	 * @param p3 - one of the points that define the triangle
	 * @param c - the color of the triangle
	 * @param material - the material of the triangle
	 */
	public Triangle(Color c, Point3D p1, Point3D p2, Point3D p3, Material material) {
		super(c,  material, p1, p2, p3);
	}
	
	/**
	 * this function find the intersections points with the geometry
	 * @param ray - to find the intersection with her
	 * @return list of Point3D
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray){
		List <GeoPoint> lst = new ArrayList<GeoPoint>();
		lst = super.get_plane().findIntersections(ray);
		if (lst == null)
			return null;
		
		Point3D p1= super.get_vertices(0);
		Point3D p2= super.get_vertices(1);
		Point3D p3= super.get_vertices(2);
		
		if (lst.get(0)._point.equals(p1) || lst.get(0)._point.equals(p2) || lst.get(0)._point.equals(p3))
			return null;
			
		Vector edge1 = p1.subtract(p2);
		Vector edge2 = p2.subtract(p3);
		Vector edge3 = p3.subtract(p1);
		
		if (lst.get(0)._point.subtract(p2).equals(edge1) || lst.get(0)._point.subtract(p3).equals(edge2) || lst.get(0)._point.subtract(p1).equals(edge3))
			return null;
		
		Vector v1 = ray.get_point().subtract(p1);
		Vector v2 = ray.get_point().subtract(p2);
		Vector v3 = ray.get_point().subtract(p3);
		
		Vector n1 = (v1.crossProduct(v2).normalize());
		Vector n2 = (v2.crossProduct(v3).normalize());
		Vector n3 = (v3.crossProduct(v1).normalize());
		
		double d1 = n1.dotProduct(ray.get_vector());
		if (Util.isZero(d1)) return null;
		double d2 = n2.dotProduct(ray.get_vector());
		if (Util.isZero(d2)) return null;
		double d3 = n3.dotProduct(ray.get_vector());
		if (Util.isZero(d3)) return null;

		if ((d1>0 && d2>0 && d3>0) || (d1<0 && d2<0 && d3<0))
		{
			List<GeoPoint> geoPoint = new ArrayList<GeoPoint>();
			geoPoint.add(new GeoPoint(this, lst.get(0)._point));
			return geoPoint;
		}
		else
			return null;
	}
}
