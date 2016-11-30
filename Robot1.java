package goym;


import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.remote.ev3.RemoteEV3;


import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.Touch;


import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.EV3TouchSensor;

import lejos.utility.Stopwatch;

import java.util.Random;

public class Robot1 {
		
	//direction constant
		public static final int FORWARD  = 0;
		public static final int BACKWARD = 1;
		public static final int ROTATE = 2;
		public static final int TURN_LEFT = 3;
		public static final int TURN_RIGHT = 4;
		public static final int RANDOM = 7;
		
	//sensor constant
		public static final boolean BLACK = true;
		public static final boolean WHITE = false;
		public static final boolean TOUCHED = true;
		public static final boolean UNTOUCHED = false;
		
	//mode constant	
		public static final boolean D = true;
		public static final boolean A = false;
	
		RemoteEV3 ev3;	
		/** motor part */
		public static RegulatedMotor Left = Motor.B;
		public static RegulatedMotor Right = Motor.C;
		public static RegulatedMotor Back = Motor.A; // back wheel. rotating reverse
		// now state
		public static DifferentialPilot pilot; 
		public static double speed;
		//public static boolean line;
		public static Lightsensor Light;
		
// sensor
		static Lightsensor light;
		static Touchsensor touch;
		static Sonarsensor sonar_EV3;
		
		static Brick b;
// port
		static Port s1;
		static Port s2;
		static Port s3;
		static Port s4;
		
		static Port mb;
		static Port mc;
//timer
		static Stopwatch timer;
		/** test case */
		
		public static void test(boolean flag) {
			// attack mode
			if(flag == A) {
				while(true) {
					if(timer.elapsed() >= 30000)
						break;
					rotate(70);
				}
			}
			
		}
		public static void return_back(){
			int elapsed_time;
			//motor(BACKWARD,0);
			pilot.setRotateSpeed(100);
			motor(ROTATE,70);
			motor(FORWARD,0);
			int start_time = timer.elapsed();
			while(true){
				elapsed_time = timer.elapsed();
				if((elapsed_time - start_time) > 800){
					Sound.beep();
					pilot.stop();
					motor(ROTATE,60);
					motor(FORWARD,0);
					start_time = timer.elapsed();
				}
				if(light.get_light() == BLACK){
					Sound.twoBeeps();
					break;
				}
			}
			sleep(100);
			pilot.setRotateSpeed(speed);
		}
		public static void rotate(int speed){
			pilot.setRotateSpeed(speed);
			pilot.rotateLeft();
			Back.backward();
			while(true){
				if(light.get_light() == WHITE){
					Sound.beep();
					return_back();
					pilot.setRotateSpeed(100);
					pilot.rotateLeft();
					pilot.setRotateSpeed(speed);
				}
				if(sonar_EV3.distance() <=0.50){
					ATT();
					Sound.buzz();
					pilot.stop();
					break;
				}
			}
		}
		public static void ATT(){
			if(sonar_EV3.distance() <=0.40){
				
				motor(FORWARD,0);
				while(true) {
					//sleep(400);

					// background color first
					if(light.get_light() == WHITE) {
						break;
					}
					// detect distance second
					if(sonar_EV3.distance() > 0.30 ) {
						break;					
					}
				}	
			}

			sleep(400);
		}
		/*end tesr**/		
		// init
	public static void init(){
		// set speed
		
		// TODO Auto-generated method stub
		b = BrickFinder.getDefault();
		s1 = b.getPort("S1");
		s2 = b.getPort("S2");
		s3 = b.getPort("S3");
		s4 = b.getPort("S4");
		
		
		//set motor speed
		Motor.B.setSpeed(36000);
		Motor.C.setSpeed(36000);
		Motor.A.setSpeed(36000);
		Motor.B.setAcceleration(36000);
		Motor.C.setAcceleration(36000);
		Motor.A.setAcceleration(36000);
		
		
		
		/** NXT sonar sensor cancel */
		//set sensor
		pilot = new DifferentialPilot(1.5f,6,Left,Right);	//POINT
		NXTLightSensor NXTlight = new NXTLightSensor(s1);
//		EV3TouchSensor EV3touch = new EV3TouchSensor(s2); //do not use
		//NXTUltrasonicSensor NXTsonar = new NXTUltrasonicSensor(s3);
		EV3UltrasonicSensor EV3sonar = new EV3UltrasonicSensor(s4);
		
		//sonar_NXT = new Sonarsensor(NXTsonar.getMode("Distance"));
		sonar_EV3 = new Sonarsensor(EV3sonar.getMode("Distance"));
		light = new Lightsensor(NXTlight);
		
		//if(sonar.distance() < 0.45){
		//}
		
		timer = new Stopwatch();
		timer.reset();
		
		return;
	}
		
	/** main function */ 
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException  {
		
		init();
		//return_back();
		
		test(A);
		return;
	}
	
	public static void motor(int move, int angle){
		//pilot.setTravelSpeed(speed);
		// A motor stop;\
		Back.backward();
		switch(move){
		//Forward
		case FORWARD:
			Motor.A.setSpeed(36000);
			Motor.B.setSpeed(36000);
			Motor.C.setSpeed(36000);
			Motor.B.setAcceleration(36000);
			Motor.C.setAcceleration(36000);
			Left.forward();
			Right.forward();
			Back.backward();
			//pilot.forward();
			break;
		//backward
		case BACKWARD:
			Motor.A.forward();
			pilot.backward();
			break;
		//rotate
		case ROTATE:
			pilot.rotate(angle);
			break;
		//turn left
		case TURN_LEFT:
			pilot.arc(-3, -angle);
			break;
		//turn right
		case TURN_RIGHT:
			pilot.arc(3, angle);
			break;
		case RANDOM:
			Random rand = new Random();
			int a = rand.nextInt(6);
			int b = rand.nextInt(200);
			a -=3; b-=100;
			pilot.arc(a, b);
			break;
		}
	}
	
	

	
	public static void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}