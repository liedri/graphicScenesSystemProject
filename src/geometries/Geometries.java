package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;


/**
 * this class is to manage a list of the intersectable
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class Geometries implements Intersectable{
	
	List<Intersectable> IntersectableGeometries;
	
	
	/**
	 * default Constructor
	 */
	public Geometries()
	{
		IntersectableGeometries = new ArrayList<Intersectable>();
	}
	
	
	/**
	 * Constructor
	 * 
	 * @param geometries - that can find intersection point
	 */
	public Geometries(Intersectable... geometries )
	{
		IntersectableGeometries = new ArrayList<Intersectable>();
		for(Intersectable g : geometries)
		{
			IntersectableGeometries.add(g);
		}
	}
	
	
	/**
	 * this function add geometries to the list
	 * 
	 * @param geometries - a few geometries
	 */
	public void add(Intersectable... geometries )
	{
		for(Intersectable g : geometries)
		{
			IntersectableGeometries.add(g);
		}
	}
	
	
	/**
	 * this function find Intersections with the ray and the geometries in the list
	 * 
	 * @param ray - to find the intersection with her
	 * @return intersections return the intersections points (and their geometry) 
	 */
	public List<GeoPoint> findIntersections(Ray ray){
		List<GeoPoint> intersections = new ArrayList<GeoPoint>();
		for(Intersectable g : IntersectableGeometries)
		{
			List<GeoPoint> tmp = g.findIntersections(ray);
			if(tmp != null)
				intersections.addAll(tmp);
		}	
		if(intersections.size() == 0)
			return null;
		return intersections;
	}
}
