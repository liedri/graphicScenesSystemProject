package geometries;
import primitives.*;

/**
 * this abstract class is the superclass of the geometries that contain radius like sphere or tube
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public abstract class RadialGeometry extends Geometry{
	
	protected double _radius;
	
	/**
	 * Constructor
	 * 
	 * @param num - radius
	 */
	public RadialGeometry(double num) {
		_radius = num;
	}
	
	/**
	 * Constructor
	 * 
	 * @param c - color
	 * @param num - radius
	 */
	public RadialGeometry(Color c, double num) {
		this(c, num, new Material(0,0,0));
	}

	
	/**
	 * Constructor
	 * 
	 * @param c - color
	 * @param num - radius
	 * @param material of the geometry
	 */
	public RadialGeometry(Color c, double num, Material material) {
		super(c, material);
		_radius = num;
	}
	
	
	/**
	 * copy Constructor
	 * @param r - radius
	 */
	public RadialGeometry(RadialGeometry r) {
		_radius = r.get_radius();
	}

	
	/**
	 * getter to the radius
	 * @return _radius - double
	 */
	public double get_radius() {
		return _radius;
	}
}