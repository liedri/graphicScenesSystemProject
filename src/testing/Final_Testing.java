package testing;

import static java.lang.System.out;
import java.util.Random;

import static org.junit.Assert.*;
import java.util.Random;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

public class Final_Testing {
		/**
		 * Produce a picture of a several spheres with beautiful effects
		 */
		@Test
		public void OurPicture1() {
			Scene scene = new Scene("Test scene");
			scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
			scene.set_distance(1000);
			scene.set_background(new Color(176,196,222));
			scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

			scene.addGeometries( 
					new Plane(new Color(java.awt.Color.BLACK), new Point3D(0, 100, 500), new Vector(new Point3D(0,-1,0)), new Material(0.5, 0.5, 0, 0.2, 60)),
					new Sphere(new Color(64,224,208), new Point3D(50, 50, 2000), 50,  new Material(0.2, 0.2, 0.8, 0, 10)),
					new Sphere(new Color(0,255,0), new Point3D(150, 0, 2100), 100,  new Material(0.2, 0.2, 0.5, 0, 100)),
					new Sphere(new Color(32,178,170), new Point3D(-250, -50, 2200), 150,  new Material(0.2, 0.2, 0, 0.2, 100)),
					new Sphere(new Color(220,20,60), new Point3D(-50, 25, 2100), 75,  new Material(0.2, 0.2, 0.3, 0.0, 20))
					);


			scene.addLights(new PointLight(new Point3D(0,-270, 2000), 1, 0.00001, 0.00000001, new Color(255,255,224)));
			scene.addLights(new SpotLight(new Vector(-1,1,0.5), new Point3D(250, -200, 1900), 1, 4E-7, 2E-10, new Color(500, 300, 0)));
			
			ImageWriter imageWriter = new ImageWriter("OurPicture1", 200, 200, 600, 600);
			Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

			render.renderImage(2);
			render.get_imageWriter().writeToImage();
		}
		
		/**
		 * Produce a picture of a some spheres with light
		 */
		//@Test
		public void OurPicture2() {
			Scene scene = new Scene("Test scene");
			//scene.set_camera(new Camera(new Point3D(0, -1000, -800), new Vector(0,1,2), new Vector(0,2,-1)));
			scene.set_camera(new Camera(new Point3D(0, -620, -800), new Vector(0,1,Math.sqrt(3)), new Vector(0,-1*Math.sqrt(3), 1)));
			//scene.set_camera(new Camera(new Point3D(0, -1000, -800), new Vector(0,1,1), new Vector(0,-1, 1)));
			//scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));//regular camera
			scene.set_distance(1000);
			scene.set_background(new Color(65,105,225));
			scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
			//scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.15));

		
			Color c = new Color(0,0,0);
			int x = -150;
			int z = 100;
			for(int j=0; j<4;j++){
				x = -150;
				for(int i=0; i<4;i++){
					scene.addGeometries(new Sphere(c, new Point3D(x, 70, z), 50,  new Material(0.2, 0.2, 0, 0, 100)));
					x += 100;
				}
			
				x = -150;
				for(int i=0; i<4;i++){
					if (j==2 && i==2)
						scene.addGeometries(new Sphere(new Color(32,178,170), new Point3D(0, 70, 400), 50,  new Material(0.2, 0.2, 0, 0.3, 100)));
					else
						scene.addGeometries(new Sphere(c, new Point3D(x-50, 70, z-100), 50,  new Material(0.2, 0.2, 0, 0, 100)));
					x += 100;
				}
				z += 200;
			}
			scene.addLights(new SpotLight(new Vector(-1,1,0.5), new Point3D(50,0,350), 1, 0.0001, 0.0001, new Color(500, 300, 0)));
			scene.addLights(new SpotLight(new Vector(-1,1,0.5), new Point3D(200,-200,350), 1, 2E-7, 2E-10, new Color(255,255,224)));
			scene.addLights(new DirectionalLight(new Vector(1,5,0), new Color(224,255,255)));

			ImageWriter imageWriter = new ImageWriter("OurPicture2", 200, 200, 600, 600);
			Render render = new Render(imageWriter, scene).setMultithreading(3);

			render.renderImage(1);
			render.get_imageWriter().writeToImage();
		}
		
		/**
		 * Produce a picture of a corona from Spheres and Triangles
		 */
		//@Test
		public void OurPictureCorona() {
			Scene scene = new Scene("Test scene");
			scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
			scene.set_distance(1000);
			scene.set_background(new Color(6,36,55));
			scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
			double r = 12.5;
			double r2 = 16;
			Color Spheres = new Color(1,168,158);
			Color Peramid = new Color(133,188,60);
			
			scene.addGeometries( 
					// the Earth
					new Sphere(Spheres, new Point3D(0,0,1000), 100,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					
				
					new Triangle(Peramid, new Point3D(-20,20,880), new Point3D(-30,15,900), new Point3D(-22,10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//א
					new Triangle(Peramid, new Point3D(-20,20,880), new Point3D(-22,10,900), new Point3D(-5,13,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ב
					new Triangle(Peramid, new Point3D(-20,20,880), new Point3D(-5,13,900), new Point3D(0,30,860), new Material(0.2, 0.5, 0, 0.0, 100)),//ג
					new Triangle(Peramid, new Point3D(-20,20,880), new Point3D(0,30,860), new Point3D(-20,40,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ד
					new Triangle(Peramid, new Point3D(-20,20,880), new Point3D(-20,40,900), new Point3D(-30,15,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ה
					new Triangle(Peramid, new Point3D(0,30,860), new Point3D(-5,13,900), new Point3D(10,20,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ו
					new Triangle(Peramid, new Point3D(0,30,860), new Point3D(-20,35,900), new Point3D(10,20,900), new Material(0.2, 0.5, 0, 0.0, 100)),	//ז				
					new Triangle(Peramid, new Point3D(0,30,860), new Point3D(25,65,900), new Point3D(10,20,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ח
					new Triangle(Peramid, new Point3D(0,30,860), new Point3D(25,65,900), new Point3D(0,55,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ט
					new Triangle(Peramid, new Point3D(0,30,860), new Point3D(-20,40,900), new Point3D(0,55,900), new Material(0.2, 0.5, 0, 0.0, 100)),//י
					//new Triangle(Peramid, new Point3D(7,75,870), new Point3D(25,65,900), new Point3D(10,75,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יא
					//new Triangle(Peramid, new Point3D(7,75,870), new Point3D(0,85,900), new Point3D(10,75,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יב
					//new Triangle(Peramid, new Point3D(-10,80,900), new Point3D(0,85,900), new Point3D(-20,90,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יג
					new Triangle(Peramid, new Point3D(7,75,870), new Point3D(0,85,900), new Point3D(-10,80,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יד
					new Triangle(Peramid, new Point3D(7,75,870), new Point3D(0,55,900), new Point3D(-10,80,900), new Material(0.2, 0.5, 0, 0.0, 100)),//טו
					new Triangle(Peramid, new Point3D(7,75,870), new Point3D(25,65,900), new Point3D(0,55,900), new Material(0.2, 0.5, 0, 0.0, 100)),//טז
					new Triangle(Peramid, new Point3D(25,65,900), new Point3D(10,20,900), new Point3D(35,55,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יז
					new Triangle(Peramid, new Point3D(-20,10,900), new Point3D(0,55,900), new Point3D(-5,85,900), new Material(0.2, 0.5, 0, 0.0, 100)),//טו

					new Triangle(Peramid, new Point3D(50,-50,850), new Point3D(60,-35,900), new Point3D(57,-60,900), new Material(0.2, 0.5, 0, 0.0, 100)),//א
					new Triangle(Peramid, new Point3D(50,-50,850), new Point3D(10,-45,900), new Point3D(60,-35,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ב
					new Triangle(Peramid, new Point3D(50,-50,850), new Point3D(57,-60,900), new Point3D(20,-80,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ג
					new Triangle(Peramid, new Point3D(50,-50,850), new Point3D(20,-80,900), new Point3D(10,-45,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ד
					new Triangle(Peramid, new Point3D(10,-45,900), new Point3D(0,-65,900), new Point3D(20,-80,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ה
					new Triangle(Peramid, new Point3D(5,-20,830), new Point3D(10,-45,900), new Point3D(60,-35,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ו
					new Triangle(Peramid, new Point3D(5,-20,830), new Point3D(10,-10,900), new Point3D(60,-35,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ז
					new Triangle(Peramid, new Point3D(5,-20,830), new Point3D(10,-10,900), new Point3D(-10,-10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ח
					new Triangle(Peramid, new Point3D(5,-20,830), new Point3D(10,-45,900), new Point3D(-10,-10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//ט
					new Triangle(Peramid, new Point3D(57,-60,900), new Point3D(60,-35,900), new Point3D(35,-40,900), new Material(0.2, 0.5, 0, 0.0, 100)),//י
					new Triangle(Peramid, new Point3D(5,-35,900), new Point3D(35,-40,900), new Point3D(65,-10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יא
					new Triangle(Peramid, new Point3D(10,-45,900), new Point3D(10,-10,900), new Point3D(65,-10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יב
					new Triangle(Peramid, new Point3D(5,-5,830), new Point3D(10,-10,900), new Point3D(65,-10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יג
					new Triangle(Peramid, new Point3D(5,-5,830), new Point3D(10,-10,900), new Point3D(-10,-10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יד
					new Triangle(Peramid, new Point3D(5,-5,830), new Point3D(30,20,900), new Point3D(65,-10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//טו
					new Triangle(Peramid, new Point3D(10,-45,900), new Point3D(0,-65,900), new Point3D(-10,-10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//טז
					new Triangle(Peramid, new Point3D(65,-10,900), new Point3D(65,35,860), new Point3D(30,20,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יז
					new Triangle(Peramid, new Point3D(65,-10,900), new Point3D(65,35,860), new Point3D(75,35,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יח
					new Triangle(Peramid, new Point3D(30,20,900), new Point3D(65,35,860), new Point3D(75,35,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יט
					
					new Triangle(Peramid, new Point3D(-50,-20,850), new Point3D(-60,-35,900), new Point3D(-40,-30,900), new Material(0.2, 0.5, 0, 0.0, 100)),//טו
					new Triangle(Peramid, new Point3D(-50,-20,850), new Point3D(-60,-35,900), new Point3D(-70,-25,900), new Material(0.2, 0.5, 0, 0.0, 100)),//טז
					new Triangle(Peramid, new Point3D(-50,-20,850), new Point3D(-65,0,900), new Point3D(-70,-25,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יז
					new Triangle(Peramid, new Point3D(-50,-20,850), new Point3D(-65,0,900), new Point3D(-55,10,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יח
					new Triangle(Peramid, new Point3D(-40,-15,900), new Point3D(-45,10,900), new Point3D(-30,-5,900), new Material(0.2, 0.5, 0, 0.0, 100)),//יט

					//corona
					new Sphere(Spheres, new Point3D(140,0,1000), r+2,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(140,0,1000), r2+2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(0,140,1000), r,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(0,140,1000), r2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(0,-140,1000), r-2,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(0,-140,1000), r2-2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(-140,0,1000), r,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(-140,0,1000), r2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					
					new Sphere(Spheres, new Point3D(130,-50,1070), r+2,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(130,-50,1070), r2+2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(90,-90,1050), r-2,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(90,-90,1050), r2-2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(50,-120,900), r,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(50,-120,900), r2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					
					new Sphere(Spheres, new Point3D(-130,-50,1070), r,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(-130,-50,1070), r2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(-90,-90,1050), r+2,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(-90,-90,1050), r2+2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(-50,-120,900), r-2,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(-50,-120,900), r2-2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					
					new Sphere(Spheres, new Point3D(130,50,1070), r+2,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(130,50,1070), r2+2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(90,90,1050), r,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(90,90,1050), r2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(50,120,900), r,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(50,120,900), r2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					
					new Sphere(Spheres, new Point3D(-130,50,1070), r-2,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(-130,50,1070), r2-2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(-90,90,1050), r,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(-90,90,1050), r2,  new Material(0.2, 0.2, 0.9, 0.0, 20)),
					new Sphere(Spheres, new Point3D(-50,120,900), r+2,  new Material(0.2, 0.2, 0.0, 0.0, 20)),
					new Sphere(new Color(0,0,0), new Point3D(-50,120,900), r2+2,  new Material(0.2, 0.2, 0.9, 0.0, 20))
					);
			
			for(int i=0; i<40;i+=2)//(0,-140,1000)
				scene.addGeometries(new Sphere(Spheres, new Point3D(0,-100-i,1000), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));

			for(int i=0; i<40;i+=2)//(0,140,1000)
				scene.addGeometries(new Sphere(Spheres, new Point3D(0,100+i,1000), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			for(int i=0; i<40;i+=2)//(-140,0,1000)
				scene.addGeometries(new Sphere(Spheres, new Point3D(-100-i,0,1000), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			for(int i=0; i<40;i+=2)//(140,0,1000)
				scene.addGeometries(new Sphere(Spheres, new Point3D(100+i,0,1000), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			//--------------------------------------		
			
			for(int i=0, j=0; i<40;i+=2, j+=2)//(130,-50,1070)
				scene.addGeometries(new Sphere(Spheres, new Point3D(130-i,-50+j,1070), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));

			for(int i=0, j=0, k= 0; i<40;i+=2, j+=2, k+=2)//(90,-90,1050)
				scene.addGeometries(new Sphere(Spheres, new Point3D(90-i,-90+j,1050-k), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			for(int i=0, j=0, k= 0; i<40;i+=1, j+=2, k+=2)//(50,-120,900)
				scene.addGeometries(new Sphere(Spheres, new Point3D(50-i,-120+j,900+k), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));

			//--------------------------------------	
			
			for(int i=0, j=0; i<40;i+=2, j+=2)//(-130,-50,1070)
				scene.addGeometries(new Sphere(Spheres, new Point3D(-130+i,-50+j,1070), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));

			for(int i=0, j=0, k= 0; i<40;i+=2, j+=2, k+=2)//(-90,-90,1050)
				scene.addGeometries(new Sphere(Spheres, new Point3D(-90+i,-90+j,1050-k), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			for(int i=0, j=0, k= 0; i<40;i+=1, j+=2, k+=2)//(-50,-120,900)
				scene.addGeometries(new Sphere(Spheres, new Point3D(-50+i,-120+j,900+k), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			//--------------------------------------	
			
			for(int i=0, j=0; i<40;i+=2, j+=2)//(130,50,1070)
				scene.addGeometries(new Sphere(Spheres, new Point3D(130-i,50-j,1070), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));

			for(int i=0, j=0, k= 0; i<40;i+=2, j+=2, k+=2)//(90,90,1050)
				scene.addGeometries(new Sphere(Spheres, new Point3D(90-i,90-j,1050-k), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			for(int i=0, j=0, k= 0; i<40;i+=1, j+=2, k+=2)//(50,120,900)
				scene.addGeometries(new Sphere(Spheres, new Point3D(50-i,120-j,900+k), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			//--------------------------------------	
			
			for(int i=0, j=0; i<40;i+=2, j+=2)//(-130,50,1070)
				scene.addGeometries(new Sphere(Spheres, new Point3D(-130+i,50-j,1070), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));

			for(int i=0, j=0, k= 0; i<40;i+=2, j+=2, k+=2)//(90,-90,1050)
				scene.addGeometries(new Sphere(Spheres, new Point3D(-90+i,90-j,1050-k), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			for(int i=0, j=0, k= 0; i<40;i+=1, j+=2, k+=2)//(50,-120,900)
				scene.addGeometries(new Sphere(Spheres, new Point3D(-50+i,120-j,900+k), 4,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
				
			//---------------------------------------
			//-----------little stars----------------
			//---------------------------------------
			Random rand = new Random(); 
			for(int i = 0; i < 150; i++)
				scene.addGeometries(new Sphere(new Color(240,248,255), new Point3D(-500+ rand.nextInt(1000), -500 + rand.nextInt(1000), 3000+ rand.nextInt(1000)), 2,  new Material(0.2, 0.2, 0.0, 0.0, 20)));
			
			//----------------------------------------
			
			Color light = new Color(255,0,0);
			scene.addLights(new PointLight(new Point3D(-200,0,1000), 1, 0.00001, 0.00000001, light));
			scene.addLights(new PointLight(new Point3D(200,0,1000), 1, 0.00001, 0.00000001, light));
			scene.addLights(new PointLight(new Point3D(0,200,1000), 1, 0.00001, 0.00000001, light));
			scene.addLights(new PointLight(new Point3D(0,-200,1000), 1, 0.00001, 0.00000001, light));
			scene.addLights(new PointLight(new Point3D(400,400,1000), 1, 0.00001, 0.00000001, light));
			scene.addLights(new PointLight(new Point3D(400,-400,1000), 1, 0.00001, 0.00000001, light));
			scene.addLights(new PointLight(new Point3D(-400,400,1000), 1, 0.00001, 0.00000001, light));
			scene.addLights(new PointLight(new Point3D(-400,-400,1000), 1, 0.00001, 0.00000001, light));
						
			scene.addLights(new PointLight(new Point3D(0,0, 700), 1, 0.00001, 0.00000001, new Color(85,107,47)));			
			ImageWriter imageWriter = new ImageWriter("OurPictureCorona", 200, 200, 600, 600);
			Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

			render.renderImageASS();
			render.get_imageWriter().writeToImage();
		}
		
		
		/**
		 * Produce a picture of fish in the sea from spheres and triangles
		 */
		//@Test
		public void OurPicture3() {
			Scene scene = new Scene("Test scene");
			//scene.set_camera(new Camera(new Point3D(1000, 0, 1000), new Vector(-1,0,0), new Vector(0, 0,-1)));
			scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));//regular camera
			scene.set_distance(1000);
			scene.set_background(new Color(75,75, 255));
			
			scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

			Color c = new Color(0,102,204);
			scene.addGeometries(
					//Bubbles
					new Sphere(new Color(0,0,255), new Point3D(0,0,1000), 85,  new Material(0.1, 0.6, 0.99,0, 100)),
					new Sphere(new Color(0,0,102), new Point3D(90, 90,1000), 30,  new Material(0.1, 0.6, 0.6,0.2, 100)),
					new Sphere(new Color(0,0,102), new Point3D(125,125,1000), 10,  new Material(0.1, 0.6, 0.6,0.2, 100)),
					
					//body
					new Sphere(c, new Point3D(0,0,1000), 50,  new Material(0.3, 0.7, 0,0, 100)),
					new Triangle(new Color(java.awt.Color.BLUE), new Point3D(-3,30,957), new Point3D(-20,30,954), new Point3D(-10,37,965), new Material(0.2, 0.5, 0, 0.3, 100)),
					new Triangle(c, new Point3D(-6,30,956), new Point3D(-17,30,952.8), new Point3D(-10,34,961), new Material(0.3, 0.7, 0, 0, 100)),
					new Triangle(new Color(112,112,0), new Point3D(25,0,1043), new Point3D(80,-20,1100), new Point3D(80,20,1100), new Material(0.2, 0.7, 0, 0.5, 100)),					
					
					//left eye
					new Sphere(new Color(java.awt.Color.WHITE), new Point3D(-30,0,975), 25,  new Material(0.2, 0.2, 0.5, 0, 100)),					
					new Sphere(new Color(238,130,238), new Point3D(-31,-3.5,970), 23,  new Material(0.2, 0.2, 0.0, 0, 10)),
					new Sphere(new Color(0,0,0), new Point3D(-31,-5,967), 20.7,  new Material(0.2, 0.2, 0,0.3, 50)),
					new Sphere(new Color(255,255,255), new Point3D(-31,-9,956), 9.5,  new Material(0.2, 0.5, 0,0.5, 500)),
					
					//right eye
					new Sphere(new Color(java.awt.Color.WHITE), new Point3D(20,0,965), 27,  new Material(0.2, 0.2, 0.5,0, 100)),
					new Sphere(new Color(238,130,238), new Point3D(19,-2,960), 25,  new Material(0.2, 0.2, 0.0, 0, 10)),
					new Sphere(new Color(0,0,0), new Point3D(18,-4,955), 21.4,  new Material(0.2, 0.2, 0,0.3, 50)),
					new Sphere(new Color(255,255,255), new Point3D(15,-8,944), 10,  new Material(0.2, 0.5, 0,0.5, 500))

					);
			double offset = -700;
			for(int j=-680;j<680;j +=60){
				for(double i = offset;i<700;i += 100){
					scene.addGeometries( 
							new Sphere(new Color(0,0,102), new Point3D(i,j,5000), 10, new Material(0.1, 0.6, 1,0.2, 100)));				
					}
				if(offset == -1000)
					offset += 50;
				else
					offset -= 50;
			}

			//scene.addLights(new SpotLight(new Vector(0,0,1), new Point3D(60, -100, 1500), 1, 4E-5, 2E-7, new Color(245,255,250)));
			scene.addLights(new PointLight(new Point3D(0,95, 1000), 1, 0.00001, 0.00000001, new Color(204,255,255)));
			scene.addLights(new SpotLight(new Vector(1,1,1), new Point3D(-60,-60, 940), 1, 4E-7, 2E-10, 2, new Color(255,255, 255)));
			scene.addLights(new DirectionalLight(new Vector(-1,2,1), new Color(0,204,204)));
			scene.addLights(new DirectionalLight(new Vector(1,1,1), new Color(0,204,204)));

			
			ImageWriter imageWriter = new ImageWriter("OurPicture3-fish", 200, 200, 600, 600);
			Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();
			render.renderImageASS();
			render.get_imageWriter().writeToImage();
		}
		
}		