// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2502.robot2022.subsystems;

import com.team2502.robot2022.Constants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax intakeMotor;
    private final CANSparkMax topBelt;
    private final CANSparkMax bottomBelt;

    private final Solenoid intakeDeploySolenoid;

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(Constants.RobotMap.Motors.INTAKE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
        topBelt = new CANSparkMax(Constants.RobotMap.Motors.SQUEEZE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
        bottomBelt = new CANSparkMax(Constants.RobotMap.Motors.HOPPER_BOTTOM_BELT, CANSparkMaxLowLevel.MotorType.kBrushless);

        intakeDeploySolenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.RobotMap.Solenoids.INTAKE);

        intakeMotor.setSmartCurrentLimit(40);
        topBelt.setSmartCurrentLimit(25);
        bottomBelt.setSmartCurrentLimit(25);
    }

    public void run(double intakeSpeed, double beltSpeed) {
        intakeMotor.set(intakeSpeed);
        topBelt.set(-beltSpeed);
        bottomBelt.set(beltSpeed);
    }

    public void runSqueeze(double speed) {
        topBelt.set(speed);
    }

    public void stop() {
        intakeMotor.stopMotor();
        bottomBelt.stopMotor();
        topBelt.stopMotor();
        intakeDeploySolenoid.set(false);
    }

    public void stopSqueeze() {
        topBelt.set(0);
    }

    public void deployIntake()
    {
        intakeDeploySolenoid.set(false);
    }

    public void retractIntake()
    {
        intakeDeploySolenoid.set(true);
    }

    public void toggleIntake()
    {
        intakeDeploySolenoid.set(!intakeDeploySolenoid.get());
    }
}
