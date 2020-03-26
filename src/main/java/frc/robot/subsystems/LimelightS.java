package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Preferences;
import frc.robot.constants.VisionConstants;
import frc.utility.MovingAverage;
import frc.wrappers.Limelight;

/**
 * This subsystem gives access to our limelight
 * @author Sammcdo
 */
public class LimelightS extends SubsystemBase {
  private Limelight limelight;

  private int horizontalAverageIndex = 5;
  private MovingAverage horizontalAvg;

  private int verticalAverageIndex = 5;
  private MovingAverage verticalAvg;

  private PIDController horizontalAdjust;
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

  public double getX() {
    horizontalAvg.addSample(limelight.getXOffset());
    return horizontalAvg.getAverage();
  }

  public double getY() {
    verticalAvg.addSample(limelight.getYOffset());
    return verticalAvg.getAverage();
  }

  public double getHorizontalAdjust() {
    return horizontalAdjust.calculate(getX());
  }

  public double getVerticalAdjust() {
    return verticalAdjust.calculate(getY());
  }

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
