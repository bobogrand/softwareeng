package Touch;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;
import lejos.hardware.Sound;

public class Touchsensor extends AbstractFilter{
	float[] sample;
	
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
