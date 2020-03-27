package frc.utility;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * This class allows us to simply create and use moving averages for doubles.
 */
public class MovingAverage implements Sendable {
    private int size;
    private ArrayList<Double> samples;

    /**
     * A class that allows us to simply create and use moving averages for doubles.
     * @param size The maximum number of items to average.
     */
    public MovingAverage(int size) {
        this.size = size;
        samples = new ArrayList<Double>();
    }

    /**
     * Add a number to be averaged. If the length is
     * greater than the maximum allowed size, it removes one.
     * @param sample A double to be averaged.
     */
    public void addSample(double sample) {
        samples.add(sample);
        if (samples.size() > this.size) {
            samples.remove(0);
        }
    }

    /**
     * Get the averaged value.
     * @return The averaged value.
     */
    public double getAverage() {
        if (samples.size() >= 1) {
            double total = 0;

            for (double sample : this.samples) {
                total += sample;
            }

            return total / this.samples.size();
        }
        else {
            return 0.0;
        }
    }

    /**
     * Get the current length of the samples array.
     * @return The length of the sample array.
     */
    public int getSetSize() {
        return samples.size();
    }

    /**
     * Get the maximum size of the sample array.
     * @return The maximum number of items to be averaged.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Set the maximum size of the sample array.
     * @param index The new maximum number of items to be averaged.
     */
    public void setSize(int index) {
        this.size = index;
    }

    /**
     * Set the maximum size of the sample array.
     * @param index The new maximum number of items to be averaged.
     */
    public void setSize(double index) {
        int newSize = (int)index;
        this.size = newSize;
    }

    /**
     * Clear the array of numbers to be averaged.
     */
    public void clear() {
        samples.clear();
    }

    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("MovingAverage");
        builder.addDoubleProperty("Average", this::getAverage, null);
        builder.addDoubleProperty("Size", this::getSize, this::setSize);
    }
}
