package scene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import geometries.*;
import primitives.Color;
import elements.LightSource;

/**
 * the class scene contain all the elements that scene need to picture
 * 
 * @author Li Edri
 * @author Adi Giladi
 *
 */
public class Scene {

	protected String _name;
	protected Color _background;
	protected AmbientLight _ambientLight;
	protected Geometries _geometries;
	protected Camera _camera;
	protected double _distance;
	protected List<LightSource> _lights;
	
	
	/**
	 * constructor
	 * 
	 * @param name
	 */
	public Scene(String name){
		_name = name;
		_geometries = new Geometries();
		_lights = new ArrayList<LightSource>();
	}

	
	/**
	 * getter to the list of the lights in the scene.
	 * @return list of light sources
	 */
	public List<LightSource> get_lights() {
		return _lights;
	}

	
	/**
	 * getter to the name of the scene.
	 * @return string of name
	 */
	public String get_name() {
		return _name;
	}

	
	/**
	 * getter to the color of the background of the scene.
	 * @return color
	 */
	public Color get_background() {
		return _background;
	}

	
	/**
	 * setter to the color of the background of the scene.
	 */
	public void set_background(Color _background) {
		this._background = _background;
	}

	
	/**
	 * getter to the color of the background of the scene.
	 * @return color
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}

	
	/**
	 * setter to the color of the background of the scene.
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}

	
	/**
	 * getter to the list of the geometries in the scene.
	 * @return list of the geometries
	 */
	public Geometries get_geometries() {
		return _geometries;
	}

	
	/**
	 * getter to the camera
	 * @return camera
	 */
	public Camera get_camera() {
		return _camera;
	}

	
	/**
	 * setter to the camera
	 * @param camera
	 */
	public void set_camera(Camera camera) {
		this._camera = camera;
	}

	
	/**
	 * getter to the distance
	 * @return distance
	 */
	public double get_distance() {
		return _distance;
	}

	
	/**
	 * setter to the distance
	 * @param _distance
	 */
	public void set_distance(double _distance) {
		this._distance = _distance;
	}
	
	
	/**
	 * this function add geometries to the list of the geometries of the scene
	 * @param geometries
	 */
	public void addGeometries(Intersectable... geometries){
		_geometries.add(geometries);
	}
	
	
	/**
	 * this function add lights to the list of the light sources of the scene
	 * @param lights
	 */
	public void addLights(LightSource... lights){
		for(LightSource l : lights)
		{
			_lights.add(l);
		}
	}
}
