package frc.robot.wrappers.motorcontrollers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;;

/**
 * This class is an encapsulation of WPI_SparkMAX that add a couple constructors
 * for forcing common settings.
 */
public class NomadSparkMax extends CANSparkMax {
    /** This decides if the talon should operate in lazy mode. */
    protected boolean lazy = false;

    protected double lastPower = Double.NaN;
    protected ControlType lastMode = null;

    /**
     * Constructs a SparkMAX, reverts it to factory default, and sets brake mode.
     * 
     * @param port The CAN ID of this SparkMAX
     */
    public NomadSparkMax(int port) {
        super(port, MotorType.kBrushless);
        restoreFactoryDefaults();
        setIdleMode(IdleMode.kBrake);
    }

    /**
     * Constructs a SparkMAX, reverts it to factory default, and sets brake mode and
     * inversion status.
     * 
     * @param port     The CAN ID of this SparkMAX.
     * @param inverted True for inverted, false if not.
     */
    public NomadSparkMax(int port, boolean inverted) {
        this(port);
        setInverted(inverted);
    }

    /**
     * Constructs a SparkMAX, reverts it to factory default, sets brake mode and
     * inversion status, and slaves it to a specified NomadSparkMAX.
     * 
     * @param port     The CAN ID of this SparkMAX.
     * @param inverted True for inverted, false if not.
     * @param master   The NomadSparkMAX to follow.
     */
    public NomadSparkMax(int port, boolean inverted, NomadSparkMax master) {
        this(port, inverted);
        follow(master);
    }

    /**
     * Check if the motor controller is lazy
     * 
     * @return Whether the motor controller is lazy
     */
    public boolean isLazy() {
        return lazy;
    }

    /**
     * Set the lazy mode
     * 
     * @param isLazy A boolean for the lazy mode, where true is lazy on
     */
    public void setLazy(boolean isLazy) {
        lazy = isLazy;
    }

    /**
     * Set the controller reference value based on the selected control mode.
     *
     * @param value The value to set depending on the control mode. For basic duty
     *              cycle control this should be a value between -1 and 1 Otherwise:
     *              Voltage Control: Voltage (volts) Velocity Control: Velocity
     *              (RPM) Position Control: Position (Rotations) Current Control:
     *              Current (Amps). Native units can be changed using the
     *              setPositionConversionFactor() or setVelocityConversionFactor()
     *              methods of the CANEncoder class
     *
     * @param ctrl  Is the control type
     *
     * @return CANError Set to REV_OK if successful
     *
     */
    public void set(ControlType type, double setpoint) {
        if (lazy) {
            if (setpoint != lastPower || type != lastMode) {
                lastPower = setpoint;
                lastMode = type;
                super.getPIDController().setReference(setpoint, type);
            }
        } else {
            super.getPIDController().setReference(setpoint, type);
        }
    }
}