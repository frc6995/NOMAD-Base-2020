package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Preferences;
import frc.robot.constants.VisionConstants;
import frc.utility.MovingAverage;
import frc.wrappers.Limelight;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

/**
 * This subsystem gives access to our limelight.
 * 
 * @author Sammcdo
 */
public class LimelightS extends SubsystemBase implements Loggable {
  @Log
  private Limelight limelight;

  @Log
  private MovingAverage horizontalAvg;
  private int horizontalAverageIndex = 5;

  @Log
  private MovingAverage verticalAvg;
  private int verticalAverageIndex = 5;

  @Config.PIDController(name="Horizontal Adjust")
  private PIDController horizontalAdjust;
  @Config.PIDController(name="Vertical Adjust")
  private PIDController verticalAdjust;

  private double sumInRange;

  /**
   * Create a new LimelightS.
   */
  public LimelightS() {
    limelight = new Limelight("limelight");

    horizontalAvg = new MovingAverage(horizontalAverageIndex);
    verticalAvg = new MovingAverage(verticalAverageIndex);

    horizontalAdjust = new PIDController(Preferences.VISION_KP_HORIZONTAL.getValue(), 0, 0);
    horizontalAdjust.setSetpoint(0);
    horizontalAdjust.setTolerance(VisionConstants.HORIZONTAL_ERROR);

    verticalAdjust = new PIDController(Preferences.VISION_KP_VERTICAL.getValue(), 0, 0);
    verticalAdjust.setSetpoint(0);
    verticalAdjust.setTolerance(VisionConstants.VERTICAL_ERROR);
  }

  /**
   * Get the averaged value of the horizontal offset
   * @return The average horizontal offset in degrees.
   */
  @Log.Graph(name="Limelight X")
  public double getX() {
    if (limelight.hasTarget()) {
      horizontalAvg.addSample(limelight.getXOffset());
      return horizontalAvg.getAverage();
    }
    else {
      return 0;
    }
  }

  /**
   * Get the averaged value of the vertical offest.
   * @return The average vertical offset.
   */
  @Log.Graph(name="Limelight Y")
  public double getY() {
    if (limelight.hasTarget()) {
      verticalAvg.addSample(limelight.getYOffset());
      return verticalAvg.getAverage();
    }
    else {
      return 0;
    }
  }

  /**
   * Get the output of the horizontal PID
   * @return The output of the horizintal PID
   */
  public double getHorizontalAdjust() {
    return horizontalAdjust.calculate(getX());
  }

  /**
   * Get the output of the vertical PID
   * @return The output of the vertical PID
   */
  public double getVerticalAdjust() {
    return verticalAdjust.calculate(getY());
  }

  /**
   * Check if the PID controllers have been within acceptable
   * range for a pre-specified time.
   * @return whether both PID controllers are at their set points.
   */
  @Log.BooleanBox(name="At Set Point")
  public boolean atSetPoint() {
    if (horizontalAdjust.atSetpoint() && verticalAdjust.atSetpoint()) {
      sumInRange++;
    }
    else {
      sumInRange = 0;
    }
    if (sumInRange >= VisionConstants.SET_POINT_COUNT) {
      return true;
    }
    else {
      return false;
    }
  }


  @Override
  public void periodic() {
  }
}
