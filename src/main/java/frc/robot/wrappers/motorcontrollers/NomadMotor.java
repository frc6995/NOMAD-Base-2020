/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.wrappers.motorcontrollers;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.PIDController;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.ControlType;

/**
 * Add your docs here.
 */
public class NomadMotor {
    private WPI_TalonSRX talon = null;
    private WPI_VictorSPX victor = null;
    private CANSparkMax sparkMax = null;

    protected boolean lazy = false;

    protected double lastPower = Double.NaN;
    protected ControlMode lastMode = null;
    
    public NomadMotor(WPI_TalonSRX talonSRX){
        talon = talonSRX;
    }

    public NomadMotor(WPI_VictorSPX victorSPX){
        victor = victorSPX;
    }

    public NomadMotor(CANSparkMax sparkMax){
        this.sparkMax = sparkMax;
    }

    public void setNeutralMode(NeutralMode mode){
        if (talon != null){
            talon.setNeutralMode(mode);
        }
        else if (victor != null){
            victor.setNeutralMode(mode);
        }
    }

    public void setIdleMode(IdleMode mode){
            if (sparkMax != null){
                sparkMax.setIdleMode(mode);
            }
    }

    public void setInverted(boolean inverted){
        if (talon != null){
            talon.setInverted(inverted);
        }
        else if (victor != null){
            victor.setInverted(inverted);
        }
        else if (sparkMax != null){
            sparkMax.setInverted(inverted);
        }
    }

    public void configFactoryDefault(){
        if (talon != null){
            talon.configFactoryDefault();
        }
        else if (victor != null){
            victor.configFactoryDefault();
        }
        else if (sparkMax != null){
            sparkMax.restoreFactoryDefaults();
        }
    }

    public void setSafetyEnabled(boolean enabled){
        if (talon != null){
            talon.setSafetyEnabled(enabled);
        }
        else if (victor != null){
            victor.setSafetyEnabled(enabled);
        }
    }

    public void follow(NomadTalonSRX master){
        if (talon != null){
            talon.follow(master.getTalonSRX());
        }
        else if (victor != null){
            victor.follow(master.getTalonSRX());
        }        
    }

    public void follow(NomadVictorSPX master){
        if (talon != null){
            talon.follow(master.getVictorSPX());
        }
        else if (victor != null){
            victor.follow(master.getVictorSPX());
        }
    }

    public void follow(NomadSparkMax master){
        if (sparkMax != null){
            sparkMax.follow(master.getSparkMax());
        }
    }

    public void follow(NomadTalonSRX master, FollowerType followerType){
        if (talon != null){
            talon.follow(master.getTalonSRX(), followerType);
        }
        else if (victor != null){
            victor.follow(master.getTalonSRX(), followerType);
        }
    }

    public void follow(NomadVictorSPX master, FollowerType followerType){
        if (talon != null){
            talon.follow(master.getVictorSPX(), followerType);
        }
        else if (victor != null){
            victor.follow(master.getVictorSPX(), followerType);
        }
    }

    /**
     * Check if the motor controller is lazy
     * 
     * @return Whether the motor controller is lazy
     */
    public boolean isLazy() {
        return lazy;
    }

    /**
     * Set the lazy mode
     * 
     * @param isLazy A boolean for the lazy mode, where true is lazy on
     */
    public void setLazy(boolean isLazy) {
        lazy = isLazy;
    }

    public void set(ControlMode mode, double value){
        if (talon != null){
            talon.set(mode, value);
        }
        else if (victor != null){
            victor.set(mode, value);
        }
    }

    public void set(ControlType controlType, double setpoint){
        if (sparkMax != null){
            sparkMax.getPIDController().setReference(setpoint, controlType);
        }
    }

    public CANPIDController getPIDController(){
        if (sparkMax != null){
            return sparkMax.getPIDController();
        }
        else{
            return null;
        }
    }

    protected WPI_TalonSRX getTalonSRX() {
        return talon;
    }

    protected WPI_VictorSPX getVictorSPX(){
        return victor;
    }

    protected CANSparkMax getSparkMax(){
        return sparkMax;
    }
}
