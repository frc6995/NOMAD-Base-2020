/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.utility;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Add your docs here.
 */
public class Point2d implements Sendable{
    public double x;
    public double y;

    public Point2d (double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("MyPoint2D");
        builder.addDoubleProperty("x", this::getX, this::setX);
        builder.addDoubleProperty("y", this::getY, this::setY);
        // builder.addDoubleProperty("i", this::getI, this::setI);
        // builder.addDoubleProperty("d", this::getD, this::setD);
        // builder.addDoubleProperty("setpoint", this::getSetpoint, this::setSetpoint);
    }
}
