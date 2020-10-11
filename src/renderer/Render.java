package renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import elements.*;

/**
 * this class is the Graphic engine of the project and connect between
 * all of the classes in the project to create the picture
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */


public class Render {
	
	protected ImageWriter _imageWriter;
	protected Scene _scene;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double a_deep_ASS = 1d/8d;
	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;

	
	/**
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}

		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}
		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
		      ++col;
		      ++_counter;
		      if (col < _maxCols) {
		        target.row = this.row;
		        target.col = this.col;
		        if (_print && _counter == _nextCounter) {
		          ++_percents;
		          _nextCounter = _pixels * (_percents + 1) / 100;
		          return _percents;
		        }
		        return 0;
		      }
		      ++row;
		      if (row < _maxRows) {
		        col = 0;
		        target.row = this.row;
		        target.col = this.col;
		        if (_print && _counter == _nextCounter) {
		          ++_percents;
		          _nextCounter = _pixels * (_percents + 1) / 100;
		          return _percents;
		        }
		        return 0;
		      }
		      return -1;
		    }

		
		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}

	
	
	/**
	 * constructor
	 * 
	 * @param imageWriter
	 * @param scene
	 */
	public Render(ImageWriter imageWriter, Scene scene){
		_imageWriter = imageWriter;
		_scene = scene;
	}
	
	
	/**
	 * getter to the imageWriter
	 * @return imageWriter
	 */
	public ImageWriter get_imageWriter() {
		return _imageWriter;
	}

	
	/**
	 * getter to the scene
	 * @return scene
	 */
	public Scene get_scene() {
		return _scene;
	}

	
	/**
	 * this function reference to the function renderImage according to your choice - 
	 * @param choice - int value
	 * 0 : renderImage_old
	 * 1 : renderImageSS
	 * 2 : renderImageASS - default parameter
	 * @param choice
	 */
	public void renderImage(int choice){
		if(choice==0)
			renderImage_old();
		if(choice==1)
			renderImageSS();
		if(choice==2)
			renderImageASS();
	}
	
	
	/**
	 * default parameter for: renderImage(int choice)
	 */
	public void renderImage(){
		renderImage(2);
	}
	
	
	/**
	 * this function calculate the to every pixel its color (the main function of the render)
	 * 
	 * with super sampling
	 */
	public void renderImage_old(){
		
		ArrayList<Ray> rays = null;
		GeoPoint closestPoint = null;
		java.awt.Color c;
		double r = 0;
		double g = 0;
		double b = 0;
		
		for (int i = 0; i< _imageWriter.getNx(); i++) // the loop for the rows in the view plane
		{
			for (int j = 0; j < _imageWriter.getNy(); j++) // the loop for the columns in the view plane
			{
				rays = _scene.get_camera().constructRaysThroughPixel(_imageWriter.getNx(), //
						_imageWriter.getNy(), i, j, _scene.get_distance(), _imageWriter.getWidth(), //
						_imageWriter.getHeight());
				
				for(Ray ray : rays) // for the rays of the pixel i,j - this for loop do the sum of the colors
				{
					closestPoint = findCLosestIntersection(ray);
					if(closestPoint == null)
					{
						c = _scene.get_background().getColor(); 
						r += c.getRed();
						g += c.getGreen();
						b += c.getBlue();
					}
					else
					{
						c = calcColor(closestPoint, ray).getColor();
						r += c.getRed();
						g += c.getGreen();
						b += c.getBlue();
					}
				}
				double n = rays.size();
				
				_imageWriter.writePixel(i,j, new Color(r/n, g/n, b/n).getColor()); // write the average of the sum
				r = 0;
				g = 0;
				b = 0;
			}
		}
	}

	
	/**
	 * this function calculate the color in specific point according to the geometry and the light
	 * @param g - the closest GeoPoint
	 * @return color
	 */
	private Color calcColor(GeoPoint g){
		Color color = _scene.get_ambientLight().get_intensity();
		color = color.add(g._geometry.get_emmission());
		
		Vector v = _scene.get_camera().getP0().subtract(g._point).normalize();
		Vector n = g._geometry.getNormal(g._point);
		Material material = g._geometry.get_material();
		int nShininess = material.get_nShininess();
		double kd = material.get_kD();
		double ks = material.get_kS();
		
		for(LightSource lightSource : _scene.get_lights()){
			Vector l = lightSource.getL(g._point);
			double n_l = Util.alignZero(n.dotProduct(l));
			double n_v = Util.alignZero(n.dotProduct(v));
			if(Math.signum(n_l) == Math.signum(n_v))
			{
				if (unshaded(lightSource, l, n, g)) {
				Color lightIntensity = lightSource.getIntensity(g._point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
						calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		return color;
	}
	
	
	/**
	 * this function calculate the color in specific point according to the geometry and the light, and mirror and transparency
	 * @param gp - the closest geoPoint
	 * @param ray - the ray that hit the geoPoint
	 * @return the color at this point
	 */
	private Color calcColor(GeoPoint gp, Ray ray){
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.get_ambientLight().get_intensity());
	}
	
	
	/**
	 * this function calculate the color in recursion (for the reflection and refraction)
	 * 
	 * @param geopoint - the closest geoPoint
	 * @param inRay - the ray that hit the geoPoint
	 * @param level - int, the level of the recursion
	 * @param k - how much to take the calculated color
	 * @return the color at this point
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {
		if (level == 1) return Color.BLACK;
		
		//order the constants
		Color color = geopoint._geometry.get_emmission();
		Vector v = _scene.get_camera().getP0().subtract(geopoint._point).normalize();
		Vector n = geopoint._geometry.getNormal(geopoint._point);
		Material material = geopoint._geometry.get_material();
		int nShininess = material.get_nShininess();
		double kd = material.get_kD();
		double ks = material.get_kS();
		
		for(LightSource lightSource : _scene.get_lights()){ // add the color of the lights
			Vector l = lightSource.getL(geopoint._point);
			double n_l = Util.alignZero(n.dotProduct(l));
			double n_v = Util.alignZero(n.dotProduct(v));
			if(Math.signum(n_l) == Math.signum(n_v))
			{
				double ktr = transparency(lightSource, l, n, geopoint);
				if (ktr * k > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(geopoint._point).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		
		double kr = geopoint._geometry.get_material().get_kR();
		double kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) { // add the color of the reflection
			Ray reflectedRay = reflectedRay(inRay.get_vector(), n, geopoint._point);
			GeoPoint reflectedPoint = findCLosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
		}
		
		double kt = geopoint._geometry.get_material().get_kT();
		double kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) { // add the color of the refraction 
			Ray refractedRay = refractedRay(inRay.get_vector(), geopoint._geometry.getNormal(geopoint._point), geopoint._point) ;
			GeoPoint refractedPoint = findCLosestIntersection(refractedRay);
			if (refractedPoint != null){
				color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt));
			}
		}
		return color;
}
	
	/**
	 * this function call to the function getClosestPoint with Point3D and send the p0 of the camera
	 * @param geos
	 * @return closestPoint - GeoPoint
	 */
	public GeoPoint getClosestPoint(List<GeoPoint> geos){
		return getClosestPoint(_scene.get_camera().getP0(), _scene.get_distance(), geos);
	}
	
	
	/**
	 * this function calculate which point is the closest to the pint and return it - refactoring
	 * @param geos
	 * @param point - Point3D
	 * @return closestPoint - GeoPoint
	 */
	public GeoPoint getClosestPoint(Point3D point, double min_dastance, List<GeoPoint> geos){
		double dis = Double.MAX_VALUE;
		GeoPoint closestPoint = null;
		double disPoint = 0;
		
		for(GeoPoint geo : geos) // check the intersection between the ray and the geometries
		{
			disPoint = point.distance(geo._point);
			if (disPoint < dis && disPoint > min_dastance)
			{
				closestPoint = geo;
				dis = disPoint;
			}
		}
		return closestPoint;
	}
	
	/**
	 * this function print grid to the image
	 * @param interval
	 * @param color
	 */
	public void printGrid(int interval, java.awt.Color color){
		for (int i = 0; i < _imageWriter.getNx(); i++)
		{
			for (int j = 0; j < _imageWriter.getNy(); j++)
			{
				if (i % interval == 0 || j % interval == 0)
					_imageWriter.writePixel(i,  j, color);
			}
		}
	}
	
	
	/**
	 * this function calculate the diffuse light in the point.
	 * @param kd - Constants to the diffuse
	 * @param l - the light direction vector of the hit in the point
	 * @param n - the normal
	 * @param lightIntensity
	 * @return color
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity){
		double l_n = l.dotProduct(n);
		if (l_n < 0)
			l_n = l_n * (-1);
		return lightIntensity.scale(kd * l_n);
	}
	
	
	/**
	 * this function calculate the specular light in the point.
	 * @param ks - Constants to the specular light
	 * @param l - the light direction vector of the hit in the point
	 * @param n - n - the normal
	 * @param v - the vector of the return light from the geometry at this point.
	 * @param nShininess
	 * @param lightIntensity
	 * @return color
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity){
		Vector r = n.scale(l.dotProduct(n)).scale(2).subtract(l);
		double num = v.scale(-1).dotProduct(r);
		if (num > 0)
			return lightIntensity.scale(Math.pow(num, nShininess) * ks);
		else
			return lightIntensity.scale(0);
	}
	
	
	/**
	 * this function return true if the point is unshaded, else return false
	 * 
	 * @param light
	 * @param l
	 * @param n
	 * @param gp- the geometry that checked
	 * @return true or false- the geometry is unshaded or not
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp){
//			unshaded 1 - before refactoring:
//
//			Vector lightDirection = l.scale(-1); // from point to light source
//			Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
//			Point3D point = gp._point.add(delta);
//			Ray lightRay = new Ray(point, lightDirection);
//			List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
//			return intersections == null;
//			}
	
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(gp._point, lightDirection, n);

		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
		if (intersections==null) return true;
		double lightDistance = light.getDistance(gp._point);
		for (GeoPoint geoP : intersections) {
			if (Util.alignZero(geoP._point.distance(gp._point) - lightDistance) <= 0 && gp._geometry.get_material().get_kT()==0)
					return false;
		}
		return true;
	}
	
	/**
	 * this function return double between 0-1 that present how much the point is shaded
	 * @param ls
	 * @param l
	 * @param n
	 * @param gp
	 * @return double
	 */
	private double transparency(LightSource ls, Vector l, Vector n, GeoPoint gp){
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(gp._point, lightDirection, n);

		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
		if (intersections==null) return 1.0;
		
		double lightDistance = ls.getDistance(gp._point);
		double ktr=1.0;
		for (GeoPoint geoP : intersections) {
			if (Util.alignZero(geoP._point.distance(gp._point) - lightDistance) <= 0){
				ktr *= geoP._geometry.get_material().get_kT();
				if(ktr < MIN_CALC_COLOR_K) return 0.0;
			}
		}
		return ktr;
	}
	
	
	/**
	 * this function return the reflected ray
	 * @param l
	 * @param n
	 * @param p
	 * @return ray- the reflected ray
	 */
	public Ray reflectedRay(Vector l, Vector n, Point3D p){
		double ln = l.dotProduct(n);
		if(ln==0) return null;
		return new Ray(p, n.scale(ln*2).subtract(l), n);
	}
	
	
	/**
	 * this function return the refracted ray
	 * @param l
	 * @param n
	 * @param p
	 * @return ray- the refracted ray
	 */
	public Ray refractedRay(Vector l, Vector n, Point3D p){
		
		return new Ray(p, l, n);
	}
	
	/**
	 * this function find CLosest Intersection with the point of the ray and the geometries of the scene.
	 * @param ray
	 * @return closestPoint - GeoPint
	 */
	private GeoPoint findCLosestIntersection(Ray ray){
		List<GeoPoint> intresectionPoint = new ArrayList<GeoPoint>();
		intresectionPoint = _scene.get_geometries().findIntersections(ray);
		GeoPoint closestPoint = null;
		if(intresectionPoint != null)
			closestPoint = getClosestPoint(ray.get_point(), 0, intresectionPoint);
		return closestPoint;
		
	}
	
	

	/**
	 * This function renders image's pixel color map from the scene included with
	 * the Renderer object - refactoring - threading
	 */
	public void renderImageSS() {
		
		final int nX = _imageWriter.getNx();
		final int nY = _imageWriter.getNy();
		final double dist = _scene.get_distance();
		final double width = _imageWriter.getWidth();
		final double height = _imageWriter.getHeight();
		final Camera camera = _scene.get_camera();

		final Pixel thePixel = new Pixel(nY, nX);

		// Generate threads
		Thread[] threads = new Thread[_threads];
		for (int i = _threads - 1; i >= 0; --i) { // this loop create the threads
			threads[i] = new Thread(() -> {
				GeoPoint closestPoint = null;
				java.awt.Color c;
				double r = 0;
				double g = 0;
				double b = 0;
				Pixel pixel = new Pixel();
				
				while (thePixel.nextPixel(pixel)) {
					ArrayList<Ray> rays = camera.constructRaysThroughPixel(nX, nY, pixel.col, pixel.row,dist, width, height);
					
					for(Ray ray : rays) // for the rays of the pixel i,j - this for loop do the sum of the colors
					{
						closestPoint = findCLosestIntersection(ray);
						if(closestPoint == null)
						{
							c = _scene.get_background().getColor(); 
							r += c.getRed();
							g += c.getGreen();
							b += c.getBlue();
						}
						else
						{
							c = calcColor(closestPoint, ray).getColor();
							r += c.getRed();
							g += c.getGreen();
							b += c.getBlue();
						}
					}
					double n = rays.size();
					_imageWriter.writePixel(pixel.col, pixel.row, new Color(r/n, g/n, b/n).getColor()); // the average of the color
					r = 0;
					g = 0;
					b = 0;
				}
			});
		}

		// Start threads
		for (Thread thread : threads) thread.start();

		// Wait for all threads to finish
		for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
		if (_print) System.out.printf("\r100%%\n");
	}
	
	
	
	/**
	 * This function renders image's pixel color map from the scene included with
	 * the Renderer object.
	 * refactoring - Adaptive super sampling and threading.
	 */
	public void renderImageASS() {
		
		final int nX = _imageWriter.getNx();
		final int nY = _imageWriter.getNy();
		final double dist = _scene.get_distance();
		final double width = _imageWriter.getWidth();
		final double height = _imageWriter.getHeight();
		final Camera camera = _scene.get_camera();
		final Pixel thePixel = new Pixel(nY, nX);

		// Generate threads
		Thread[] threads = new Thread[_threads];
		for (int i = _threads - 1; i >= 0; --i) { // create the threads
			threads[i] = new Thread(() -> {
				
				Color c;
				ArrayList<Ray> rays;
				ArrayList<Color> colors = new ArrayList<Color>();
				GeoPoint closestPoint;
				Pixel pixel = new Pixel();
				
				while (thePixel.nextPixel(pixel)) { // calculate the color of every pixel
					
					rays = camera.constructRaysThroughEdgesPixel(nX, nY, pixel.col, pixel.row, dist, width, height, 0, 0); // array of 4 rays - The edges of the square
					
					for(Ray ray : rays) // to every ray - calculate the color
					{
						closestPoint = findCLosestIntersection(ray);
						if(closestPoint == null)
							colors.add(_scene.get_background());
						else
							colors.add(calcColor(closestPoint, ray));	
					}
					c = AdaptiveSuperSampling(colors,pixel, 1, 0, 0);
					_imageWriter.writePixel(pixel.col, pixel.row, c.getColor());
					colors.clear();
				}
			});
		}

		// Start threads
		for (Thread thread : threads) thread.start();

		// Wait for all threads to finish
		for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
		if (_print) System.out.printf("\r100%%\n");
	}
	
	
	

	/**
	 * Set multithreading <br> - if the parameter is 0 - number of coress less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}

	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		_print = true;
		return this;
	}
	
	
	/**
	 * this function calculate the color of the pixel according to the method adaptive super sampling.
	 * 
	 * @param colors
	 * @param pixel
	 * @param a
	 * @param dx
	 * @param dy
	 * @return - the averaged color
	 */
	public Color AdaptiveSuperSampling(ArrayList<Color> colors, Pixel pixel, double a, double dx, double dy){
		
		if(a<=a_deep_ASS){//במקרה שהגענו לגבול עומק הרקורסיה
			return Color.average(colors);
		}
		
		// in the case that the color of the edges of the square is equal
		if(Color.equals(colors)){
			return Color.average(colors);
		}
		
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		double dist = _scene.get_distance();
		double width = _imageWriter.getWidth();
		double height = _imageWriter.getHeight();
		Camera camera = _scene.get_camera();
		
		ArrayList<Color> newColors = new ArrayList<Color>();
		ArrayList<Ray> rays = camera.constructRaysThroughCentersPixel(nX, nY, pixel.col, pixel.row, dist, width, height, dx, dy, a/2); // array of 4 rays - The edges of the square
		GeoPoint closestPoint;
		
		for(Ray ray : rays) // to every ray - calculate the color
		{
			closestPoint = findCLosestIntersection(ray);
			if(closestPoint == null)
				newColors.add(_scene.get_background());
			else
				newColors.add(calcColor(closestPoint, ray));	
		}
		
		// calculate the color of the 4 square
				
		Color c1 = AdaptiveSuperSampling(new ArrayList<Color>(Arrays.asList(colors.get(0), newColors.get(1), // 0,0
				newColors.get(4), newColors.get(0))), pixel, a/2.0, dx,dy);  
		
		Color c2 = AdaptiveSuperSampling(new ArrayList<Color>(Arrays.asList(newColors.get(1), colors.get(1), // 0.5,0
				newColors.get(0), newColors.get(3))), pixel, a/2.0, dx + a/2.0,dy);
		
		Color c3 = AdaptiveSuperSampling(new ArrayList<Color>(Arrays.asList(newColors.get(4), newColors.get(0), // 0,0.5
				colors.get(2), newColors.get(2))), pixel, a/2.0, dx,dy +a/2.0);
		
		Color c4 = AdaptiveSuperSampling(new ArrayList<Color>(Arrays.asList(newColors.get(0), newColors.get(3), //0.5,0.5
				newColors.get(2), colors.get(3))), pixel, a/2.0, dx + a/2.0,dy + a/2.0);  
		
		return Color.average(4, c1, c2, c3, c4);// return the average of the colors
	}

	
}