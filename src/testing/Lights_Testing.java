package testing;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class Lights_Testing {

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void sphereDirectional() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50, new Material(0.5, 0.5, 100)));

        scene.addLights(new DirectionalLight(new Vector(1, -1, 1), new Color(500, 300, 0)));

        ImageWriter imageWriter = new ImageWriter("sphereDirectional", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.get_imageWriter().writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a point light
     */
    @Test
    public void spherePoint() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50, new Material(0.5, 0.5, 100)));

        scene.addLights(new PointLight(new Point3D(-50, 50, -50), 1, 0.00001, 0.000001, new Color(500, 300, 0)));

        ImageWriter imageWriter = new ImageWriter("spherePoint", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();
        render.renderImage();
        render.get_imageWriter().writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void sphereSpot() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50, new Material(0.5, 0.5, 100)));

        scene.addLights(new SpotLight(new Vector(1, -1, 2), new Point3D(-50, 50, -50), 1, 0.00001, 0.00000001,  new Color(500, 300, 0)));

        ImageWriter imageWriter = new ImageWriter("sphereSpot", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.get_imageWriter().writeToImage();
    }
    
    /**
     * Produce a picture of a sphere lighted by 3 light sources
     */
    @Test
    public void sphereMultiLights() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));//java.awt.Color.BLUE    192,192,192

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50, new Material(0.5, 0.5, 100)));

        scene.addLights(new SpotLight(new Vector(1, -1, 2), new Point3D(-50, 50, -50), 1, 0.00001, 0.00000001,  new Color(224,255,255)));
        scene.addLights(new DirectionalLight(new Vector(-1, 1, 1), new Color(255,255,0)));
        scene.addLights(new PointLight(new Point3D(50, 50, -10), 1, 0.00001, 0.000001, new Color(250,128,114)));
        
        ImageWriter imageWriter = new ImageWriter("sphereMultiLights", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.get_imageWriter().writeToImage();
    }
    
    
    /**
     * Produce a picture of a sphere lighted by a spot light - spot is focus
     */
    @Test
    public void sphereSpot_Sc() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));//java.awt.Color.BLUE    192,192,192

        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50, new Material(0.5, 0.5, 100)));

        scene.addLights(new SpotLight(new Vector(1, -1, 2), new Point3D(-50, 50, -50), 1, 0.00001, 0.00000001, 0.5, new Color(500, 300, 0)));
        
        ImageWriter imageWriter = new ImageWriter("sphereSpot_Sc", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.get_imageWriter().writeToImage();
    }
    

    /**
     * Produce a picture of a two triangles lighted by a directional light
     */
    @Test
    public void trianglesDirectional() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150), new Material(0.8, 0.2, 300)),
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150), new Material(0.8, 0.2, 300)));

        scene.addLights(new DirectionalLight(new Vector(0, 0, 1), new Color(300, 150, 150)));

        ImageWriter imageWriter = new ImageWriter("trianglesDirectional", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.get_imageWriter().writeToImage();
        
    }

    /**
     * Produce a picture of a two triangles lighted by a point light
     */
    @Test
    public void trianglesPoint() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150), new Material(0.5, 0.5, 300)),
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150), new Material(0.5, 0.5, 300)));

        scene.addLights(new PointLight(new Point3D(10, 10, 130), 1, 0.0005, 0.0005, new Color(500, 250, 250)));

        ImageWriter imageWriter = new ImageWriter("trianglesPoint", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.get_imageWriter().writeToImage();
        
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light
     */
    @Test
    public void trianglesSpot() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150), new Material(0.5, 0.5, 300)),
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150), new Material(0.5, 0.5, 300)));

        scene.addLights(new SpotLight(new Vector(-2, 2, 1), new Point3D(10, 10, 130), 1, 0.0001, 0.000005, new Color(500, 250, 250)));

        ImageWriter imageWriter = new ImageWriter("trianglesSpot", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.get_imageWriter().writeToImage();
    }
    
    /**
     * Produce a picture of a two triangles lighted by by 3 light sources
     */
    @Test
    public void trianglesMultiLghts() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150), new Material(0.8, 0.2, 300)),
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150), new Material(0.8, 0.2, 300)));

        scene.addLights(new DirectionalLight(new Vector(0, 0, 1), new Color(47,79,79)));
        scene.addLights(new SpotLight(new Vector(2, -2, 1), new Point3D(50, 100, 130), 1, 0.0001, 0.00000005, new Color(32,178,170)));
        scene.addLights(new PointLight(new Point3D(-15, -15, 100), 1, 0.000001, 0.00003, new Color(219, 112, 147)));

        
        ImageWriter imageWriter = new ImageWriter("trianglesMultiLghts", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.get_imageWriter().writeToImage();
        
    }
    
    /**
     * Produce a picture of a two triangles lighted by a spot light - spot is focus
     */
    @Test
    public void triangleSpot_Sc() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150), new Material(0.8, 0.2, 300)),
                new Triangle(Color.BLACK, new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150), new Material(0.8, 0.2, 300)));

        scene.addLights(new SpotLight(new Vector(-2, 2, 1), new Point3D(10, 10, 130), 1, 0.0001, 0.000005, 0.5, new Color(500, 250, 250)));

        
        ImageWriter imageWriter = new ImageWriter("triangleSpot_Sc", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.get_imageWriter().writeToImage();
        
    }
    
    
}
