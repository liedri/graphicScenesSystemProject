package testing;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.Cylinder;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import geometries.Tube;
import primitives.*;
import geometries.Intersectable.GeoPoint;

/**
 * Testing UtitTsest Class
 * @author Dan
 *
 */

public class Unittest{
	
	
	@Test
	public void Test()
	{
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
		
		
		//TC1: Sphere Test (2 Points)
		Sphere sphere = new Sphere(new Point3D(0,0,3),1d);
		List<GeoPoint> lst = new ArrayList<GeoPoint>();
		for(int i=0; i<3;i++)
			for(int j=0;j<3;j++)
			{
				List<GeoPoint> tmp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(tmp != null)
					lst.addAll((Collection<? extends GeoPoint>) tmp);
			}
		assertEquals("Wrong number of Points", 2, lst.size());
		
		
		
		//TC2: Sphere Test (18 Points)
				sphere = new Sphere(new Point3D(0,0,2.5),2.5);
				camera = new Camera(new Point3D (0,0,-0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
				lst = new ArrayList<GeoPoint>();
				for(int i=0; i<3;i++)
					for(int j=0;j<3;j++)
					{
						List<GeoPoint> tmp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(tmp != null)
							lst.addAll((Collection<? extends GeoPoint>) tmp);
					}
				assertEquals("Wrong number of Points", 18, lst.size());
        
				//TC4: Sphere Test (9 Points)
				sphere = new Sphere(new Point3D(0,0,2d),4d);
				lst = new ArrayList<GeoPoint>();
				for(int i=0; i<3;i++)
					for(int j=0;j<3;j++)
					{
						List<GeoPoint> tmp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(tmp != null)
							lst.addAll((Collection<? extends GeoPoint>) tmp);
					}
				assertEquals("Wrong number of Points", 9, lst.size());
        
				//TC5: Sphere Test (0 Points)
				sphere = new Sphere(new Point3D(0,0,-1d),0.5);
				lst = new ArrayList<GeoPoint>();
				for(int i=0; i<3;i++)
					for(int j=0;j<3;j++)
					{
						List<GeoPoint> tmp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(tmp != null)
							lst.addAll((Collection<? extends GeoPoint>) tmp);
					}
				assertEquals("Wrong number of Points", new ArrayList<Point3D>(), lst);
        
				
				//TC6: Sphere Test (2 Points)
				sphere = new Sphere(new Point3D(0,0,-0.5d),0.25);
				lst = new ArrayList<GeoPoint>();
				for(int i=0; i<3;i++)
					for(int j=0;j<3;j++)
					{
						List<GeoPoint> tmp = sphere.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(tmp != null)
							lst.addAll((Collection<? extends GeoPoint>) tmp);
					}
				assertEquals("Wrong number of Points", 9, lst.size());
				
        
				//TC7: Plane Test (9 Points)
				Plane plane = new Plane(new Point3D(1,0,2d), new Point3D(0,1,2d), new Point3D(1,1,2d));
				lst = new ArrayList<GeoPoint>();
				for(int i=0; i<3;i++)
					for(int j=0;j<3;j++)
					{
						List<GeoPoint> tmp = plane.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(tmp != null)
							lst.addAll((Collection<? extends GeoPoint>) tmp);
					}
				assertEquals("Wrong number of Points", 9, lst.size());
				
				
				//TC8: Plane Test (9 Points)
				plane = new Plane(new Point3D(0,-1.5,0.5), new Point3D(0,1d,2d), new Point3D(3d,2d,3d));
				lst = new ArrayList<GeoPoint>();
				for(int i=0; i<3;i++)
					for(int j=0;j<3;j++)
					{
						List<GeoPoint> tmp = plane.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(tmp != null)
							lst.addAll((Collection<? extends GeoPoint>) tmp);
					}
				assertEquals("Wrong number of Points", 9, lst.size());
				
				//TC9: Plane Test (6 Points)
				plane = new Plane(new Point3D(0,-1.5,0.5), new Point3D(0,-1d,2d), new Point3D(3d,2d,3d));
				lst = new ArrayList<GeoPoint>();
				for(int i=0; i<3;i++)
					for(int j=0;j<3;j++)
					{
						List<GeoPoint> tmp = plane.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(tmp != null)
							lst.addAll((Collection<? extends GeoPoint>) tmp);
					}
				assertEquals("Wrong number of Points", 6, lst.size());
				
				//TC10: Triangle Test (1 Points)
				Triangle tr = new Triangle(new Point3D(0d,-1d,2d), new Point3D(1d,1d,2d), new Point3D(-1d,1d,2));
				lst = new ArrayList<GeoPoint>();
				for(int i=0; i<3;i++)
					for(int j=0;j<3;j++)
					{
						List<GeoPoint> tmp = tr.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(tmp != null)
							lst.addAll((Collection<? extends GeoPoint>) tmp);
					}
				assertEquals("Wrong number of Points", 1, lst.size());
				
				//TC11: Triangle Test (2 Points)
				tr = new Triangle(new Point3D(0d,-20d,2d), new Point3D(1d,1d,2d), new Point3D(-1d,1d,2));
				lst = new ArrayList<GeoPoint>();
				for(int i=0; i<3;i++)
					for(int j=0;j<3;j++)
					{
						List<GeoPoint> tmp = tr.findIntersections(camera.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(tmp != null)
							lst.addAll((Collection<? extends GeoPoint>) tmp);
					}
				assertEquals("Wrong number of Points", 2, lst.size());
	}
	
}