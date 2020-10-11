package primitives;

/**
 * the class material is for the material of the geometry (in light calculation)
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class Material {

	double _kD, _kS, _kT, _kR; //Constants to the diffuse and specular light
	int _nShininess;//Constant to how much the material is Shiny
	
	
	
	/**
	 * Constructor
	 * 
	 * @param kD - constant between 0 to 1 to diffuse light
	 * @param kS - constant between 0 to 1 to specular light
	 * @param n - how much the color is shiny
	 */
	public Material(double kD, double kS, int n){
		 this(kD, kS, 0, 0, n);
	}


	/**
	 * Constructor
	 * 
	 * @param kD - constant between 0 to 1 to diffuse light
	 * @param kS - constant between 0 to 1 to specular light
	 * @param kT - constant between 0 to 1 to tell how much the geometry is transparency
	 * @param kR - constant between 0 to 1 to tell how much the geometry is mirror
	 * @param n - how much the color is shiny
	 */
	public Material(double kD, double kS, double kT, double kR, int n){
		 _kD = kD; 
		 _kS = kS;
		 _kT = kT; 
		 _kR = kR;
		 _nShininess = n;
	}
	
	/**
	 * getter to kD
	 * @return kD - double between 0 to 1
	 */
	public double get_kD() {
		return _kD;
	}

	
	/**
	 * getter to kS
	 * @return kS - double between 0 to 1
	 */
	public double get_kS() {
		return _kS;
	}

	
	/**
	 * getter to nShininess
	 * @return nShininess - int
	 */
	public int get_nShininess() {
		return _nShininess;
	}
	
	
	/**
	 * getter to kT
	 * @return kT
	 */
	public double get_kT() {
		return _kT;
	}


	/**
	 * getter to kR
	 * @return kR
	 */
	public double get_kR() {
		return _kR;
	}


}
