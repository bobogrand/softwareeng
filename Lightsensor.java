package goym;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;

public class Lightsensor extends AbstractFilter{
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;
	
	float[] sample;
	public Lightsensor(SampleProvider source){
		super(source);
		sample = new float[sampleSize];
	}
	
	public boolean get_light(){
		super.fetchSample(sample, 0);
		if(sample[0] >= 0.35){
			return WHITE;
		} else {
			return BLACK;
		}
	}
}