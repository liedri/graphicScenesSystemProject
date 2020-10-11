package primitives;

import static primitives.Util.isZero;

import java.util.List;

import javax.swing.colorchooser.ColorSelectionModel;

/**
 * Wrapper class for java.jwt.Color The constructors operate with any
 * non-negative RGB values. The colors are maintained without upper limit of
 * 255. Some additional operations are added that are useful for manipulating
 * light's colors
 * 
 * @author Dan Zilberstein
 */
public class Color {
    /**
     * The internal fields tx`o maintain RGB components as double numbers from 0 to
     * whatever...
     */
    private double _r = 0.0, _g = 0.0, _b = 0.0;
    private static final double Min_Distance = 3.0;
    public final static Color BLACK = new Color();

    /**
     * Default constructor - to generate Black Color (privately)
     */
    private Color() {}

    /**
     * Constructor to generate a color according to RGB components Each component in
     * range 0..255 (for printed white color) or more [for lights]
     *
     * @param r Red component
     * @param g Green component
     * @param b Blue component
     */
    public Color(double r, double g, double b) {
        if (r < 0 || g < 0 || b < 0)
            throw new IllegalArgumentException("Negative color component is illegal");
        _r = r;
        _g = g;
        _b = b;
    }

    /**
     * Copy constructor for Color
     * 
     * @param other the source color
     */
    public Color(Color other) {
        _r = other._r;
        _g = other._g;
        _b = other._b;
    }

    /**
     * Constructor on base of java.awt.Color object
     * 
     * @param other java.awt.Color's source object
     */
    public Color(java.awt.Color other) {
        _r = other.getRed();
        _g = other.getGreen();
        _b = other.getBlue();
    }

    /**
     * Color setter to reset the color to BLACK
     * 
     * @return the Color object itself for chaining calls
     */
    public Color setColor() {
        _r = 0.0;
        _g = 0.0;
        _b = 0.0;
        return this;
    }

    /**
     * Color setter to generate a color according to RGB components Each component
     * in range 0..255 (for printed white color) or more [for lights]
     * 
     * @param r Red component
     * @param g Green component
     * @param b Blue component
     * @return the Color object itself for chaining calls
     */
    public Color setColor(double r, double g, double b) {
        if (r < 0 || g < 0 || b < 0)
            throw new IllegalArgumentException("Negative color component is illegal");
        _r = r;
        _g = g;
        _b = b;
        return this;
    }

    /**
     * Color setter to copy RGB components from another color
     *
     * @param other source Color object
     * @return the Color object itself for chaining calls
     */
    public Color setColor(Color other) {
        _r = other._r;
        _g = other._g;
        _b = other._b;
        return this;
    }

    /**
     * Color setter to take components from an base of java.awt.Color object
     *
     * @param other java.awt.Color's source object
     * @return the Color object itself for chaining calls
     */
    public Color setColor(java.awt.Color other) {
        _r = other.getRed();
        _g = other.getGreen();
        _b = other.getBlue();
        return this;
    }

    /**
     * Color getter - returns the color after converting it into java.awt.Color
     * object During the conversion any component bigger than 255 is set to 255
     *
     * @return java.awt.Color object based on this Color RGB components
     */
    public java.awt.Color getColor() {
        int r = (int)_r, g = (int)_g, b = (int)_b;
        return new java.awt.Color(r > 255 ? 255 : r, g > 255 ? 255 : g, b > 255 ? 255 : b);
    }

    /**
     * Operation of adding this and one or more other colors (by component)
     *
     * @param colors one or more other colors to add
     * @return new Color object which is a result of the operation
     */
    public Color add(Color... colors) {
        double r = _r, g = _g, b = _b;
        for (Color c : colors) {
            r += c._r;
            g += c._g;
            b += c._b;
        }
        return new Color(r, g, b);
    }

    /**
     * Scale the color by a scalar
     *
     * @param k scale factor
     * @return new Color object which is the result of the operation
     */
    public Color scale(double k) {
        if (k < 0)
            throw new IllegalArgumentException("Can't scale a color by a negative number");
        double r = _r * k;
        double g = _g * k;
        double b = _b * k;
        return new Color(r, g, b);
    }

    /**
     * Scale the color by (1 / reduction factor)
     * @param k reduction factor
     * @return new Color object which is the result of the operation
     */
    public Color reduce(double k) {
        if (k < 1)
            throw new IllegalArgumentException("Can't scale a color by a by a number lower than 1");
        double r = _r / k;
        double g = _g / k;
        double b = _b / k;
        return new Color(r, g, b);
    }
    
    /**
     * this function calculate the average of the colors
     * @param colors - list
     * @return color
     */
    public static Color average(List<Color> colors){
    	double r = 0;
		double g = 0;
		double b = 0;
		for(Color c: colors){
			java.awt.Color temp= c.getColor();
			r += temp.getRed();
			g += temp.getGreen();
			b += temp.getBlue();
		}
		return new Color(r,g,b).reduce(colors.size());
    }
    
    
    /**
     * this function calculate the average of the colors
     * @param size - the number of the colors
     * @param colors - arguments
     * @return color
     */
    public static Color average(int size, Color... colors){
    	double r = 0;
		double g = 0;
		double b = 0;
		for(Color c: colors){
			java.awt.Color temp= c.getColor();
			r += temp.getRed();
			g += temp.getGreen();
			b += temp.getBlue();
		}
		return new Color(r,g,b).reduce(size);
    }

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Color other = (Color) obj;
//		if (Math.abs(_b - other._b)>4)
//			return false;
//		if(Math.abs(_g - other._g)>4)
//			return false;
//		if (Math.abs(_r - other._r)>4)
//			return false;
//		return true;
//	}
//	
    
    
    /**
     * this function calculate the distance between the color - 
     * this distance is not oucleed - to r, g, and b there id different weight
     * @param red1 - r in RGB color1
     * @param green1 - g in RGB color1
     * @param blue1 - b in RGB color1
     * @param red2 - r in RGB color2
     * @param green2 - g in RGB color2
     * @param blue2 - b in RGB color2
     * @return the distance without square
     */
    public static double colourDistance2(double red1,double green1, double blue1, double red2, double green2, double blue2)
    {
        double rmean = ( red1 + red2 )/2;
        double r = red1 - red2;
        double g = green1 - green2;
        double b = blue1 - blue2;
        double weightR = 512 + rmean;
        double weightG = 4.0;
        double weightB = 767-rmean;
        return weightR*r*r/256 + weightG*g*g + weightB*b*b/256;
    }
    
	/**
	 * this function get list of colors and check if they are similar
	 * 
	 * @param colors - list of colors
	 * @return true or false
	 */
	public static boolean equals(List<Color> colors) {
		
		Color c0 = Color.average(colors);//colors.get(0);
		for(Color c:colors){
			if(colourDistance2(c0._r, c0._g, c0._b,c._r,c._g,c._b)>Min_Distance)
				return false;
		}
		return true;
	}
    
    
    
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Color other = (Color) obj;
//		if (!isZero(_b - other._b))
//			return false;
//		if (!isZero(_g - other._g))
//			return false;
//		if (!isZero(_r - other._r))
//			return false;
//		return true;
//	}
    

}
