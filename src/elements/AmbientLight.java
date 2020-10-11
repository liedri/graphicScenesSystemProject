package elements;
import primitives.Color;

/**
 * this class is ambient light - the natural light that exist even when there are no lighting sources
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class AmbientLight extends Light{

	
	/**
	 * Constructor
	 * 
	 * @param Ia - color
	 * @param Ka - Ka is betwwen 0-1
	 */
	public AmbientLight(Color Ia, double Ka)
	{
		super (Ia.scale(Ka));
	}
	
	
	/**
	 * this function is a superclass function. but in order to use it outside the class
	 * we wrote the same function here that calling to the superclass function 
	 * @return color
	 */
	public Color GetIntensity()
	{
		return super.get_intensity();
	}
}
