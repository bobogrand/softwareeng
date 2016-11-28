package fighting_robot;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;


import lejos.hardware.*;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RMIRegulatedMotor;

public class Robot {
		RemoteEV3 ev3;
		static RMIRegulatedMotor motorB;
		static RMIRegulatedMotor motorC;
		static boolean stop = false;
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException  {
		// TODO Auto-generated method stub
		// Creates the remote ev3
		RemoteEV3 ev3=new RemoteEV3("10.0.1.1");
		ev3.isLocal();
		
		//get ports
		ev3.getPort("B");
		ev3.getPort("C");
		
		// creates motors
		
		if(motorB==null){motorB = ev3.createRegulatedMotor("B", 'L');}
		if(motorC==null){motorC = ev3.createRegulatedMotor("C", 'L');}
		
		//lets move some motors
		
		
		while(!stop){
			motorB.forward();
			motorC.forward();
			timer();
			motorB.stop(true);
			motorC.stop(true);
			stop = true;
		
		}
		motorB.stop(true);
		motorC.stop(true);
		//beep because its nice to know we are done
		Sound.twoBeeps();
		
		if(!stop){
			//close ports. Must be completed every time
			motorB.close();
			motorC.close();
		}
	}
	
	public static void timer(){
		try{
			Thread.sleep(1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}	
	}
	

}

