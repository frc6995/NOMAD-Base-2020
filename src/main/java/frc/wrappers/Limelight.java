package frc.wrappers;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * This class represents a limelight.
 * 
 * @author Sammcdo
 */
public class Limelight implements Sendable {
    /**
     * The possible states of the limelight LEDs.
     */
    public enum LedState {
        On, Off, Blink, Preset
    }

    /**
     * The possible camera modes of a limelight.
     */
    public enum CameraMode {
        Driver, Vision
    }

    /** The vertical field of view of the limelight in degrees. */
    public double kVertFOV = 41.0;
    /** The horizontal field of view of the limelight in degrees. */
    public double kHorFOV = 54.0;

    private String tableName;
    private boolean mode3d = false;

    /**
     * A class representing a limelight.
     * @param name The name of the limelight network table.
     */
    public Limelight(String name) {
        this.tableName = name;
    }

    /**
     * A class representing a limelight.
     * @param name The name of the limelight network table.
     * @param mode3D whether the limelight is in 3d mode.
     */
    public Limelight(String name, boolean mode3D) {
        this(name);
        this.mode3d = mode3D;
    }

    
    /**
     * Returns whether the limelight sees a valid target.
     * This is the value of entry 'tv' as a boolean.
     * @return True if the limelight sees a target.
     */
    public boolean hasTarget() {
        if (get("tv") == 1) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Get the Horizontal Offset from the target.
     * The value will be a between +-29.8 degrees.
     * This is the value of entry 'tx'.
     * @return The horizontal offset in degrees.
     */
    public double getXOffset() {
        return get("tx");
    }
    
    /**
     * Get the Vertical Offset from the target.
     * The value will be between +- 24.85 degrees.
     * This is the value of entry 'ty'.
     * @return The vertical offset in degrees.
     */
    public double getYOffset() {
        return get("ty");
    }
    
    /**
     * Get the estimated skew from the target.
     * The value will be between -90 and 0 degrees.
     * This is the value of 'ts'.
     * @return The skew in degrees.
     */
    public double getSkew() {
        return get("ts");
    }
    
    /**
     * Return the current vision pipeline,
     * from 0-9.
     * @return
     */
    public double getPipeline() {
        return get("getpipe");
    }
    
    /**
     * Set the limelight pipeline.
     * This must be a whole number
     * between 0 and 9.
     * @param index
     */
    public void setPipeline(double index) {
        set("pipeline", index);
    }

    /**
     * Set the state of the LEDs.
     * This changes the value of the 'ledMode'
     * entry.
     * @param state The desired {@link LedState}
     */
    public void setLedMode(LedState state) {
        switch (state) {
        case On:
            set("ledMode", 3);
            break;
        case Off:
            set("ledMode", 1);
            break;
        case Blink:
            set("ledMode", 2);
            break;
        case Preset:
            set("ledMode", 0);
            break;
        }
    }

    /**
     * Set the camera mode to the specified state.
     * This sets the network table entry 'camMode'.
     * @param state The desired {@link CameraMode}.
     */
    public void setCamMode(CameraMode state) {
        switch (state) {
            case Driver:
                set("camMode", 1);
                break;
            case Vision:
                set("camMode", 0);
                break;
        }
    }

    public Limelight3dPos get3dPosition() {
        if (this.mode3d) {
            return new Limelight3dPos(getArray("camtran"));
        }
        else {
            return new Limelight3dPos(new double[6]);
        }
    }


    private double get(String varName){
        return NetworkTableInstance.getDefault().getTable(this.tableName).getEntry(varName).getDouble(0);
    }

    private double[] getArray(String varName){
        return NetworkTableInstance.getDefault().getTable(this.tableName).getEntry(varName).getDoubleArray(new double[0]);
    }

    private void set(String varName, double value){
        NetworkTableInstance.getDefault().getTable(this.tableName).getEntry(varName).setNumber(value);
    }

    private String getTableName() {
        return this.tableName;
    }

    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Limelight");
        builder.addDoubleProperty("Horizontal Offset", this::getXOffset, null);
        builder.addDoubleProperty("Vertical Offset", this::getYOffset, null);
        builder.addDoubleProperty("Skew", this::getSkew, null);
        builder.addDoubleProperty("Pipeline", this::getPipeline, this::setPipeline);
        builder.addStringProperty("Table Name", this::getTableName, null);
    }

    /**
     * A class representing the results of the
     * limelights 3d calculations.
     */
    public class Limelight3dPos{
        public double x,y,z,pitch,yaw,roll;
        public double[] camtran;
        public Limelight3dPos(double[] camtran) {
            this.camtran = camtran;
            this.x = camtran[0];
            this.y = camtran[1];
            this.z = camtran[2];
            this.pitch = camtran[3];
            this.yaw = camtran[4];
            this.roll = camtran[5];
        }
    }
}