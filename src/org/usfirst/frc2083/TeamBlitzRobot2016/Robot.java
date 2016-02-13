// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2083.TeamBlitzRobot2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANJaguar.JaguarControlMode;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;

import org.usfirst.frc2083.TeamBlitzRobot2016.commands.*;
import org.usfirst.frc2083.TeamBlitzRobot2016.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    DriveCommand driveCommand;
    ArmCommand armCommand;
    DigitalInput autoDistSelect;
//    ClawCommand clawCommand;
//    ShootCommand shootCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

        RobotMap.leftForwardMotorController = new CANJaguar(RobotMap.leftForwardMotorControllerID);
        RobotMap.leftBackMotorController = new CANJaguar(RobotMap.leftBackMotorControllerID);
        RobotMap.rightForwardMotorController = new CANJaguar(RobotMap.rightForwardMotorControllerID);
        RobotMap.rightBackMotorController = new CANJaguar(RobotMap.rightBackMotorControllerID);
        RobotMap.leftForwardMotorController.configNeutralMode(CANJaguar.NeutralMode.Brake);
        RobotMap.leftBackMotorController.configNeutralMode(CANJaguar.NeutralMode.Brake);
        RobotMap.rightForwardMotorController.configNeutralMode(CANJaguar.NeutralMode.Brake);
        RobotMap.rightBackMotorController.configNeutralMode(CANJaguar.NeutralMode.Brake);
        
        RobotMap.rightBackMotorController.setVoltageMode();
        RobotMap.leftBackMotorController.setVoltageMode();
        
        RobotMap.leftForwardMotorController.setVoltageMode(CANJaguar.kQuadEncoder, 360);
        RobotMap.rightForwardMotorController.setVoltageMode(CANJaguar.kQuadEncoder, 250);
        
        RobotMap.armBarMotorController = new CANTalon(RobotMap.armBarMotorControllerID);
        RobotMap.armBarMotorController.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        
        RobotMap.armBarMotorController.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
        RobotMap.armBarMotorController.enableBrakeMode(true);
        RobotMap.armBarMotorController.setForwardSoftLimit(756);
        RobotMap.armBarMotorController.enableForwardSoftLimit(true);
        RobotMap.armBarMotorController.setReverseSoftLimit(100);  //was 8
        RobotMap.armBarMotorController.enableReverseSoftLimit(true);
        
        autoDistSelect = new DigitalInput(RobotMap.autoDistSelectChannel);
        
//        double p = 1;
//        double i = .01;
//        double d = 0;
//        double f = 0;
//        int izone = 0;
//        double closeLoopRampRate = 10;
//        int profile = 0;
        //RobotMap.armBarMotorController.setPID(p , i , d, f, izone, closeLoopRampRate, profile);
       // RobotMap.armBarMotorController.reverseSensor(false);
        
        
//        RobotMap.leftFront.setPositionMode(CANJaguar.kQuadEncoder, 360, 0.01, 0, 0);
//        RobotMap.rightFront.setPositionMode(CANJaguar.kQuadEncoder, 250, 0.01, 0, 0);
        
//        RobotMap.leftFront.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
//        RobotMap.rightFront.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
//        RobotMap.leftFront.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
//        RobotMap.rightFront.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
                
//            RobotMap.compressorRelay = new Relay(1);
//            RobotMap.compressorRelay.setDirection(Relay.Direction.kForward);
//            RobotMap.solenoidRelay = new Relay(2);
//            RobotMap.solenoidRelay.setDirection(Relay.Direction.kForward);
//            RobotMap.shooterValveSolenoid = new Solenoid(1);
//            RobotMap.shooterValveSolenoid.set(false);
                    
        // Initialize all subsystems
        CommandBase.init();
        driveCommand = new DriveCommand();
        armCommand = new ArmCommand();
//            clawCommand = new ClawCommand();
        DriveCommand.xbox = new Joystick(0);
        ArmCommand.xbox = DriveCommand.xbox;
//            ClawCommand.xbox = DriveCommand.xbox;
//            shootCommand = new ShootCommand();
//            ShootCommand.xbox = DriveCommand.xbox;
        
       
        
//            clawCommand.disableControl();
        driveCommand.disableControl();
        armCommand.disableControl();
    }

    /**
     * This function is called at the beggining of autonomous control
     */
    public void autonomousInit() {
    	RobotMap.auto = true;
    	RobotMap.autoTimer = System.currentTimeMillis();
    	if (autoDistSelect.get()) {
    		RobotMap.autoDriveTime = 0; // if jumper is unplugged
    	} else {
    		RobotMap.autoDriveTime = 1500; // if jumper is plugged in
    	}
    	driveCommand.enableControl();
    	driveCommand.start();
    	//System.out.println("ran Autonomous init");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(System.currentTimeMillis()-RobotMap.autoTimer < RobotMap.autoDriveTime ){
    		RobotMap.autoY = .5;
//    		System.out.println("autoY = .5, System Time millis = " 
//    				+ System.currentTimeMillis() + 
//    				", autoTimer = " + RobotMap.autoTimer);
    	} else {
    		RobotMap.autoY = 0;
        }
    	Scheduler.getInstance().run();
    }
    /**
     * This function is called at the beggining of operator control (Teleop)
     */
    public void teleopInit() {
    	RobotMap.auto = false;
        System.out.println("TELEOP INIT");
        driveCommand.enableControl();
        driveCommand.start();
        armCommand.enableControl();
        armCommand.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
