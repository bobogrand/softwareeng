package fighting_robot;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;
import lejos.hardware.Sound;

public class Touchsensor extends AbstractFilter{
	float[] sample;
	public static final boolean TOUCHED = true;
	public static final boolean UNTOUCHED = false;
	
	public Touchsensor(SampleProvider source){
		super(source);
		sample = new float[sampleSize];
	}
	
	public boolean pressed(){
		super.fetchSample(sample, 0);
		if(sample[0] == 0){
			return false;
		} else {
			Sound.twoBeeps();
			return true;
		}
	}

}
