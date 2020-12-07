package frc.robot.wrappers.motorcontrollers;

import com.revrobotics.CANSparkMax;

/**
 * This class is an encapsulation of WPI_SparkMAX that add a couple constructors
 * for forcing common settings.
 */
public class NomadSparkMaxBrushed extends NomadSparkMax {

    /**
     * Constructs a SparkMAX, reverts it to factory default, and sets brake mode.
     * 
     * @param port The CAN ID of this SparkMAX
     */
    public NomadSparkMaxBrushed(int port) {
        super(port);
        setMotorType(CANSparkMax.MotorType.kBrushed);
        configFactoryDefault();
        setIdleMode(IdleMode.Brake);
    }

    /**
     * Constructs a SparkMAX, reverts it to factory default, and sets brake mode and
     * inversion status.
     * 
     * @param port     The CAN ID of this SparkMAX.
     * @param inverted True for inverted, false if not.
     */
    public NomadSparkMaxBrushed(int port, boolean inverted) {
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
    public NomadSparkMaxBrushed(int port, boolean inverted, NomadSparkMaxBrushed master) {
        this(port, inverted);
        follow(master);
    }
}
