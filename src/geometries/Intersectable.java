package geometries;

import primitives.*;
import java.util.List;

/**
 * this interface is for all of the geometries who can find intersections points with ray
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public interface Intersectable {
	
	/**
	 * this function is the main function that all of the intersectable geometries realize it.
	 * @param ray - to find the intersection with her
	 * @return list of intersection geoPoints
	 */
	List<GeoPoint> findIntersections(Ray ray);

	/**
	 * inner static class - geoPoint is couple of point and it geometries.
	 * @author Adi Giladi and Li Edri
	 *
	 */
	public static class GeoPoint {
	    public Geometry _geometry;
	    public Point3D _point;
	    
	    public GeoPoint(Geometry geometry, Point3D point){
	    	_geometry = geometry;
	    	_point = point;
	    }
	    
	    
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (_geometry == null) {
				if (other._geometry != null)
					return false;}
			if (_point == null) {
				if (other._point != null)
					return false;} 
			else if (!_point.equals(other._point))
				return false;
			return true;
		}	    
	}
}
