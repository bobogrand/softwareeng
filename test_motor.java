package fighting_robot;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;


import lejos.hardware.motor.Motor;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.RegulatedMotor;



public class Robot {
		
		
	
		RemoteEV3 ev3;		
		public static RegulatedMotor Left = Motor.B;
		public static RegulatedMotor Right = Motor.C;
		
		

		//init
		public static void init(){
			// set speed
			Left.setSpeed(150);
			Right.setSpeed(150);
			return;
		}
		
		
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException  {
		init();
		motor(0,0,0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		motor(1,0,0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		motor(4,180,0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void motor(int move, int angle, int speed){

		if(speed != 0){
			Left.setSpeed(speed);
			Right.setSpeed(speed);
		}
		
		switch(move){
			
		//forward
			case 0:
				Left.forward();
				Right.forward();
			break;		
		//backward
			case 1:
				Left.backward();
				Right.backward();
			break;
		//turn left
			case 2:
			break;
		//turn right
			case 3:
				break;
		//rotate
			case 4:
				Left.rotate(angle);
				Right.rotate(angle);
				break;
		}
	}
	

}
