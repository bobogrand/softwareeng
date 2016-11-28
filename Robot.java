package fighting_robot;


import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.remote.ev3.RemoteEV3;


import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.Touch;


import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.EV3TouchSensor;


public class Robot {
		
	//direction constant
		public static final int FORWARD  = 0;
		public static final int BACKWARD = 1;
		public static final int ROTATE = 2;
		public static final int TURN_LEFT = 3;
		public static final int TURN_RIGHT = 4;
		public static final int RANDOM = 5;
		
		public static final boolean BLACK = true;
		public static final boolean WHITE = false;
		public static final boolean TOUCHED = true;
		public static final boolean UNTOUCHED = false;
	
		RemoteEV3 ev3;	
		/** motor part */
		public static RegulatedMotor Left = Motor.B;	//LEFT MOTOR
		public static RegulatedMotor Right = Motor.C;	//RIGHT MOTOR
		
		// now state
		public static DifferentialPilot pilot; 
		public static int speed;
		//public static boolean line;
		public static Lightsensor Light;
		

		/** test case */
		
		public static void test() {
			
			Lightsensor light;
			Touchsensor touch;
			
			// TODO Auto-generated method stub
			Brick b = BrickFinder.getDefault();
			Port s1 = b.getPort("S1");
			Port s2 = b.getPort("S2");
			//Port s4 = b.getPort("S4");
			NXTLightSensor NXTlight = new NXTLightSensor(s1);
			//EV3UltrasonicSensor EV3sonar = new EV3UltrasonicSensor(s4);
			EV3TouchSensor EV3touch = new EV3TouchSensor(s2);
			
			//sonar = new Ultrasonic(EV3sonar.getMode("Distance"));
			touch = new Touchsensor(EV3touch);
			light = new Lightsensor(NXTlight);
			
			//if(sonar.distance() < 0.45){
			//}
			if(touch.pressed()== TOUCHED){
				Sound.twoBeeps();
				sleep(2000);
			}
			if(light.pressed()== WHITE){
				Sound.twoBeeps();
				sleep(2000);
			}
				
		}
		
		/*end tesr**/
		
		
		// init
	public static void init(){
		// set speed
		speed = 150;
		pilot = new DifferentialPilot(1.5f,6,Left,Right);	//POINT
		return;
	}
		
	/** main function */ 
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException  {
		
		init();

		test();
		return;
		
	}
	
	
	public static void motor(int move, int angle){
		pilot.setTravelSpeed(speed);
		switch(move){
		//Forward
		case FORWARD:
			pilot.forward();
			break;
		//backward
		case BACKWARD:
			pilot.backward();
			break;
		//rotate
		case ROTATE:
			pilot.rotate(angle);
			break;
		//turn left
		case TURN_LEFT:
			pilot.arc(5, -90);
			break;
		//turn right
		case TURN_RIGHT:
			pilot.arc(5, 90);
			break;
			
		case RANDOM:
			
			
			break;
		}

		
	}
	
	

	
	public static void sleep(int time){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
