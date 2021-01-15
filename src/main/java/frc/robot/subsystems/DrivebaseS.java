/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.simulation.ADXRS450_GyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.PDPSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.VecBuilder;
import frc.robot.constants.AutoConstants;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.DriveConstantsSim;
import frc.robot.utility.drivebase.DrivebaseWheelPercentages;
import frc.robot.wrappers.motorcontrollers.NomadTalonSRX;

public class DrivebaseS extends SubsystemBase {
  private NomadTalonSRX left = new NomadTalonSRX(10);
  private NomadTalonSRX right = new NomadTalonSRX(11);

  private ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();
  private ADXRS450_GyroSim m_gyroSim;

  private final DifferentialDrive m_drive = new DifferentialDrive(left, right);

  public DifferentialDrivetrainSim m_drivetrainSimulator;
  private Field2d m_fieldSim;
  private final DifferentialDriveOdometry m_odometry;

  /**
   * Creates a new DrivebaseS.
   */
  public DrivebaseS(final DriveConstants driveConstants, final AutoConstants autoConstants) {
    left.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    right.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    if (RobotBase.isSimulation()) { // If our robot is simulated
      // This class simulates our drivetrain's motion around the field.
      m_drivetrainSimulator =
          new DifferentialDrivetrainSim(
              DriveConstantsSim.kDrivetrainPlant,
              DriveConstantsSim.kDriveGearbox,
              DriveConstantsSim.kDriveGearing,
              DriveConstantsSim.kTrackwidthMeters,
              DriveConstantsSim.kWheelDiameterMeters / 2.0,
              VecBuilder.fill(0, 0, 0.0001, 0.1, 0.1, 0.005, 0.005));

      // The encoder and gyro angle sims let us set simulated sensor readings
      //m_leftEncoderSim = new EncoderSim(m_leftEncoder);
      //m_rightEncoderSim = new EncoderSim(m_rightEncoder);
      m_gyroSim = new ADXRS450_GyroSim(m_gyro);

      // the Field2d class lets us visualize our robot in the simulation GUI.
      m_fieldSim = new Field2d();
      SmartDashboard.putData("Field", m_fieldSim);
    }
  }
  
  @Override
  public void periodic() {
    m_odometry.update(
        Rotation2d.fromDegrees(getHeading()),
        getLeftEncoderDistance(),
        getRightEncoderDistance());
    m_fieldSim.setRobotPose(getPose());   
  }

  public void simulationPeriodic() {
    // To update our simulation, we set motor voltage inputs, update the simulation,
    // and write the simulated positions and velocities to our simulated encoder and gyro.
    // We negate the right side so that positive voltages make the right side
    // move forward.
    m_drivetrainSimulator.setInputs(
        left.get() * RobotController.getBatteryVoltage(),
        -right.get() * RobotController.getBatteryVoltage());
    m_drivetrainSimulator.update(0.020);

    left.getSimCollection().setQuadratureRawPosition((int)(m_drivetrainSimulator.getLeftPositionMeters() / DriveConstantsSim.kEncoderDistancePerPulse));
    left.getSimCollection().setQuadratureVelocity((int)(m_drivetrainSimulator.getLeftVelocityMetersPerSecond()/DriveConstantsSim.kEncoderDistancePerPulse*10));
    right.getSimCollection().setQuadratureRawPosition((int)(m_drivetrainSimulator.getRightPositionMeters() / DriveConstantsSim.kEncoderDistancePerPulse));
    right.getSimCollection().setQuadratureVelocity((int)(m_drivetrainSimulator.getRightVelocityMetersPerSecond()/DriveConstantsSim.kEncoderDistancePerPulse*10));
    
    m_gyroSim.setAngle(-m_drivetrainSimulator.getHeading().getDegrees());
  }

  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  public double getDrawnCurrentAmps() {
    return m_drivetrainSimulator.getCurrentDrawAmps();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoderRate(), getRightEncoderRate());
  }

  public double getLeftEncoderDistance() {
    return left.getSelectedSensorPosition() * DriveConstantsSim.kEncoderDistancePerPulse;
  }
  public double getLeftEncoderRate() {
    return left.getSelectedSensorVelocity() * DriveConstantsSim.kEncoderDistancePerPulse / 10;
  }

  public double getRightEncoderDistance() {
    return right.getSelectedSensorPosition() * DriveConstantsSim.kEncoderDistancePerPulse;
  }
  public double getRightEncoderRate() {
    return right.getSelectedSensorVelocity() * DriveConstantsSim.kEncoderDistancePerPulse / 10;
  }
  
  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DriveConstantsSim.kGyroReversed ? -1.0 : 1.0);
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_drivetrainSimulator.setPose(pose);
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  public void resetEncoders() {
    left.setSelectedSensorPosition(0);
    right.setSelectedSensorPosition(0);
  }

  public double getAverageEncoderDistance() {
    return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0;
  }

  public void zeroHeading() {
    m_gyro.reset();
  }

  public DrivebaseWheelPercentages arcadeDriveController(double fwdBack, double leftRight) {
    double leftPercent = fwdBack + leftRight; 
    double rightPercent = fwdBack - leftRight;
    return new DrivebaseWheelPercentages().setLeftPercentage(leftPercent).setRightPercentage(rightPercent);
  }

  public void drivePercentages(DrivebaseWheelPercentages percentages){
    left.set(ControlMode.PercentOutput, percentages.getLeftPercentage());
    right.set(ControlMode.PercentOutput, percentages.getRightPercentage());
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    var batteryVoltage = RobotController.getBatteryVoltage();
    if (Math.max(Math.abs(leftVolts), Math.abs(rightVolts)) > batteryVoltage) {
      leftVolts *= batteryVoltage / 12.0;
      rightVolts *= batteryVoltage / 12.0;
    }
    left.setVoltage(leftVolts);
    right.setVoltage(-rightVolts);
    m_drive.feed();
  }

  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

}
