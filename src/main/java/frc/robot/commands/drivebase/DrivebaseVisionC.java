package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.LimelightS;

/**
 * VisionAlignC uses the Limelight PID and the drivebase
 * to accurately position the robot relative to a target.
 * 
 * @author Ari Shashivkopanazak, Shueja, Sammcdo
 */
public class DrivebaseVisionC extends CommandBase {
  DrivebaseS drivebase;
  LimelightS limelight;

  private DifferentialDriveWheelSpeeds wheelSpeeds; 


  // Adjustment values
  private double horizontalAdjust = 0.0;
  private double verticalAdjust = 0.0;

  /** Ramps our PID to full over the period of ramp time */
  private double clampValue = 0.0;

  /** Determines if this is the first loop in the target Range */
  private boolean firstLoop = true;

  //General WPILIB Timer
  private Timer rampTimer = new Timer();

  /**
   * Allows the Robot to Aim the shooter at a target.
   * 
   * @param drivebaseS The DrivebaseS object to use.
   */
  public DrivebaseVisionC(DrivebaseS drivebaseS, LimelightS limelightS, int pipeline) {
    addRequirements(drivebaseS);
    drivebase = drivebaseS;
    limelight = limelightS;
  }

  /**
   * Determine that this is the first loop
   */
  @Override
  public void initialize() {

    firstLoop = true;
  }

  /**
   * Start the timer After the first loop, first loop returns false Set the
   * pipline to the vision mode Turns on LED Get horizontal position offset and
   * assign it to a double Get vertical position offset and assign it to a double
   * The horizontal point we need to adjust to is defined as our horizontal error
   * times our horizontal P Value The Vertical point we need to adjust to is
   * defined as our vertical error times our vertical P Value Input these values
   * into the drivebases arcadeDrive
   */
  @Override
  public void execute() {
    if (firstLoop) {
      rampTimer.stop();
      rampTimer.reset();
      rampTimer.start();
      firstLoop = false;
    }

    horizontalAdjust = limelight.getHorizontalAdjust();
    verticalAdjust = limelight.getVerticalAdjust();

    clampValue = MathUtil.clamp(rampTimer.get() / VisionConstants.VISION_RAMP_TIME, -1, 1);

    horizontalAdjust = MathUtil.clamp(horizontalAdjust, -clampValue, clampValue);
    verticalAdjust = MathUtil.clamp(verticalAdjust, -clampValue, clampValue);

    wheelSpeeds = DriveConstants.kDriveKinematics
        .toWheelSpeeds(new ChassisSpeeds(verticalAdjust, 0, Math.toRadians(horizontalAdjust)));
    drivebase.trajectoryDrive(wheelSpeeds.leftMetersPerSecond - horizontalAdjust, wheelSpeeds.rightMetersPerSecond + horizontalAdjust);
  }

  /**
   * Stop Ramp timer Reset to First loop turn off the leds
   */
  @Override
  public void end(boolean interrupted) {
    rampTimer.stop();
    rampTimer.reset();
    firstLoop = true;
  }

  /**
   * When Crosshairs are within the range,
   * 
   * @return the count to the amount needed to end the command
   */
  @Override
  public boolean isFinished() {
    return limelight.atSetPoint();
  }
}
