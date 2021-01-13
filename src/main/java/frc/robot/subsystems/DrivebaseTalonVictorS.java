/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.wrappers.motorcontrollers.NomadNoneMotor;
import frc.robot.wrappers.motorcontrollers.NomadTalonSRX;

public class DrivebaseTalonVictorS extends SubsystemBase {
  public NomadTalonSRX<NomadNoneMotor> leftLeader;
  /**
   * The configuration for the Talons.
   */
  //private DifferentialDrive differentialDrive;
  //private final DifferentialDriveOdometry differentialDriveOdometry;


  /**
   * Creates a new DrivebaseS.
   */
  public DrivebaseTalonVictorS() {
    leftLeader =  new NomadTalonSRX<NomadNoneMotor>(10);
  }

  public void periodic() {
    // This method will be called once per scheduler run    
  }


  


}
