/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.wrappers.motorcontrollers;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

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
    public enum IdleMode{
        Brake {
            
            protected NeutralMode getTalonVersion(){
                return NeutralMode.Brake;
            }

            protected NeutralMode getVictorVersion(){
                return NeutralMode.Brake;
            }

            protected CANSparkMax.IdleMode getSparkVersion(){
                return CANSparkMax.IdleMode.kBrake;
            }
        },
        Coast{
            protected NeutralMode getTalonVersion(){
                return NeutralMode.Coast;
            }

            protected NeutralMode getVictorVersion(){
                return NeutralMode.Coast;
            }

            protected CANSparkMax.IdleMode getSparkVersion(){
                return CANSparkMax.IdleMode.kCoast;
            }
        };

        protected abstract NeutralMode getTalonVersion();
        protected abstract NeutralMode getVictorVersion();
        protected abstract CANSparkMax.IdleMode getSparkVersion();

    }

    public enum MotorType{
        TalonSRX{
            protected boolean isTalonSRX(){
                return true;
            }
            
            protected boolean isVictorSPX(){
                return false;
            }

            protected boolean isSparkMax(){
                return false;
            }
        },
        VictorSPX{
            protected boolean isTalonSRX(){
                return false;
            }

            protected boolean isVictorSPX(){
                return true;
            }

            protected boolean isSparkMax(){
                return false;
            }
        },
        SparkMax{
            protected boolean isTalonSRX(){
                return false;
            }

            protected boolean isVictorSPX(){
                return false;
            }

            protected boolean isSparkMax(){
                return true;
            }
        };

        protected abstract boolean isTalonSRX();
        protected abstract boolean isVictorSPX();
        protected abstract boolean isSparkMax();
    }

    private WPI_TalonSRX talon = null;
    private WPI_VictorSPX victor = null;
    private CANSparkMax sparkMax = null;

    protected boolean lazy = false;

    protected double lastPower = Double.NaN;
    protected ControlMode lastMode = null;
    
    protected MotorType motorType;

    public NomadMotor(WPI_TalonSRX talonSRX){
        talon = talonSRX;        
        motorType = MotorType.TalonSRX;
    }

    public NomadMotor(WPI_VictorSPX victorSPX){
        victor = victorSPX;
        motorType = MotorType.VictorSPX;
    }

    public NomadMotor(CANSparkMax sparkMax){
        this.sparkMax = sparkMax;
        motorType = MotorType.SparkMax;
    }

    public void setIdleMode(IdleMode mode){
            if (motorType.isTalonSRX()){
                talon.setNeutralMode(mode.getTalonVersion());
            }
            else if (motorType.isVictorSPX()){
                victor.setNeutralMode(mode.getVictorVersion());
            }
            else if (motorType.isSparkMax()){
                sparkMax.setIdleMode(mode.getSparkVersion());
            }            
    }


    public void setInverted(boolean inverted){
        if (motorType.isTalonSRX()){
            talon.setInverted(inverted);
        }
        else if (motorType.isVictorSPX()){
            victor.setInverted(inverted);
        }
        else if (motorType.isSparkMax()){
            sparkMax.setInverted(inverted);
        }
    }

    public void configFactoryDefault(){
        if (motorType.isTalonSRX()){
            talon.configFactoryDefault();
        }
        else if (motorType.isVictorSPX()){
            victor.configFactoryDefault();
        }
        else if (motorType.isSparkMax()){
            sparkMax.restoreFactoryDefaults();
        }
    }

    public void setSafetyEnabled(boolean enabled){
        if (motorType.isTalonSRX()){
            talon.setSafetyEnabled(enabled);
        }
        else if (motorType.isVictorSPX()){
            victor.setSafetyEnabled(enabled);
        }
    }

    public void follow(NomadMotor master){
        if (master.motorType.isTalonSRX()){
            if (motorType.isTalonSRX()){
                talon.follow(master.getTalonSRX());
            }
            else if (motorType.isVictorSPX()){
                victor.follow(master.getTalonSRX());
            }            
        }
        else if (master.motorType.isVictorSPX()){
            if (motorType.isTalonSRX()){
                talon.follow(master.getVictorSPX());            
            }
            else if (motorType.isVictorSPX()){
                victor.follow(master.getVictorSPX());
            }
        }
        else if (master.motorType.isSparkMax()){
            sparkMax.follow(master.getSparkMax());
        }
    }   

    public void follow(NomadMotor master, FollowerType followerType){
        if (master.motorType.isTalonSRX()){
            if (motorType.isTalonSRX()){
                talon.follow(master.getTalonSRX(), followerType);
            }
            else if (motorType.isVictorSPX()){
                victor.follow(master.getTalonSRX(), followerType);
            }            
        }
        else if (master.motorType.isVictorSPX()){
            if (motorType.isTalonSRX()){
                talon.follow(master.getVictorSPX(), followerType);            
            }
            else if (motorType.isVictorSPX()){
                victor.follow(master.getVictorSPX(), followerType);
            }
        }
        else if (master.motorType.isSparkMax()){
            sparkMax.follow(master.getSparkMax());
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
        if (motorType.isTalonSRX()){
            talon.set(mode, value);
        }
        else if (motorType.isVictorSPX()){
            victor.set(mode, value);
        }
    }

    public void set(ControlType controlType, double setpoint){
        if (motorType.isSparkMax()){
            sparkMax.getPIDController().setReference(setpoint, controlType);
        }
    }

    public CANPIDController getPIDController(){
        if (motorType.isSparkMax()){
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
