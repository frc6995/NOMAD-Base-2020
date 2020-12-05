/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.wrappers.motorcontrollers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Add your docs here.
 */
public class NomadMotor {
    private WPI_TalonSRX talon = null;
    private WPI_VictorSPX victor = null;
    private CANSparkMax sparkMax = null;
    
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
