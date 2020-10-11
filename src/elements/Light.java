package elements;
import primitives.Color;

/**
 * this abstract class is the superclass to all the kind of class 
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public abstract class Light {

	protected Color _intensity;
	
	/**
	 * Constructor
	 * 
	 * @param intensity - color
	 */
	public Light(Color intensity){
		_intensity = intensity;
	}
	
	
	/**
	 * getter to the intensity
	 * 
	 * @return color
	 */
	public Color get_intensity() {
		return new Color(_intensity);
	}	
	
}
