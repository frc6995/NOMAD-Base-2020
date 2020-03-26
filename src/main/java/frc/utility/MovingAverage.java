package frc.utility;

import java.util.ArrayList;

/**
 * This class allows us to simply create and use moving averages for doubles.
 */
public class MovingAverage {
    private int size;
    private ArrayList<Double> samples;


    public MovingAverage (int size) {
        this.size = size;
    }

    public void addSample (double sample) {
        samples.add(sample);
        if (samples.size() > this.size) {
            samples.remove(0);
        }
    }

    public double getAverage () {
        double total = 0;

        for (double sample : this.samples) {
            total += sample;
        }

        return total / this.samples.size();
    }

    public int getSize () {
        return samples.size();
    }

    public void clear () {
        samples.clear();
    }
}
