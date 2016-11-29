package fighting_robot;


import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import java.util.Random;

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
		public static DifferentialPilot pilot; 		// now arc and angle
		public static int speed;					// 	now speed
		
		public static EV3TouchSensor EV3touch;		// touch
		public static NXTLightSensor NXTlight;			// light
		public static Ultrasonic sonar;				// sonar
		
		//public static boolean line;
		


		
		// init
	public static void init(){
		// set speed
		speed = 150;
		pilot = new DifferentialPilot(1.5f,6,Left,Right);	//POINT
	
		//get port
		Brick b = BrickFinder.getDefault();
		Port s1 = b.getPort("S1");		// s1 is light	
		Port s2 = b.getPort("S2");		// s2 is touch
		Port s4 = b.getPort("S4")		// s3 is sonar
		
		NXTlight = new NXTLightSensor(s1);
		EV3UltrasonicSensor EV3sonar = new EV3UltrasonicSensor(s4);
		EV3touch = new EV3TouchSensor(s2);
		sonar = new Ultrasonic(EV3sonar.getMode("Distance"));
		
		return;
	}
		
	/** main function */ 
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException  {
		
		init();
		
		
		/** 매 시간마다 센서들을 감지한다. */

		while(true){
			
			
			
			
		}
		
		
		
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
			Random rand = new Random();
			pilot.arcBackward(rand.nextInt(30));
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
