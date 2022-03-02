// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //Defining DriveTrain Hardware
  VictorSPX driveLeftA = new VictorSPX(Constants.LEFT_MOTOR_A);
  VictorSPX driveLeftB = new VictorSPX(Constants.LEFT_MOTOR_B);
  VictorSPX driveRightA = new VictorSPX(Constants.RIGHT_MOTOR_A);
  VictorSPX driveRightB = new VictorSPX(Constants.RIGHT_MOTOR_B);
  
  //Defining Intake & Shooter Hardware
  Spark intake = new Spark(Constants.INTAKE_MOTOR);
  Spark intakeBelt = new Spark(Constants.INTAKE_BELT_MOTOR);

  //Defining XboxController
  XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER);
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    driveLeftA.setInverted(true);
    driveLeftB.setInverted(true);
    driveRightA.setInverted(false);
    driveRightB.setInverted(false);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected");
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //SmartDashboard.putNumber("Joystick Left Y value", driverController.getRawAxis(Constants.CONTROLLER_Y1_AXIS));
    //SmartDashboard.putNumber("Trigger Right Value", driverController.getRawAxis(Constants.CONTROLLER_RIGHT_TRIGGER));
    //SmartDashboard.putNumber("Trigger Left Value", driverController.getRawAxis(Constants.CONTROLLER_LEFT_TRIGGER));

    double forward = -driverController.getRawAxis(Constants.CONTROLLER_Y1_AXIS);
    double turn1 = driverController.getRawAxis(Constants.CONTROLLER_RIGHT_TRIGGER);
    double turn2 = -driverController.getRawAxis(Constants.CONTROLLER_LEFT_TRIGGER);
    double turn = (turn1 + turn2);
    double driveLeftPower = forward - turn;
    double driveRightPower = forward + turn;

    driveLeftA.set(ControlMode.PercentOutput, driveLeftPower*0.5);
    driveLeftB.set(ControlMode.PercentOutput, driveLeftPower*0.5);
    driveRightA.set(ControlMode.PercentOutput, driveRightPower*0.5);
    driveRightB.set(ControlMode.PercentOutput, driveRightPower*0.5);

    if(driverController.getRawButton(2)){
      intake.set(1);
      intakeBelt.set(1);
    }
    else if(driverController.getRawButton(1)){
      intake.set(-1);
      intakeBelt.set(-1);
    }
    else{
      intake.set(0);
      intakeBelt.set(0);
    }


  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    driveLeftA.set(ControlMode.PercentOutput, 0);
    driveLeftB.set(ControlMode.PercentOutput, 0);
    driveRightA.set(ControlMode.PercentOutput, 0);
    driveRightB.set(ControlMode.PercentOutput, 0);

    intake.set(0);
    intakeBelt.set(0);
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
