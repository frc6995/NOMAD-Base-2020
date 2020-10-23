/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.function.Consumer;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.wrappers.MotorControllers.NomadTalonSRX;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickC;
import frc.robot.utility.drivebase.DrivebaseWheelPercentages;

public class DrivebaseS extends SubsystemBase {
  public NomadTalonSRX leftTalon;
  public NomadTalonSRX rightTalon;
  public DrivebaseArcadeDriveStickC drivebaseArcadeDriveStickC;
  /**
   * Creates a new DrivebaseS.
   */
  public DrivebaseS() {
    leftTalon = new NomadTalonSRX(10);
    rightTalon = new NomadTalonSRX(11); // TODO Refactor into constants file.
    //Instantiate commands 
  }
  public DrivebaseS(NomadTalonSRX leftTalonSRX, NomadTalonSRX rightTalonSRX){
    leftTalon = leftTalonSRX;
    rightTalon = rightTalonSRX;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run    
  }

  /**
   * Basic arcade drive. Converts joystick inputs into percent outputs for each side of the drivebase.
   * @param fwdBack The joystick input for the forward/back axis. It is assumed that a value of 1 represents forward, and -1 represents backward.
   * @param leftRight The joystick input for the left/right axis. It is assumed that 1 represents left point turn, and -1 represents right point turn.
   */
  public DrivebaseWheelPercentages arcadeDriveController(double fwdBack, double leftRight) {
    double leftPercent = MathUtil.clamp((fwdBack + leftRight), -1, 1); 
    double rightPercent = MathUtil.clamp((fwdBack - leftRight), -1, 1);
    return new DrivebaseWheelPercentages().setLeftPercentage(leftPercent).setRightPercentage(rightPercent);
  }

  public void drivePercentages(DrivebaseWheelPercentages percentages){
    leftTalon.set(ControlMode.PercentOutput, percentages.getLeftPercentage());
    rightTalon.set(ControlMode.PercentOutput, percentages.getRightPercentage());
  }
}
