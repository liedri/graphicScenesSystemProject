package elements;


import java.util.ArrayList;

import primitives.*;


/**
 * this class has the details of the camera -
 * location, direction and function that creating rays from the camera.
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class Camera {

	private Point3D _p0;
	private Vector _Vup;
	private Vector _Vto;
	private Vector _Vright;
	private static int numOfRays = 64;
	
	
	/**
	 * Constructor
	 * 
	 * @param p0 - location
	 * @param Vto - vector for direction
	 * @param Vup - vector for direction
	 */
	public Camera(Point3D p0, Vector Vto, Vector Vup)
	{
		if(Vto.dotProduct(Vup)!=0)
			throw new IllegalArgumentException("the vectors are not orthogonal");
		_Vright = Vto.crossProduct(Vup).normalized();
		_Vup = Vup.normalized();
		_Vto = Vto.normalized();
		_p0 = p0;
	}
	
	
	/**
	 * getter for p0
	 * @return p0 - Point3D
	 */
	public Point3D getP0() {
		return _p0;
	}
	
	
	/**
	 * getter for Vup
	 * @return Vup - Vector
	 */
	public Vector getVup() {
		return _Vup;
	}
	
	
	/**
	 * getter for Vto
	 * @return Vto - Vector
	 */
	public Vector getVto() {
		return _Vto;
	}
	
	
	/**
	 * getter for Vright
	 * @return Vright - Vector
	 */
	public Vector getVright() {
		return _Vright;
	}
	
	
	/**
	 * this function create a ray that start in the camera according to current pixel 
	 * 
	 * @param nX - the number of pixels in the width of the view plane
	 * @param nY- the number of pixels in the Height of the view plane
	 * @param j - index of column
	 * @param i - index of row
	 * @param screenDistance - he distance between the camera and the view plane
	 * @param screenWidth - the width of the view plane
	 * @param screenHeight - the width of the view plane
	 * @return - the ray from the current pixel
	 */
	public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight)
	{
		Point3D Pc = getP0().add(getVto().scale(screenDistance));
		
		double Rx = screenWidth/(double)nX;
		double Ry = screenHeight/(double)nY;
		
		double yi = (i-((nY)/(double)2))*Ry + (Ry/(double)2);
		double xj = (j-((nX)/(double)2))*Rx + (Rx/(double)2);
		Point3D Pij;
		if(yi == 0 && xj != 0)
			Pij = Pc.add(getVright().scale(xj));
		else if(xj == 0 && yi != 0)
			Pij = Pc.add(getVup().scale(-1*yi));
		else if(xj == 0 && yi == 0)
			Pij = new Point3D(Pc);
		else
			Pij = Pc.add(getVup().scale(yi).subtract(getVright().scale(xj)));
		
		Vector Vij = getP0().subtract(Pij);
		
		return new Ray(getP0(), Vij);
	}
	
	
	/**
	 * this function is constructRayThroughPixel with implement 'super sampling'   
	 * @param nX - the number of pixels in the width of the view plane
	 * @param nY- the number of pixels in the Height of the view plane
	 * @param j - index of column
	 * @param i - index of row
	 * @param screenDistance - he distance between the camera and the view plane
	 * @param screenWidth - the width of the view plane
	 * @param screenHeight - the width of the view plane
	 * @return list of rays
	 */
	public ArrayList<Ray> constructRaysThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight)
	{
		Point3D Pc = getP0().add(getVto().scale(screenDistance));
		
		double Rx = screenWidth/(double)nX;
		double Ry = screenHeight/(double)nY;
		
		double yi = (i-((nY)/(double)2))*Ry + (Ry/(double)2);
		double xj = (j-((nX)/(double)2))*Rx + (Rx/(double)2);
		
		ArrayList<Ray> RaysList = new ArrayList<Ray>();
		
		Point3D Pij;
		if(yi == 0 && xj != 0)
			Pij = Pc.add(getVright().scale(xj));
		else if(xj == 0 && yi != 0)
			Pij = Pc.add(getVup().scale(-1*yi));
		else if(xj == 0 && yi == 0)
			Pij = new Point3D(Pc);
		else
			Pij = Pc.add(getVup().scale(yi).subtract(getVright().scale(xj)));
		
		Vector Vij = getP0().subtract(Pij);
		RaysList.add(new Ray(getP0(), Vij));
		if(numOfRays==1) // in case of there is not super sampling
			return RaysList;
		//בהנחה שתמיד הפיקסל הוא ריבוע - אחרת זו לא בחירה חכמה של פיקסלים והתמונה
		//לא תצא טוב בכל מקרה (ולא רק מרובע)
		int numRowsAndColumns = (int)Math.floor(Math.sqrt(numOfRays)); 
		double ProgressX = Rx/(double)numRowsAndColumns;
		double ProgressY = Ry/(double)numRowsAndColumns;
		Pij = new Point3D(Pij.add(getVup().scale(-1 * (Rx/2.0)).add(getVright().scale(-1 * (Rx/2.0)))));
		for(int row = 0; row < numRowsAndColumns; row++) // these loops create n rays equally on the square
		{
			for(int column = 0; column < numRowsAndColumns; column++)
			{
				Vij = getP0().subtract(Pij);
				RaysList.add(new Ray(getP0(), Vij));
				
				Pij = Pij.add(getVright().scale(ProgressX));
			}
			Pij = Pij.add(getVup().scale(ProgressY)).add(getVright().scale(-1*Rx));
		}
		
		return RaysList;
	}
	
	
	
	/**
	 * refactoring - this function construct 4 Rays Through the edges of the Pixel, for adaptive super sampling.
	 * 
	 * @param nX - the number of pixels in the width of the view plane
	 * @param nY- the number of pixels in the Height of the view plane
	 * @param j - index of column
	 * @param i - index of row
	 * @param screenDistance - he distance between the camera and the view plane
	 * @param screenWidth - the width of the view plane
	 * @param screenHeight - the width of the view plane
	 * @param dx - tell us where is the x start in the pixel
	 * @param dy - tell us where is the y start in the pixel
	 * @return list of 4 rays - the edges of the square
	 */
	public ArrayList<Ray> constructRaysThroughEdgesPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight, double dx, double dy)
	{
		Point3D Pc = getP0().add(getVto().scale(screenDistance));
		
		double Rx = screenWidth/(double)nX;// הרוחב של הפיקסל
		double Ry = screenHeight/(double)nY;// האורך של הפיקסל
		
		double yi = (i-((nY)/(double)2))*Ry + (Ry/(double)2) + dy*Ry;// מרכז הפיקסל
		double xj = (j-((nX)/(double)2))*Rx + (Rx/(double)2) + dx*Rx;// כנ"ל
		
		
		Rx = Rx - Rx*dx;
		Ry = Ry - Ry*dy;
		
		ArrayList<Ray> RaysList = new ArrayList<Ray>();
		
		Point3D Pij;// הנקודה שרוצים להחזיר
		if(yi == 0 && xj != 0)
			Pij = Pc.add(getVright().scale(xj));
		else if(xj == 0 && yi != 0)
			Pij = Pc.add(getVup().scale(-1*yi));
		else if(xj == 0 && yi == 0)
			Pij = new Point3D(Pc);
		else
			Pij = Pc.add(getVup().scale(yi).subtract(getVright().scale(xj)));
		
		Vector Vij;
		
		Point3D Pij1 = Pij.add(getVup().scale(-1 * (Rx/2.0)).add(getVright().scale(-1 * (Rx/2.0))));// הנקודה בקצה הפיקסל- השמאלי העליון
		Vij = getP0().subtract(Pij1);
		RaysList.add(new Ray(getP0(), Vij));
		
		Point3D Pij2 = Pij1.add(getVright().scale(Rx));// הנקודה בקצה הימני העליון
		Vij = getP0().subtract(Pij2);
		RaysList.add(new Ray(getP0(), Vij));
		
		Point3D Pij3 = Pij1.add(getVup().scale(Ry));// הנקודה בקצה השמאלי התחתון
		Vij = getP0().subtract(Pij3);
		RaysList.add(new Ray(getP0(), Vij));
		
		Point3D Pij4 = Pij3.add(getVright().scale(Rx));// הנקודה בקצה הימני התחתון
		Vij = getP0().subtract(Pij4);
		RaysList.add(new Ray(getP0(), Vij));
		
		
		return RaysList;
	}
	
	
	/**
	 * refactoring - this function construct 5 Rays Through the centers of the Pixel, for adaptive super sampling.
	 * 
	 * @param nX - the number of pixels in the width of the view plane
	 * @param nY- the number of pixels in the Height of the view plane
	 * @param j - index of column
	 * @param i - index of row
	 * @param screenDistance - he distance between the camera and the view plane
	 * @param screenWidth - the width of the view plane
	 * @param screenHeight - the width of the view plane
	 * @param dx - tell us where is the x start in the pixel
	 * @param dy - tell us where is the y start in the pixel
	 * @param a - how much to take from the pixel
	 * @return list of 5 rays center, up, down, right, left
	 */
	public ArrayList<Ray> constructRaysThroughCentersPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight, double dx, double dy, double a)
	{
		Point3D Pc = getP0().add(getVto().scale(screenDistance));
		
		double Rx = screenWidth/(double)nX;// הרוחב של הפיקסל
		double Ry = screenHeight/(double)nY;// האורך של הפיקסל
		
		double yi = (i-((nY)/(double)2))*Ry + dy*Ry;// מרכז הפיקסל\הריבוע
		double xj = (j-((nX)/(double)2))*Rx + dx*Rx;// כנ"ל
		
		
		Rx = Rx*a;
		Ry = Ry*a;
		
		ArrayList<Ray> RaysList = new ArrayList<Ray>();
		
		Point3D Pij;// הנקודה שרוצים להחזיר
		if(yi == 0 && xj != 0)
			Pij = Pc.add(getVright().scale(xj));
		else if(xj == 0 && yi != 0)
			Pij = Pc.add(getVup().scale(-1*yi));
		else if(xj == 0 && yi == 0)
			Pij = new Point3D(Pc);
		else
			Pij = Pc.add(getVup().scale(yi).subtract(getVright().scale(xj)));
		
		Vector Vij = getP0().subtract(Pij);
		RaysList.add(new Ray(Pij,Vij));
		
		Point3D Pij1 = Pij.add(getVup().scale(-1 * (Ry/2.0)));// הנקודה המרכזית למעלה
		Vij = getP0().subtract(Pij1);
		RaysList.add(new Ray(getP0(), Vij));
		
		Point3D Pij2 = Pij.add(getVup().scale(Ry/2.0));// הנקודה המרכזית למטה
		Vij = getP0().subtract(Pij2);
		RaysList.add(new Ray(getP0(), Vij));
		
		Point3D Pij3 = Pij.add(getVright().scale(Rx/2.0));// הנקודה הימנית במרכז
		Vij = getP0().subtract(Pij3);
		RaysList.add(new Ray(getP0(), Vij));
		
		Point3D Pij4 = Pij.add(getVright().scale(-1*Rx/2.0));// הנקודה השמאלית במרכז
		Vij = getP0().subtract(Pij4);
		RaysList.add(new Ray(getP0(), Vij));
		
		return RaysList;
	}
	
	
}
