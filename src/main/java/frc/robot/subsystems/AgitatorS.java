/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.wrappers.MotorControllers.NomadSparkMax;

public class AgitatorS extends SubsystemBase {
  private NomadSparkMax leftMotor;
  private NomadSparkMax rightMotor;
  
  /**
   * Creates a new Agitator.
   */
  public AgitatorS() {
    leftMotor = new NomadSparkMax(31);
    rightMotor = new NomadSparkMax(32);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setLeftMotor(double motorSpeed){
    leftMotor.set(motorSpeed);
  }

  public void setRightMotor(double motorSpeed){
    rightMotor.set(motorSpeed);
    }

    public void stopLeftMotor(){
      leftMotor.stopMotor();
    }

    public void stopRightMotor(){
      rightMotor.stopMotor();
    }
}
