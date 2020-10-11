package geometries;
import primitives.*;

/**
 * this abstract class is the superclass of all of the geometries -
 * and contain the color and the kind of the material of the geometry
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public abstract class Geometry implements Intersectable {

	protected Color _emmission;
	protected Material _material; 
	
	/**
	 * default Constructor - Initializing the parameters to the default
	 */
	public Geometry(){
		_emmission = new Color(java.awt.Color.BLACK);
		_material = new Material(0, 0, 0);
	}

	/**
	 * Constructor
	 * 
	 * @param color - init the geometry's color
	 */
	public Geometry(Color color){
		_emmission = new Color(color);
		_material = new Material(0, 0, 0);
	}
	
	/**
	 * Constructor
	 * 
	 * @param color - init the geometry's color
	 * @param material - init the geometry's material
	 */
	public Geometry(Color color, Material material){
		_emmission = new Color(color);
		_material = material;
	}
	
	/**
	 * getter to the material
	 * @return material - of the geometry
	 */
	public Material get_material() {
		return _material;
	}

	/**
	 * this function is abstract and all of the geometries have to do it- 
	 * to have the ability to calculate the normal of the geometry in specific point.
	 * @param p - point
	 * @return normal vector
	 */
	public abstract Vector getNormal(Point3D p);
	
	
	/**
	 * getter to the emmission
	 * @return emmission
	 */
	public Color get_emmission() {
		return _emmission;
	}
}
