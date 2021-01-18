// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.template.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.hal.simulation.SimDeviceDataJNI;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.simulation.ADXRS450_GyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.VecBuilder;
import frc.lib.subsystems.DifferentialDrivebaseS;
import frc.lib.utility.drivebase.DrivebaseWheelPercentages;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.template.constants.AutoConstants;
import frc.template.constants.DriveConstants;

public class AutonomousDrivebaseS extends DifferentialDrivebaseS {
  private NomadTalonSRX m_leftFront = new NomadTalonSRX(21);
  private NomadTalonSRX m_rightFront = new NomadTalonSRX(22);

  private DifferentialDrive m_drive = new DifferentialDrive(m_leftFront, m_rightFront);

  private Encoder m_leftEncoder = new Encoder(0, 1, false);
  private Encoder m_rightEncoder = new Encoder(2, 3, true);

  private EncoderSim m_leftEncoderSim = new EncoderSim(m_leftEncoder);
  private EncoderSim m_rightEncoderSim = new EncoderSim(m_rightEncoder);

  private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  private ADXRS450_GyroSim m_gyroSim;

  static final double KvLinear = 1.98;
  static final double KaLinear = 0.2;
  static final double KvAngular = 1.5;
  static final double KaAngular = 0.3;
  static final double kWheelRadius = Units.inchesToMeters(6);
  static final double kEncoderResolution = 8192;

  // Create the simulation model of our drivetrain.
  private DifferentialDrivetrainSim m_driveSim = new DifferentialDrivetrainSim(
      // Create a linear system from our characterization gains.
      LinearSystemId.identifyDrivetrainSystem(KvLinear, KaLinear, KvAngular, KaAngular), DCMotor.getCIM(2), // The track
                                                                                                            // width is
                                                                                                            // 0.7112
                                                                                                            // meters.
      7.29, // 7.29:1 gearing reduction.
      0.7112, // 2 NEO motors on each side of the drivetrain.
      kWheelRadius, // The robot uses 3" radius wheels.

      // The standard deviations for measurement noise:
      // x and y: 0.001 m
      // heading: 0.001 rad
      // l and r velocity: 0.1 m/s
      // l and r position: 0.005 m
      VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005));

  private DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(new Rotation2d());

  private Field2d m_field = new Field2d();

  /** Creates a new AutonomousDrivebaseS. */
  public AutonomousDrivebaseS(DriveConstants driveConstants, AutoConstants autoConstants) {
    super(driveConstants, autoConstants);

    m_leftEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution);
    m_rightEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution);

    SmartDashboard.putData("Field", m_field);

    m_gyroSim = new ADXRS450_GyroSim(gyro);

    resetOdometry(new Pose2d());
  }

  @Override
  public void periodic() {
    m_odometry.update(gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
    m_field.setRobotPose(m_odometry.getPoseMeters());
  }

  public void simulationPeriodic() {
    // Set the inputs to the system. Note that we need to convert
    // the [-1, 1] PWM signal to voltage by multiplying it by the
    // robot controller voltage.
    m_driveSim.setInputs(m_leftFront.get() * RobotController.getInputVoltage(),
        -m_rightFront.get() * RobotController.getInputVoltage());

    // Advance the model by 20 ms. Note that if you are running this
    // subsystem in a separate thread or have changed the nominal timestep
    // of TimedRobot, this value needs to match it.
    m_driveSim.update(0.02);

    // Update all of our sensors.
    m_leftEncoderSim.setDistance(m_driveSim.getLeftPositionMeters());
    m_leftEncoderSim.setRate(m_driveSim.getLeftVelocityMetersPerSecond());
    m_rightEncoderSim.setDistance(m_driveSim.getRightPositionMeters());
    m_rightEncoderSim.setRate(m_driveSim.getRightVelocityMetersPerSecond());

    m_gyroSim.setAngle(-m_driveSim.getHeading().getDegrees());
  }

  @Override
  public double getYaw() {
    return Math.IEEEremainder(gyro.getAngle(), 360) * (driveConstants.getGyroReversed() ? -1.0 : 1.0);
  }

  @Override
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
  }

  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  @Override
  public double getLeftVelocity() {
    return m_leftEncoder.getRate();
  }

  @Override
  public double getRightVelocity() {
    return m_rightEncoder.getRate();
  }

  @Override
  public DrivebaseWheelPercentages arcadeDriveController(double fwdBack, double leftRight) {
    double leftPercent = fwdBack + leftRight;
    double rightPercent = fwdBack - leftRight;
    return new DrivebaseWheelPercentages().setLeftPercentage(leftPercent).setRightPercentage(rightPercent);
  }

  @Override
  public void drivePercentages(DrivebaseWheelPercentages percentages) {
    m_leftFront.set(ControlMode.PercentOutput, percentages.getLeftPercentage());
    m_rightFront.set(ControlMode.PercentOutput, percentages.getRightPercentage());
  }

  @Override
  public void stopMotor() {
    m_leftFront.stopMotor();
    m_rightFront.stopMotor();
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_driveSim.setPose(pose);
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getYaw()));
  }

  @Override
  public void updateTelemetry() {

    SmartDashboard.putData("Drivebase", this);
    // SmartDashboard.putNumber("fwdbackaxis",
    // driveConstants.getDriveControllerFwdBackAxis());
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("DifferentialDrive");
    builder.setActuator(false);
    builder.setSafeState(this::stopMotor);
    builder.addDoubleProperty("Left Motor Speed", this::getLeftVelocity, null);
    builder.addDoubleProperty("Right Motor Speed", this::getRightVelocity, null);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    var batteryVoltage = RobotController.getBatteryVoltage();
    if (Math.max(Math.abs(leftVolts), Math.abs(rightVolts)) > batteryVoltage) {
      leftVolts *= batteryVoltage / 12.0;
      rightVolts *= batteryVoltage / 12.0;
    }
    m_leftFront.setVoltage(leftVolts);
    m_rightFront.setVoltage(-rightVolts);
    m_drive.feed();
  }
}
