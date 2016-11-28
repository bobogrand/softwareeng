package fighting_robot;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;


import lejos.hardware.motor.Motor;
import lejos.remote.ev3.RemoteEV3;


import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.RegulatedMotor;



public class Robot {
		
	//direction
		public static final int FORWARD  = 0;
		public static final int BACKWARD = 1;
		public static final int ROTATE = 2;
		public static final int TURN_LEFT = 3;
		public static final int TURN_RIGHT = 4;

	
		RemoteEV3 ev3;	
		/** motor part */
		public static RegulatedMotor Left = Motor.B;	//LEFT MOTOR
		public static RegulatedMotor Right = Motor.C;	//RIGHT MOTOR
		public static DifferentialPilot pilot; 
		public static int speed;
		

		//init
	public static void init(){
		// set speed
		speed = 150;
		pilot = new DifferentialPilot(1.5f,6,Left,Right);	//POINT
		return;
	}
		
	/** main function */ 
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException  {
		
		init();
		
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
