package frc.wrappers.MotorControllers;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;;

/**
 * This class is an encapsulation of WPI_SparkMAX that add a couple constructors for forcing common settings.
 */
public class NomadSparkMAXBrushed extends CANSparkMax {
    protected double mLastSet = Double.NaN;
    protected ControlType mLastControlType = null;
    /**
     * Constructs a SparkMAX, reverts it to factory default, and sets brake mode.
     * @param port The CAN ID of this SparkMAX
     */
    public NomadSparkMAXBrushed(int port){
        super(port, MotorType.kBrushed);
        this.restoreFactoryDefaults();
        this.setIdleMode(IdleMode.kBrake);
    }

    /**
     * Constructs a SparkMAX, reverts it to factory default, and sets brake mode and inversion status.
     * @param port The CAN ID of this SparkMAX.
     * @param inverted True for inverted, false if not.
     */
    public NomadSparkMAXBrushed(int port, boolean inverted) {
        this(port);
        this.setInverted(inverted);
    }
    /**
     * Constructs a SparkMAX, reverts it to factory default, sets brake mode and inversion status, and slaves it to a specified NomadSparkMAX.
     * @param port The CAN ID of this SparkMAX.
     * @param inverted True for inverted, false if not.
     * @param master The NomadSparkMAX to follow.
     */    
    public NomadSparkMAXBrushed(int port, boolean inverted, NomadSparkMAXBrushed master){
        this(port, inverted);
        this.follow(master);
    }

    /**
     * wrapper method to mimic TalonSRX set method
     */
    public void set(ControlType type, double setpoint) {
        if (setpoint != mLastSet || type != mLastControlType) {
            mLastSet = setpoint;
            mLastControlType = type;
            super.getPIDController().setReference(setpoint, type);
        }
    }
}
